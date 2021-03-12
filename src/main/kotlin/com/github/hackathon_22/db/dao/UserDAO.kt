package com.github.hackathon_22.db.dao

import com.github.hackathon_22.db.models.User
import com.j256.ormlite.dao.Dao

class UserDAO(
        private val delegateDAO: Dao<User, Long>
) {
    fun findBy(username: String): User =
            delegateDAO.queryForEq(
                    "username", username
            ).first()

    fun save(user: User) {
        delegateDAO.create(user)
    }
}