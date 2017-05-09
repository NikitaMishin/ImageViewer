package Model

import View.Viewer
import java.awt.image.BufferedImage

/**
 * Created by nikita on 07.05.17.
 */


class BmpModel24(rawData: MutableList<Byte>) : BmpModel(rawData) {
    private var drawers = mutableListOf<Viewer>()

    override fun convertToImage() {
        var pixel: Int
        val height = imageInfo.height!!.toInt()
        val width = imageInfo.width!!.toInt()
        val rowSize = ((imageInfo.bitCount!!.toInt() * width + 31) / 32) * 4 //round low?
        image = BufferedImage(width, height, 1)//rgb
        for (i in height - 1 downTo 0) {
            for (j in 0..width - 1) {
                pixel = convertBytesToLong(pixelData, (height - 1 - i) * (rowSize) + 3 * j, (height - 1 - i) * (rowSize) + 3 * j + 3).toInt()
                image!!.setRGB(j, i, pixel)
            }
        }
    }

}



