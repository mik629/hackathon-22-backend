package com.github.hackathon_22.services

import com.github.hackathon_22.db.dao.AuthInfoDao
import com.github.hackathon_22.db.dao.UserDAO
import com.github.hackathon_22.db.exceptions.ExistingEntityException
import com.github.hackathon_22.db.models.AuthInfo
import com.github.hackathon_22.db.models.User
import com.github.hackathon_22.services.models.LoginResult
import org.springframework.beans.factory.annotation.Autowired

class RegisterService(
        @Autowired val userDAO: UserDAO,
        @Autowired val authInfoDao: AuthInfoDao
) {
    fun register(username: String, pwd: String, name: String, isMentor: Boolean): LoginResult {
        val existingUser = userDAO.findBy(username = username)
        if (existingUser == null) {
            val user = User(username = username, pwd = pwd, name = name, isMentor = isMentor)
            userDAO.save(user = user)
            val token = generateToken()
            authInfoDao.save(authInfo = AuthInfo(token = token, userId = user.id))
            return LoginResult(
                    success = true,
                    token = token,
                    user = user
            )
        } else {
            throw ExistingEntityException()
        }
    }
}