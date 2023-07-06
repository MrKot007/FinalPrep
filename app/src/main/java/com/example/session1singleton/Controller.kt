package com.example.session1singleton

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File

class Controller {
    val file = File(pathToQueueFile)

    init {
        if (!file.exists()) {
            file.createNewFile()
            file.saveData(Json.encodeToString(dataQueue))
        }
    }

    fun getSize() : Int {
        return getDataFromFile().size
    }

    fun getElement() : Triple<String, String, Int> {
        return getDataFromFile()[0]
    }

    fun deleteElement() {
        val data = getDataFromFile()
        file.saveData(Json.encodeToString(data.subList(1, data.lastIndex)))
    }

    private fun getDataFromFile() : List<Triple<String, String, Int>> {
        return Json.decodeFromString(file.getData())
    }
}
