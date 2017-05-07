package Model

import View.Viewer
import java.awt.image.BufferedImage

/**
 * Created by nikita on 07.05.17.
 */


class BmpModel24(rawData: MutableList<Byte>) : Model, BmpModel(rawData) {
    private var drawers = mutableListOf<Viewer>()

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
        val height = imageInfo.height!!.toInt()
        val width = imageInfo.width!!.toInt()
        val align = when ((width) % 4) {//real Width
            3 -> width + 1
            2 -> width + 2
            1 -> width + 3
            else -> width
        }
        image = BufferedImage(width, height, 1)//rgb
        for (i in height - 1 downTo 0) {
            for (j in 0..width - 1) {
                pixel = convertBytesToLong(pixelData, (height - 1 - i) * 3 * align + 3 * j, (height - 1 - i) * (3 * align) + 3 * j + 3).toInt()
                image!!.setRGB(j, i, pixel)
            }
        }
    }

}

