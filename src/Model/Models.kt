package Model

import View.Viewer
import java.awt.Color
import java.awt.image.BufferedImage
import kotlin.collections.HashMap


/**
 * Created by nikita on 24.04.17.
 */

interface Model {
    fun pushEvent()
    fun registerDrawer(drawer: Viewer)
    fun removeDrawer(drawer: Viewer)
}

class ImageInfo(tableData: HashMap<String, Long>, ColorTable: List<Byte>) {
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
    var ColorTable: List<Byte> = ColorTable
}


class BmpModel8(rawData: MutableList<Byte>) : Model {
    init {

    }

    var drawers = mutableListOf<Viewer>()
    override fun pushEvent() {
        TODO()
        var PixelData = mutableListOf<Byte>()
        // for (drawer in drawers) drawer.handleEvent(PixelData)
    }

    override fun registerDrawer(drawer: Viewer) {
        drawers.add(drawer)
    }

    override fun removeDrawer(drawer: Viewer) {
        drawers.remove(drawer)
    }

}

class BmpModel24(rawData: MutableList<Byte>) : Model {
    var imageInfo: ImageInfo
    var image: BufferedImage? = null
    var pixelData: MutableList<Byte>

    init {//specify

        if (!(rawData[0].toChar() == 'B' && rawData[1].toChar() == 'M')) print("error")

        var tableData = HashMap<String, Long>()
        tableData.put("fileSize", convertBytesToLong(rawData, 2, 6))
        tableData.put("offsetPixelBits", convertBytesToLong(rawData, 10, 14))
        println()
        println(convertBytesToLong(rawData, 10, 14))
        tableData.put("width", convertBytesToLong(rawData, 18, 22))
        tableData.put("height", convertBytesToLong(rawData, 22, 26))
        tableData.put("bitCount", convertBytesToLong(rawData, 28, 30))
        tableData.put("compression", convertBytesToLong(rawData, 30, 34))
        tableData.put("colorUsed", convertBytesToLong(rawData, 46, 50))
        tableData.put("version", convertBytesToLong(rawData, 14, 18))
        print(tableData["height"]!!)
        var startWith = when (tableData["version"]!!.toInt()) {
            12 -> 26
            40 -> when (tableData["compression"]!!.toInt()) {
                3 -> 66
                6 -> 70
                else -> 54
            }
            108 -> 122
            124 -> 138
            else -> -100//raise error
        }

        var ColorTable = rawData.slice(startWith..tableData["colorUsed"]!!.toInt() * 4 + startWith - 1)//check include and exclude
        imageInfo = ImageInfo(tableData, ColorTable)// color table could be depends on colorUsed and ither
        pixelData = rawData.subList(imageInfo.offsetPixelBits.toInt(), rawData.size)
    }

    var drawers = mutableListOf<Viewer>()
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
        print("fg")
        var pixel: Int
        val height = imageInfo.height!!.toInt()
        val width = imageInfo.width!!.toInt()
        image = BufferedImage(width, height, 1)
        for (i in height - 1 downTo 0)
            for (j in 0..width - 1) {//where 4 must be variable which
                pixel = convertBytesToLong(pixelData, (height - 1 - i) * (4 * width) + 4 * j, (height - 1 - i)*(4*width) + 4 * j + 4).toInt()
                image!!.setRGB(j, i, pixel)
            }

    }
    
}

fun convertBytesToLong(array: MutableList<Byte>, startWith: Int, endWith: Int): Long {
    var value: Long = 0
    var tmp: Long
    for (i in endWith - 1 downTo startWith) {
        tmp = array[i].toLong()
        if (tmp < 0) tmp + 256
        value = value shl 8
        value += tmp
    }
    return value
}