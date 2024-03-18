package com.example.groupproject

data class Candidate(
    val name: String? = null,
    val title: String? = null,
    val photoUrl: String? = null
) {
    // Default constructor required for Firebase
    constructor() : this("", "", "")
}

