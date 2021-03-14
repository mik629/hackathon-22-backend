package com.github.hackathon_22.api.controllers

import com.github.hackathon_22.api.models.CourseDTO
import com.github.hackathon_22.api.models.UpdateCourseRequestDTO
import com.github.hackathon_22.api.models.fromCourse
import com.github.hackathon_22.api.models.toCourse
import com.github.hackathon_22.services.CoursesService
import com.github.hackathon_22.services.LoginService
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

private val logger = KotlinLogging.logger {}

@RestController
@RequestMapping("/courses")
class CoursesController(
        @Autowired val coursesService: CoursesService,
        @Autowired val loginService: LoginService
) {
    @PostMapping("update")
    fun updateCourse(
            @RequestHeader(TOKEN_HEADER, required = false) token: String?,
            @RequestBody updateCourseRequestDTO: UpdateCourseRequestDTO
    ): CourseDTO {
        if (token == null) {
            throw ResponseStatusException(HttpStatus.UNAUTHORIZED)
        }
        val authInfo = loginService.getAuthInfo(token)
        if (authInfo != null) {
            return fromCourse(coursesService.save(userId = authInfo.userId, course = updateCourseRequestDTO.toCourse()))
        } else {
            throw ResponseStatusException(HttpStatus.UNAUTHORIZED)
        }
    }


    @GetMapping("all")
    fun getAllCourses(
            @RequestHeader(TOKEN_HEADER, required = false) token: String?
    ): List<CourseDTO> {
        logger.error("The token is ${token ?: "null"}")
        if (token != null && loginService.getAuthInfo(token) != null) {
            return coursesService.getAllCourses()
                    .map { course -> fromCourse(course = course) }
        } else {
            throw ResponseStatusException(HttpStatus.UNAUTHORIZED)
        }
    }


    @GetMapping("favorite")
    fun getFavoriteCourses(
            @RequestHeader(TOKEN_HEADER, required = false) token: String?
    ): List<CourseDTO> {
        if (token == null) {
            throw ResponseStatusException(HttpStatus.UNAUTHORIZED)
        }
        val authInfo = loginService.getAuthInfo(token)
        if (authInfo != null) {
            return coursesService.getFavoriteCourses(userId = authInfo.userId)
                    .map { course -> fromCourse(course = course) }
        } else {
            throw ResponseStatusException(HttpStatus.UNAUTHORIZED)
        }
    }

    companion object {
        const val TOKEN_HEADER = "Token"
    }
}