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
    //initialize all controllers
//initialize all class models- consist of raw images
    // viewer
    // var path:Path=Paths.get("/home/nikita/Desktop/lena512.bmp")
    // var petux=Files.readAllBytes(path)
// have \
    var bmpViewer = BmpViewer()
    var gifViewer = GifViewer()
    var path: String = "exit"
    var Images: MutableList<Model.Model>

    /*
    possible commands
    open filename
    close
     */


   link@ do {
       println("nema")
        path = readLine()!!
        when (path.substring(path.lastIndex - 3, path.lastIndex + 1)) {
            ".bmp" -> {
                BmpController(path, bmpViewer).parseFile()//

            }
            "exit" -> break@link
        }

    } while (true)
}