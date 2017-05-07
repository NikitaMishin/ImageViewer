package Controller

import Model.BmpModel16
import Model.BmpModel24
import Model.BmpModel32
import Model.BmpModel8
import View.BmpViewer
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

/**
 * Created by nikita on 07.05.17.
 */

class BmpController(var path: String, val ImageViewer: BmpViewer) : Controller {

    override fun parseFile() {
        val rawData: MutableList<Byte>
        val path: Path = Paths.get(path)
        try {
            rawData = Files.readAllBytes(path).toMutableList()
        } catch (e: java.nio.file.NoSuchFileException) {
            println("File don't exist")
            return
        }
        when (Model.convertBytesToLong(rawData, 28, 30).toInt()) {
            24 -> {
                val model = BmpModel24(rawData)
                model.registerDrawer(ImageViewer)
                model.pushEvent()
            }
            8 -> {
                val model = BmpModel8(rawData)
                model.registerDrawer(ImageViewer)
                model.pushEvent()
            }
            32 -> {
                val model = BmpModel32(rawData)
                model.registerDrawer(ImageViewer)
                model.pushEvent()
            }
            16 -> {
                val model = BmpModel16(rawData)
                model.registerDrawer(ImageViewer)
                model.pushEvent()
            }
            4 -> TODO()
            2 -> TODO()
            1 -> TODO()
        }
    }

    override fun validateFormat(): Boolean = path.substring(path.lastIndex - 3, path.lastIndex + 1) == ".bmp"//specific! throw own exception
}
