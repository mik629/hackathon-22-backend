package com.github.hackathon_22.db.models

import com.github.hackathon_22.api.models.UserProfileDTO
import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.table.DatabaseTable

@DatabaseTable
data class User(
        @DatabaseField(generatedId = true)
        val id: Long = 0,
        @DatabaseField
        val username: String,
        @DatabaseField
        val pwd: String,
        @DatabaseField
        val name: String,
        @DatabaseField
        val isMentor: Boolean
) {
    constructor() : this(id = 0, username = "", pwd = "", name = "", isMentor = false)
}

fun User.toUserProfileDTO(): UserProfileDTO =
        UserProfileDTO(
                username = username,
                name = name,
                isMentor = isMentor
        )