package ru.progyammer.mimo.model

data class Post(
    val id: Int,
    val photoUrl: String,
    var likesCount: Int,
    var commentsCount: Int,
    val description: String,
)

