package Controller


/**
 * Created by nikita on 24.04.17.
 */

interface Controller {
    fun validateFormat(): Boolean
    fun parseFile()
}

class GifController(var path: String) : Controller {
    override fun parseFile(){
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun validateFormat(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}

class WavController(var path: String) : Controller {
    override fun parseFile() {
TODO()
    }

    override fun validateFormat(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}