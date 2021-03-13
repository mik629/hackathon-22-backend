package com.github.hackathon_22.api.models

import com.github.hackathon_22.WinnerAppConfig
import com.github.hackathon_22.db.models.Lecture

data class UpdateLectureRequestDTO(
        val id: Long? = null,
        val title: String,
        val youtubeUrl: String = "",
        val githubRepoUrl: String = "",
        val telegramChannel: String = "",
        val additionalMaterials: List<AdditionalMaterialDTO>,
        val imgUrl: String? = null,
        val tags: List<String>,
        val courseId: Long
)

fun UpdateLectureRequestDTO.toLecture(): Lecture =
        Lecture(
                title = title,
                youtubeUrl = youtubeUrl,
                githubRepoUrl = githubRepoUrl,
                telegramChannel = telegramChannel,
                additionalMaterials = WinnerAppConfig.objectMapper.writeValueAsString(additionalMaterials),
                imgUrl = imgUrl,
                tags = tags.joinToString(),
                courseId = courseId
        )