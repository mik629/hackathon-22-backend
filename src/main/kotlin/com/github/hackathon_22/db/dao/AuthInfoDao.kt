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


    fun findFcmTokens(userIds: List<Long>): List<String?> =
            delegateDAO.queryBuilder()
                    .where()
                    .`in`("userId", userIds)
                    .query()
                    .map { it.fcmToken }

    fun save(authInfo: AuthInfo) {
        if (delegateDAO.queryForEq("token", authInfo.token) != null) {
            delegateDAO.update(authInfo)
        } else {
            delegateDAO.create(authInfo)
        }
    }
}