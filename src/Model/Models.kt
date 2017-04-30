package Model

import View.Viewer
import java.awt.Color
import java.awt.image.BufferedImage
import kotlin.collections.HashMap


/**
 * Created by nikita on 24.04.17.
 */


class PixelColor(){


}

interface Model {
    fun pushEvent()
    fun registerDrawer(drawer: Viewer)
    fun removeDrawer(drawer: Viewer)
}

class ImageInfo(tableData: HashMap<String, Long>, ColorTable: MutableList<Byte>) {
    var fileSize: Long = tableData["fileSize"]!!
    var offsetPixelBits: Long = tableData["offsetPixelBits"]!!
    //remember about sizeof of headers
    var bitCount: Long? = tableData["bitCount"]!!
    var sizeStructure: Long? = tableData["sizeStructure"]
    var colorUsed = tableData["colorUsed"]
    var version: Long? = tableData["version"]
    var height: Long? = tableData["height"]!!
    var width: Long? = tableData["width"]!!
    var compression: Long? = tableData["fileSize"]!!
    var ColorTable: MutableList<Byte> = ColorTable
}


class BmpModel16(rawData: MutableList<Byte>) : Model {
    override fun removeDrawer(drawer: Viewer) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun registerDrawer(drawer: Viewer) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun pushEvent() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

class BmpModel32(rawData: MutableList<Byte>) : Model {
    override fun registerDrawer(drawer: Viewer) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun pushEvent() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun removeDrawer(drawer: Viewer) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

class BmpModel8(rawData: MutableList<Byte>) : Model {
    var imageInfo: ImageInfo
    var image: BufferedImage? = null
    var pixelData: MutableList<Byte>

    private var drawers = mutableListOf<Viewer>()

    init {
        if (!(rawData[0].toChar() == 'B' && rawData[1].toChar() == 'M')) print("error")
        val tableData = HashMap<String, Long>()
        tableData.put("fileSize", convertBytesToLong(rawData, 2, 6))
        tableData.put("offsetPixelBits", convertBytesToLong(rawData, 10, 14))
        tableData.put("version", convertBytesToLong(rawData, 14, 18))
        tableData.put("width", convertBytesToLong(rawData, 18, 22))
        tableData.put("height", convertBytesToLong(rawData, 22, 26))
        tableData.put("bitCount", convertBytesToLong(rawData, 28, 30))
        tableData.put("compression", convertBytesToLong(rawData, 30, 34))
        tableData.put("colorUsed", convertBytesToLong(rawData, 46, 50))//other if need
        val startWith = when (tableData["version"]!!.toInt()) {//where start the color table
            12 -> 26
            40 -> when (tableData["compression"]!!.toInt()) {
                3 -> 66
                6 -> 70
                else -> 54
            }
            108 -> 122
            124 -> 138
            else -> throw UnsupportedClassVersionError("Something wrong with image")
        }
        var ColorTable = rawData.slice(startWith..tableData["colorUsed"]!!.toInt() * 4 + startWith - 1).toMutableList()
        imageInfo = ImageInfo(tableData, ColorTable)
        pixelData = rawData.subList(imageInfo.offsetPixelBits.toInt(), rawData.size)
        print(imageInfo.ColorTable.size)
    }

    override fun pushEvent() {
        convertToImage()
        for (drawer in drawers) drawer.handleEvent(image!!)
    }

    override fun registerDrawer(drawer: Viewer) {
        drawers.add(drawer)
    }

    override fun removeDrawer(drawer: Viewer) {
        drawers.remove(drawer)
    }

    fun convertToImage() {
        var pixel: Int
        var position: Int
        val height = imageInfo.height!!.toInt()
        val width = imageInfo.width!!.toInt()
        image = BufferedImage(width, height, 1)//only rgb
        for (i in height - 1 downTo 0)
            for (j in 0..width - 1) {
                position = convertBytesToLong(pixelData, (height - 1 - i) * width + j, (height - 1 - i) * width + j + 1).toInt()

                pixel = convertBytesToLong(imageInfo.ColorTable, position*4, position*4 + 4).toInt()//make mo understandable
                image!!.setRGB(j, i, pixel)
            }
    }

}

class BmpModel24(rawData: MutableList<Byte>) : Model {
    private var imageInfo: ImageInfo
    private var image: BufferedImage? = null
    private var pixelData: MutableList<Byte>
    private var drawers = mutableListOf<Viewer>()

    init {
        if (!(rawData[0].toChar() == 'B' && rawData[1].toChar() == 'M')) print("error")

        val tableData = HashMap<String, Long>()
        tableData.put("fileSize", convertBytesToLong(rawData, 2, 6))
        tableData.put("offsetPixelBits", convertBytesToLong(rawData, 10, 14))
        tableData.put("version", convertBytesToLong(rawData, 14, 18))
        tableData.put("width", convertBytesToLong(rawData, 18, 22))
        tableData.put("height", convertBytesToLong(rawData, 22, 26))
        tableData.put("bitCount", convertBytesToLong(rawData, 28, 30))
        tableData.put("compression", convertBytesToLong(rawData, 30, 34))
        tableData.put("colorUsed", convertBytesToLong(rawData, 46, 50))//other if need
        val startWith = when (tableData["version"]!!.toInt()) {//where start the color table
            12 -> 26
            40 -> when (tableData["compression"]!!.toInt()) {
                3 -> 66
                6 -> 70
                else -> 54
            }
            108 -> 122
            124 -> 138
            else -> throw UnsupportedClassVersionError("Something wrong with image")
        }
        var ColorTable = rawData.slice(startWith..tableData["colorUsed"]!!.toInt() * 4 + startWith - 1).toMutableList()
        println(ColorTable.size)
        imageInfo = ImageInfo(tableData, ColorTable)// color table could be depends on colorUsed and ither
        pixelData = rawData.subList(imageInfo.offsetPixelBits.toInt(), rawData.size)
    }

    override fun pushEvent() {
        for (drawer in drawers) drawer.handleEvent(image!!)
    }

    override fun registerDrawer(drawer: Viewer) {
        drawers.add(drawer)
    }

    override fun removeDrawer(drawer: Viewer) {
        drawers.remove(drawer)
    }

    fun convertToImage() {
        var pixel: Int
        val height = imageInfo.height!!.toInt()
        val width = imageInfo.width!!.toInt()
        image = BufferedImage(width, height, 3) //corresponds to 32 bit model argb pre
        for (i in height - 1 downTo 0)
            for (j in 0..width - 1) {//where 4 must be variable which
                pixel = convertBytesToLong(pixelData, (height - 1 - i) * (4 * width) + 4 * j, (height - 1 - i) * (4 * width) + 4 * j + 4).toInt()
                image!!.setRGB(j, i, pixel)
            }
    }
}


fun convertBytesToLong(array: MutableList<Byte>, startWith: Int, endWith: Int): Long {
    var value: Long = 0
    if (startWith + 1 == endWith) {
        value = array[startWith].toLong()
        if (value<0) return value+256 else return value
    }
    var tmp: Long
    for (i in endWith - 1 downTo startWith) {
        tmp = array[i].toLong()
        if (tmp < 0) tmp + 256
        value = value shl 8
        value += tmp
    }
    return value
}


//32-битная структура RGBQUAD. Применяется если в BITMAPINFO использована информационная структура версии 3, 4 или 5. В самой же структуре RGBQUAD указывается цвет в модели RGB в четырёх байтовых ячейках (все имеют WinAPI-тип BYTE): rgbBlue (синий), rgbGreen (зелёный), rgbRed (красный) и rgbReserved (зарезервирована и должна быть обнулена).

//16-битные индексы цветов (беззнаковые целые числа) в текущей логической палитре контекста устройства (системные объекты Windows GDI). Этот вид доступен только во время выполнения приложения. Формат BMP не поддерживает явное указание, что используется такая таблица и поэтому приложение само извещает WinAPI-функции об этом в специальных параметрах (как правило константой DIB_PAL_COLORS).
