package com.github.hackathon_22.services.models

import com.github.hackathon_22.db.models.User

data class LoginResult(
        val success: Boolean,
        val token: String,
        val user: User?
)