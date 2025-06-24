package com.solo.core.domain.model

data class User(
    val id: String = "",
    val firstName: String = "",
    val lastName: String = "",
    val email: String = "",
    val jobTitle: String = "",
    val userInterests: List<String> = emptyList()
)
