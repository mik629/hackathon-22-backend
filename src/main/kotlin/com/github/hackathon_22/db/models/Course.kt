package com.github.hackathon_22.db.models

import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.table.DatabaseTable

@DatabaseTable(tableName = "courses")
data class Course(
        @DatabaseField(generatedId = true)
        val id: Long? = null,
        @DatabaseField
        val title: String,
        @DatabaseField
        val shortDescription: String? = null,
        @DatabaseField
        val fullDescription: String? = null,
        @DatabaseField
        val imgUrl: String? = null,
        @DatabaseField
        val tags: String? = "",
        @DatabaseField
        val isSubscribed: Boolean = false
)