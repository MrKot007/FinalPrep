package com.example.session_5

data class DriverItem(
    val name: String,
    val id: String,
    val rating: Int,
    val avatar: String
)
data class DriverData(
    val name: String,
    val rating: Int,
    val registrationNumber: String,
    val carModel: String,
    val gender: String,
    val reviews: List<ModelReview>
)
data class ModelReview(
    val userName: String,
    val message: String,
    val rating: Int,
    val avatar: String
)