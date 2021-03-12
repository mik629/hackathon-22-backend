package com.github.hackathon_22.api.models

data class LoginResponseDTO(
        val userProfile: UserProfileDTO,
        val token: String
)