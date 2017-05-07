package View

import java.awt.Canvas
import java.awt.Graphics
import java.awt.image.BufferedImage

import javax.swing.JFrame
import javax.swing.WindowConstants
import kotlin.system.exitProcess


/**
 * Created by nikita on 24.04.17.
 */

class ImageViewerCanvas(private var bufferedImage: BufferedImage) : Canvas() {
    override fun paint(g: Graphics) {
        super.paint(g)
        g.drawImage(bufferedImage, 0, 0, this)
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
        frame.defaultCloseOperation = WindowConstants.DISPOSE_ON_CLOSE
        frame.setSize(bufferedImage.width, bufferedImage.height)
        frame.add(ImageViewerCanvas(bufferedImage))
        frame.isVisible = true
    }

}



