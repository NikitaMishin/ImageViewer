package View

import Controller.Controller

import java.awt.Canvas
import java.awt.Graphics
import java.awt.image.BufferedImage

import javax.swing.JFrame


/**
 * Created by nikita on 24.04.17.
 */

class ImageViewerCanvas(private var bufferedImage: BufferedImage) : Canvas() {
    override fun paint(g: Graphics) {
        super.paint(g)
        for (i in 0..bufferedImage.height - 1)
            for (j in 0..bufferedImage.width - 1)
                g.drawImage(bufferedImage, i, j, this)
    }
}

interface Viewer {
    fun handleEvent(bufferedImage: BufferedImage)
    fun drawImage(bufferedImage: BufferedImage)
}


class GifViewer() : Viewer {
    override fun handleEvent(bufferedImage: BufferedImage) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun drawImage(bufferedImage: BufferedImage) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}


class BmpViewer() : Viewer, Canvas() {
    override fun handleEvent(bufferedImage: BufferedImage) = drawImage(bufferedImage)//make update and rewrite buffered image

    override fun drawImage(bufferedImage: BufferedImage) {
        val frame = JFrame()
        frame.setSize(bufferedImage.width, bufferedImage.height)
        frame.add(ImageViewerCanvas(bufferedImage))
        frame.isVisible = true
    }//not good input data!!

}


