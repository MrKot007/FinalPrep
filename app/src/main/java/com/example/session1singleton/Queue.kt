package com.example.session1singleton

import java.util.LinkedList
import java.util.Queue

class MyQueue() {
    private var queue: Queue<Triple<String, String, Int>> = LinkedList()

    fun getSize() : Int {
        return queue.size
    }

    fun getElement() : Triple<String, String, Int> {
        return queue.element()
    }

    fun removeElement() {
        queue.remove()
    }
    fun fillQueue(elements: Collection<Triple<String, String, Int>>) {
        queue = LinkedList(elements)
    }
    fun fillQueueFromFile() {
        //пока не придмал, как сделать файл :)
        queue = LinkedList()
    }
}