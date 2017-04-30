package Controller

//import  Model.BmpModel

import  Model.BmpModel24
import  Model.BmpModel8
import View.BmpViewer
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;

/**
 * Created by nikita on 24.04.17.
 */

/**
 * parse file
 *
 */

//class BitMapInfo {
//    var Header: HashMap<String, Long> = hashMapOf()
//    var PixelData: MutableList<Byte> = mutableListOf()
//}

interface Controller {
    fun validateFormat(): Boolean
    fun parseFile()
}

class BmpController(var path: String, val ImageViewer: BmpViewer) : Controller {

    override fun parseFile() {
        val path: Path = Paths.get(path)
        val rawData = Files.readAllBytes(path).toMutableList()
        when (Model.convertBytesToLong(rawData, 28, 30).toInt()) {
            8 -> {
                val model = BmpModel24(rawData)
                model.registerDrawer(ImageViewer)
                model.pushEvent()
            }
            25 -> {
                val model = BmpModel8(rawData)
                model.registerDrawer(ImageViewer)
                model.pushEvent()
            }
            32 -> TODO()
            16 -> TODO()
        }
    }

    override fun validateFormat(): Boolean = path.substring(path.lastIndex - 3, path.lastIndex + 1) == ".bmp"//specific! throw own exception

}

class GifController(var path: String) : Controller {
    override fun parseFile() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun validateFormat(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}

class WavController(var path: String) : Controller {
    override fun parseFile() {

    }

    override fun validateFormat(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}