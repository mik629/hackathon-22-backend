package com.github.hackathon_22.api.controllers

import com.github.hackathon_22.api.models.LectureDTO
import com.github.hackathon_22.api.models.UpdateLectureRequestDTO
import com.github.hackathon_22.api.models.fromLecture
import com.github.hackathon_22.api.models.toLecture
import com.github.hackathon_22.services.LecturesService
import com.github.hackathon_22.services.LoginService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/lectures")
class LecturesController(
        @Autowired val lecturesService: LecturesService,
        @Autowired val loginService: LoginService
) {
    @PostMapping("update")
    fun updateLecture(
            @RequestHeader(CoursesController.TOKEN_HEADER, required = false) token: String?,
            @RequestBody updateLectureRequestDTO: UpdateLectureRequestDTO
    ): LectureDTO {
        if (token == null) {
            throw throw ResponseStatusException(HttpStatus.UNAUTHORIZED)
        }
        val authInfo = loginService.getAuthInfo(token)
        if (authInfo != null) {
            return fromLecture(lecturesService.save(lecture = updateLectureRequestDTO.toLecture()))
        } else {
            throw ResponseStatusException(HttpStatus.UNAUTHORIZED)
        }
    }

    @GetMapping("all")
    fun getAll(
            @RequestHeader(CoursesController.TOKEN_HEADER, required = false) token: String?,
            @RequestParam courseId: Long
    ): List<LectureDTO> {
        if (token != null && loginService.getAuthInfo(token) != null) {
            return lecturesService.getAll()
                    .map { lecture -> fromLecture(lecture = lecture) }
        } else {
            throw ResponseStatusException(HttpStatus.UNAUTHORIZED)
        }
    }

    @GetMapping("by-id")
    fun getLectureById(
            @RequestHeader(CoursesController.TOKEN_HEADER, required = false) token: String?,
            @RequestParam lectureId: Long
    ): LectureDTO {
        if (token != null && loginService.getAuthInfo(token) != null) {
            return fromLecture(lecturesService.getById(id = lectureId))
        } else {
            throw ResponseStatusException(HttpStatus.UNAUTHORIZED)
        }
    }

}
