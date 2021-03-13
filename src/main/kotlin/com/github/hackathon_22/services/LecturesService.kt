package com.github.hackathon_22.services

import com.github.hackathon_22.db.dao.LecturesDao
import com.github.hackathon_22.db.models.Lecture
import org.springframework.beans.factory.annotation.Autowired

class LecturesService(
        @Autowired val lecturesDao: LecturesDao
) {
    fun save(lecture: Lecture): Lecture =
            lecturesDao.save(lecture)


    fun getAll(): List<Lecture> =
            lecturesDao.getAll()

}