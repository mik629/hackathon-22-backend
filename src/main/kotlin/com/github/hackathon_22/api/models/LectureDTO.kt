package com.github.hackathon_22.api.models

import com.fasterxml.jackson.module.kotlin.readValue
import com.github.hackathon_22.WinnerAppConfig
import com.github.hackathon_22.db.models.Lecture

data class LectureDTO(
        val id: Long,
        val title: String,
        val youtubeUrl: String = "",
        val githubRepoUrl: String = "",
        val telegramChannel: String = "",
        val additionalMaterials: List<AdditionalMaterialDTO>,
        val imgUrl: String? = null,
        val tags: List<String>,
        val courseId: Long
)

fun fromLecture(lecture: Lecture): LectureDTO {
    val additionalMaterials: List<AdditionalMaterialDTO> = WinnerAppConfig.objectMapper.readValue(lecture.additionalMaterials)
    return LectureDTO(
            id = lecture.id!!,
            title = lecture.title,
            youtubeUrl = lecture.youtubeUrl,
            githubRepoUrl = lecture.githubRepoUrl,
            telegramChannel = lecture.telegramChannel,
            additionalMaterials = additionalMaterials,
            imgUrl = lecture.imgUrl,
            tags = lecture.tags.split(", "),
            courseId = lecture.courseId
    )
}
