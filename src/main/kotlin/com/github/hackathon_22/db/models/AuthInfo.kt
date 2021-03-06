package com.github.hackathon_22.db.models

import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.table.DatabaseTable

@DatabaseTable
data class AuthInfo(
        @DatabaseField(id = true)
        val token: String,
        @DatabaseField
        val fcmToken: String? = null,
        @DatabaseField
        val userId: Long
) {
        constructor() : this(token = "", userId = 0)
}