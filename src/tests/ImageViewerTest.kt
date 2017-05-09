import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.io.File
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import javax.imageio.ImageIO.*

/**
 * Created by nikita on 09.05.17.
 */
internal class ImageViewerTest {
    @Test
    fun image24bit() {
        val name = "/home/nikita/bmp/beaut_24bit.bmp"
        val file = File("/home/nikita/bmp/beaut_24bit.bmp")
        val test = read(file)
        val path: Path = Paths.get(name)
        var model = Model.BmpModel24(Files.readAllBytes(path).toMutableList())
        model.convertToImage()
        val width = test.width
        val height = test.height
        for (j in 0..width - 1)
            for (i in 0..height - 1)
                assertEquals(test.getRGB(j, i), model.image!!.getRGB(j, i))

    }

    @Test
    fun image24bit2() {
        val name = "/home/nikita/bmp/dodj_24bit.bmp"
        val file = File("/home/nikita/bmp/dodj_24bit.bmp")
        val test = read(file)
        val path: Path = Paths.get(name)
        var model = Model.BmpModel24(Files.readAllBytes(path).toMutableList())
        model.convertToImage()
        val width = test.width
        val height = test.height
        for (j in 0..width - 1)
            for (i in 0..height - 1)
                assertEquals(test.getRGB(j, i), model.image!!.getRGB(j, i))
    }

    @Test
    fun image24bit3() {
        val name = "/home/nikita/bmp/per_24bit.bmp"
        val file = File("/home/nikita/bmp/per_24bit.bmp")
        val test = read(file)
        val path: Path = Paths.get(name)
        var model = Model.BmpModel24(Files.readAllBytes(path).toMutableList())
        model.convertToImage()
        val width = test.width
        val height = test.height
        for (j in 0..width - 1)
            for (i in 0..height - 1)
                assertEquals(test.getRGB(j, i), model.image!!.getRGB(j, i))
    }

    @Test
    fun image24bit4() {
        val name = "/home/nikita/bmp/haker_24bit.bmp"
        val file = File("/home/nikita/bmp/haker_24bit.bmp")
        val test = read(file)
        val path: Path = Paths.get(name)
        var model = Model.BmpModel24(Files.readAllBytes(path).toMutableList())
        model.convertToImage()
        val width = test.width
        val height = test.height
        for (j in 0..width - 1)
            for (i in 0..height - 1)
                assertEquals(test.getRGB(j, i), model.image!!.getRGB(j, i))

    }

    @Test
    fun image24bit5() {
        val name = "/home/nikita/bmp/love_24bit.bmp"
        val file = File("/home/nikita/bmp/love_24bit.bmp")
        val test = read(file)
        val path: Path = Paths.get(name)
        var model = Model.BmpModel24(Files.readAllBytes(path).toMutableList())
        model.convertToImage()
        val width = test.width
        val height = test.height
        for (j in 0..width - 1)
            for (i in 0..height - 1)
                assertEquals(test.getRGB(j, i), model.image!!.getRGB(j, i))
    }

    @Test
    fun image24bit6() {
        val name = "/home/nikita/bmp/warrios_24bit.bmp"
        val file = File("/home/nikita/bmp/warrios_24bit.bmp")
        val test = read(file)
        val path: Path = Paths.get(name)
        var model = Model.BmpModel24(Files.readAllBytes(path).toMutableList())
        model.convertToImage()
        val width = test.width
        val height = test.height
        for (j in 0..width - 1)
            for (i in 0..height - 1)
                assertEquals(test.getRGB(j, i), model.image!!.getRGB(j, i))
    }

    @Test
    fun image8bit1() {
        val name = "/home/nikita/bmp/bogts_8bit.bmp"
        val file = File("/home/nikita/bmp/bogts_8bit.bmp")
        val test = read(file)
        val path: Path = Paths.get(name)
        var model = Model.BmpModel8(Files.readAllBytes(path).toMutableList())
        model.convertToImage()
        val width = test.width
        val height = test.height
        for (j in 0..width - 1)
            for (i in 0..height - 1)
                assertEquals(test.getRGB(j, i), model.image!!.getRGB(j, i))
    }

    @Test
    fun image8bit2() {
        val name = "/home/nikita/Desktop/bmp/man.bmp"
        val file = File("/home/nikita/Desktop/bmp/man.bmp")
        val test = read(file)
        val path: Path = Paths.get(name)
        var model = Model.BmpModel8(Files.readAllBytes(path).toMutableList())
        model.convertToImage()
        val width = test.width
        val height = test.height
        for (j in 0..width - 1)
            for (i in 0..height - 1)
                assertEquals(test.getRGB(j, i), model.image!!.getRGB(j, i))
    }


    @Test
    fun image8bit3() {
        val name = "/home/nikita/bmp/hm_8bit.bmp"
        val file = File("/home/nikita/bmp/hm_8bit.bmp")
        val test = read(file)
        val path: Path = Paths.get(name)
        var model = Model.BmpModel8(Files.readAllBytes(path).toMutableList())
        model.convertToImage()
        val width = test.width
        val height = test.height
        for (j in 0..width - 1)
            for (i in 0..height - 1)
                assertEquals(test.getRGB(j, i), model.image!!.getRGB(j, i))
    }


    @Test
    fun image8bit4() {
        val name = "/home/nikita/bmp/freebsd2_8bit.bmp"
        val file = File("/home/nikita/bmp/freebsd2_8bit.bmp")
        val test = read(file)
        val path: Path = Paths.get(name)
        var model = Model.BmpModel8(Files.readAllBytes(path).toMutableList())
        model.convertToImage()
        val width = test.width
        val height = test.height
        for (j in 0..width - 1)
            for (i in 0..height - 1)
                assertEquals(test.getRGB(j, i), model.image!!.getRGB(j, i))
    }

}


