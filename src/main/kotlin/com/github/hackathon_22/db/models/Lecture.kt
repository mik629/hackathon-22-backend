package com.github.hackathon_22.db.models

import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.table.DatabaseTable

@DatabaseTable(tableName = "lectures")
data class Lecture(
        @DatabaseField(generatedId = true)
        val id: Long? = null,
        @DatabaseField
        val title: String,
        @DatabaseField
        val youtubeUrl: String = "",
        @DatabaseField
        val githubRepoUrl: String = "",
        @DatabaseField
        val telegramChannel: String = "",
        @DatabaseField
        val additionalMaterials: String,
        @DatabaseField
        val imgUrl: String? = null,
        @DatabaseField
        val tags: String,
        @DatabaseField
        val courseId: Long
) {
        constructor() : this(title = "", additionalMaterials = "", tags = "", courseId = 0)
}