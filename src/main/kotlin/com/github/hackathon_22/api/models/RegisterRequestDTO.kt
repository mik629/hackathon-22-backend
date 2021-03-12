package com.github.hackathon_22.api.models

data class RegisterRequestDTO(
        val username: String,
        val pwd: String,
        val name: String,
        val isMentor: Boolean
)