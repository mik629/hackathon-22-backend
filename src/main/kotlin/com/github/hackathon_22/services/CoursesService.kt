package com.github.hackathon_22.services

import com.github.hackathon_22.db.dao.CoursesDao
import com.github.hackathon_22.db.models.Course
import org.springframework.beans.factory.annotation.Autowired

class CoursesService(
        @Autowired val coursesDao: CoursesDao,
) {
    fun save(userId: Long, course: Course): Course =
            coursesDao.save(userId = userId, course = course)

    fun getAllCourses(): List<Course> =
            coursesDao.getAll()

    fun getFavoriteCourses(userId: Long): List<Course> =
            coursesDao.getFavorite(userId = userId)
}