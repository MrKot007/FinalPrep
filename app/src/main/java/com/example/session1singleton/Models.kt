package com.example.session1singleton

data class QueueJson(
    val elements: List<QueueElement>
)
data class QueueElement(
    val id: Int,
    val heading: String,
    val paragraph: String,
    val imageLink: Int
)