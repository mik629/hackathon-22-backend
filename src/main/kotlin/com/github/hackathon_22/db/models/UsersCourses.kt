package com.github.hackathon_22.db.models

import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.table.DatabaseTable

@DatabaseTable
data class UsersCourses(
        @DatabaseField(generatedId = true)
        val id: Long? = null,
        @DatabaseField
        val userId: Long,
        @DatabaseField
        val courseId: Long
) {
    constructor() : this(userId = 0, courseId = 0)
}