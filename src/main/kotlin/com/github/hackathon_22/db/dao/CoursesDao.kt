package com.github.hackathon_22.db.dao

import com.github.hackathon_22.db.models.Course
import com.github.hackathon_22.db.models.UsersCourses
import com.j256.ormlite.dao.Dao

class CoursesDao(
        private val coursesDelegateDAO: Dao<Course, Long>,
        private val userCoursesDelegateDao: Dao<UsersCourses, Long>
) {
    fun save(userId: Long, course: Course): Course {
        if (course.id != null) {
            coursesDelegateDAO.update(course)
        } else {
            coursesDelegateDAO.create(course)
        }
        if (course.subscribed) {
            userCoursesDelegateDao.create(
                    UsersCourses(
                            userId = userId, courseId = course.id!!
                    )
            )
        }
        return findById(course.id!!)
    }

    private fun findById(id: Long): Course =
            coursesDelegateDAO.queryForId(id)

    fun getAll(): List<Course> =
            coursesDelegateDAO.queryForAll()

    fun getFavorite(userId: Long): List<Course> {
        val courseIds = userCoursesDelegateDao.queryForEq("userId", userId)
                .map { usersCourses -> usersCourses.courseId }
        return coursesDelegateDAO.queryBuilder()
                .where()
                .`in`("id", courseIds)
                .and()
                .eq("subscribed", true)
                .query()
    }
}