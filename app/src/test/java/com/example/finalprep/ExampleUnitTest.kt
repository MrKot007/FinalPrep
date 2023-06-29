package com.example.finalprep

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun sizeCheck() {
        val controller = Controller()
        val list = listOf(
            Triple("firstHeading", "firstText", R.drawable.boarding1),
            Triple("secondHeading", "secondText", R.drawable.boarding2),
            Triple("thirdHeading", "secondText", R.drawable.boarding3))
        for (i in list) {
            controller.addElement(i)
        }
        val sz = controller.getSize()
        controller.deleteElement()
        assertEquals(controller.getSize(), sz-1)
    }
    @Test
    fun orderCheck() {
        val controller = Controller()
        val list = listOf(
            Triple("firstHeading", "firstText", R.drawable.boarding1),
            Triple("secondHeading", "secondText", R.drawable.boarding2),
            Triple("thirdHeading", "secondText", R.drawable.boarding3)
        )
        for (i in list) {
            controller.addElement(i)
        }
        for (i in list) {
            assertEquals(controller.getElement(), i)
            controller.deleteElement()
        }
    }

}