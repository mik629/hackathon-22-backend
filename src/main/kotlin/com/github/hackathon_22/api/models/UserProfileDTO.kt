package com.github.hackathon_22.api.models

data class UserProfileDTO(
        val username: String,
        val name: String,
        val mentor: Boolean
)