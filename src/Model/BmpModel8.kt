package Model

import View.Viewer
import java.awt.image.BufferedImage

/**
 * Created by nikita on 07.05.17.
 */

class BmpModel8(rawData: MutableList<Byte>) : Model, BmpModel(rawData) {
    init {
        when (imageInfo.colorUsed!!.toInt()) {
            0 -> imageInfo.ColorTable = rawData.slice(startWith..Math.pow(2.0, imageInfo.bitCount!!.toDouble()).toInt() * 4 + startWith - 1).toMutableList()
            else -> imageInfo.ColorTable = rawData.slice(startWith..imageInfo.colorUsed!!.toInt() * 4 + startWith - 1).toMutableList()
        }
    }

    override fun convertToImage() {
        var pixel: Int
        var position: Int
        val height = imageInfo.height!!.toInt()
        val width = imageInfo.width!!.toInt()
        val align = when ((width) % 4) {
            3 -> width + 1
            2 -> width + 2
            1 -> width + 3
            else -> width
        }
        image = BufferedImage(width, height, 1)//only rgb
        for (i in height - 1 downTo 0)//depends on version!!//
            for (j in 0..width - 1) {
                position = convertBytesToLong(pixelData, (height - 1 - i) * align + j, (height - 1 - i) * align + j + 1).toInt()
                pixel = convertBytesToLong(imageInfo.ColorTable, position * 4, position * 4 + 4).toInt()
                image!!.setRGB(j, i, pixel)
            }
    }

}