package com.github.hackathon_22.services

import com.github.hackathon_22.db.dao.AuthInfoDao
import com.github.hackathon_22.db.dao.UsersDao
import com.github.hackathon_22.db.models.AuthInfo
import com.github.hackathon_22.services.models.LoginResult
import org.springframework.beans.factory.annotation.Autowired

class LoginService(
        @Autowired val userDAO: UsersDao,
        @Autowired val authInfoDao: AuthInfoDao
) {
    fun login(username: String, pwd: String): LoginResult {
        val user = userDAO.findBy(username = username)
        val success = user?.pwd == pwd
        var token = ""
        if (success) {
            token = generateToken()
            authInfoDao.save(
                    AuthInfo(token = token, userId = user!!.id)
            )
        }
        return LoginResult(
                success = success,
                token = token,
                user = user
        )
    }

    fun getAuthInfo(token: String): AuthInfo? =
            authInfoDao.findBy(token = token)

    fun updateAuthInfo(token: String, fcmToken: String, userId: Long) {
        authInfoDao.save(
                AuthInfo(
                        token = token,
                        fcmToken = fcmToken,
                        userId = userId
                )
        )
    }
}