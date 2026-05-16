package com.example.nimmaguru2

data class User(

    val name: String? = null,
    val role: String? = null,
    val subject: String? = null,

    // availability (days + time)
    val availability: Map<String, Any>? = null
)