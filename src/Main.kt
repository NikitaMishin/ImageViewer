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

    var Images: MutableList<Model.Model>

    var path = "smp"
//    print(petux[1].toChar())
    var example = BmpController("/home/nikita/Desktop/lena512.bmp", bmpViewer)
    if (!example.validateFormat()) print("df")
    example.parseFile()
   // example.


}