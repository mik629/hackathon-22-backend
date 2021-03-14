package com.github.hackathon_22.services

import com.github.hackathon_22.db.dao.AuthInfoDao
import com.github.hackathon_22.db.dao.LecturesDao
import com.github.hackathon_22.db.dao.UsersCoursesDao
import com.github.hackathon_22.db.models.Lecture
import org.springframework.beans.factory.annotation.Autowired
import java.util.concurrent.TimeUnit

class LecturesService(
        @Autowired val lecturesDao: LecturesDao,
        @Autowired val usersCoursesDao: UsersCoursesDao,
        @Autowired val authInfoDao: AuthInfoDao
) {
    fun save(lecture: Lecture): Lecture {
        lecturesDao.save(lecture)
        return lecture
    }

    fun getById(id: Long): Lecture? =
            lecturesDao.findById(id)

    fun getAll(): List<Lecture> =
            lecturesDao.getAll()

    fun findFcmTokensToBeNotified(): Map<Lecture, List<String?>> {
        val lectureToTokens = HashMap<Lecture, List<String?>>()
        for (lecture in getAll()) {
            val timeDiff = lecture.startTimestamp - System.currentTimeMillis()
            if (timeDiff > 0 && timeDiff < TimeUnit.MILLISECONDS.convert(15, TimeUnit.MINUTES)) {
                val userIds = usersCoursesDao.findUserIds(courseId = lecture.courseId)
                lectureToTokens[lecture] = authInfoDao.findFcmTokens(userIds)
            }
        }
        return lectureToTokens
    }

}