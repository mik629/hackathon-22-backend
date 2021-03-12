package com.github.hackathon_22.api.models

data class RegisterResponseDTO(
        val token: String,
        val userProfile: UserProfileDTO
)