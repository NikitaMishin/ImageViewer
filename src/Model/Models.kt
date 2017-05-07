package Model

import View.Viewer
import sun.awt.image.ImageFormatException
import java.awt.image.BufferedImage
import java.util.*
import kotlin.collections.HashMap


/**
 * Created by nikita on 24.04.17.
 */


interface Model {
    fun pushEvent()
    fun registerDrawer(drawer: Viewer)
    fun removeDrawer(drawer: Viewer)
}

class ImageInfo(tableData: HashMap<String, Long>, ColorTable: MutableList<Byte>) {
    //maybe make hash map without class
    var fileSize: Long = tableData["fileSize"]!!
    var offsetPixelBits: Long = tableData["offsetPixelBits"]!!
    var bitCount: Long? = tableData["bitCount"]!!
    var sizeStructure: Long? = tableData["sizeStructure"]
    var colorUsed = tableData["colorUsed"]
    var version: Long? = tableData["version"]
    var height: Long? = tableData["height"]!!
    var width: Long? = tableData["width"]!!
    var compression: Long? = tableData["compression"]!!
    var ColorTable: MutableList<Byte> = ColorTable
}

abstract class BmpModel(rawData: MutableList<Byte>) {
    var imageInfo: ImageInfo
    var image: BufferedImage? = null
    var pixelData: MutableList<Byte>
    protected val startWith: Int

    init {
        if (!(rawData[0].toChar() == 'B' && rawData[1].toChar() == 'M')) throw (ImageFormatException("It's wrong format"))

        val tableData = HashMap<String, Long>()
        tableData.put("fileSize", convertBytesToLong(rawData, 2, 6))
        tableData.put("offsetPixelBits", convertBytesToLong(rawData, 10, 14))
        tableData.put("version", convertBytesToLong(rawData, 14, 18))
        tableData.put("width", convertBytesToLong(rawData, 18, 22))
        tableData.put("height", convertBytesToLong(rawData, 22, 26))//could be with - -> order of rews
        tableData.put("bitCount", convertBytesToLong(rawData, 28, 30))
        tableData.put("compression", convertBytesToLong(rawData, 30, 34))
        tableData.put("colorUsed", convertBytesToLong(rawData, 46, 50))//other if need
        this.startWith = when (tableData["version"]!!.toInt()) {//where start the color table
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
        var ColorTable: MutableList<Byte> = mutableListOf()//put in init
        imageInfo = ImageInfo(tableData, ColorTable)
        pixelData = rawData.subList(imageInfo.offsetPixelBits.toInt(), rawData.size)
    }
}

fun convertBytesToLong(array: MutableList<Byte>, startWith: Int, endWith: Int): Long {
    var value = 0L
    var tmp: Long
    for (i in endWith - 1 downTo startWith) {
        tmp = array[i].toLong()
        if (tmp < 0) tmp += 256
        value = value shl 8
        value += tmp

    }
    return value
}

