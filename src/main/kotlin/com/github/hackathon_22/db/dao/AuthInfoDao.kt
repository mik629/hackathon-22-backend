package com.github.hackathon_22.db.dao

import com.github.hackathon_22.db.models.AuthInfo
import com.j256.ormlite.dao.Dao

class AuthInfoDao(
        private val delegateDAO: Dao<AuthInfo, String>
) {
    fun findBy(token: String): AuthInfo? {
        val infos = delegateDAO.queryForEq("token", token)
        return if (infos.isNotEmpty()) {
            infos.first()
        } else {
            null
        }
    }


    fun save(authInfo: AuthInfo) {
        delegateDAO.create(authInfo)
    }
}