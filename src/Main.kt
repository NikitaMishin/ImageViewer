/**
 * Created by nikita on 24.04.17.
 */

import java.io.File
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;

import  Controller.BmpController
import View.Viewer
import View.BmpViewer
import View.GifViewer

fun main(args: Array<String>) {

    var bmpViewer = BmpViewer()
    var gifViewer = GifViewer()
    var path: String = "exit"
    var Images: MutableList<Model.Model>

    link@ do {
        path = readLine()!!
        if (path == "exit") break@link
        if (path.lastIndex < 4) continue
        when (path.substring(path.lastIndex - 3, path.lastIndex + 1)) {
            ".bmp" -> {
                BmpController(path, bmpViewer).parseFile()
            }
            ".gif"->{
                TODO()
            }
        }
    } while (true)
}