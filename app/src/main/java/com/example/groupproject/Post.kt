package com.example.groupproject

data class Post(
    val postId: String = "",
    val userId: String = "",
    val content: String = "",
    val timestamp: Long = 0
) {
    // No-argument constructor required by Firebase
    constructor() : this("","","",0)
}