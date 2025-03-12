package ru.progyammer.mimo.model

data class Profile(
    val name: String,
    val photoUrl: String,
    val description: String,
    val followersCount: Int,
    val subscriptionsCount: Int,
    val posts: MutableList<Post>,
)
