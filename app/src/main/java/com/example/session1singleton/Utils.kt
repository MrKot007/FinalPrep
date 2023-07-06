package com.example.session1singleton

import android.os.Environment
import com.google.common.io.Files
import java.io.File
import java.io.FileOutputStream


val pathToQueueFile = Environment.getExternalStorageDirectory().toString() + "/queue.txt"

fun File.saveData(data: String) {
    val stream = FileOutputStream(this)
    stream.use {
        stream -> stream.write(data.toByteArray())
    }
}

fun File.getData() : String {
    return Files.asCharSource(this, Charsets.UTF_8).read()
}

val dataQueue = listOf(
    Triple("Quick Delivery At Your Doorstep", "Enjoy quick pick-up and delivery to your destination", R.drawable.boarding1),
    Triple("Flexible Payment", "Different modes of payment either before and after delivery without stress", R.drawable.boarding2),
    Triple("Real-time Tracking", "Track your packages/items from the comfort of your home till final destination", R.drawable.boarding3)
)
