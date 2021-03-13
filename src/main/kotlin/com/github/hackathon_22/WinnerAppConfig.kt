package com.github.hackathon_22

import com.fasterxml.jackson.databind.ObjectMapper
import com.github.hackathon_22.db.dao.AuthInfoDao
import com.github.hackathon_22.db.dao.UserDAO
import com.github.hackathon_22.db.models.AuthInfo
import com.github.hackathon_22.db.models.User
import com.github.hackathon_22.services.LoginService
import com.github.hackathon_22.services.RegisterService
import com.j256.ormlite.dao.Dao
import com.j256.ormlite.dao.DaoManager
import com.j256.ormlite.jdbc.JdbcConnectionSource
import com.j256.ormlite.table.TableUtils
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class WinnerAppConfig {
    @Value(value = "\${sqlite.backend.url}")
    lateinit var url: String

    @Bean
    fun objectMapper(): ObjectMapper =
            ObjectMapper()

    @Bean
    fun userDao(): UserDAO =
            UserDAO(
                    delegateDAO = createDao(clazz = User::class.java)
            )

    @Bean
    fun authInfoDAO(): AuthInfoDao =
            AuthInfoDao(delegateDAO = createDao(clazz = AuthInfo::class.java))

    @Bean
    fun loginService(
            userDAO: UserDAO,
            authInfoDao: AuthInfoDao
    ): LoginService =
            LoginService(userDAO = userDAO, authInfoDao = authInfoDao)

    @Bean
    fun registerService(
            userDAO: UserDAO,
            authInfoDao: AuthInfoDao
    ): RegisterService =
            RegisterService(userDAO = userDAO, authInfoDao = authInfoDao)

    private fun <T, I> createDao(clazz: Class<T>): Dao<T, I> {
        val connectionSource = JdbcConnectionSource(url)
        val articleORM: Dao<T, I> = DaoManager.createDao(
                connectionSource,
                clazz
        )
        TableUtils.createTableIfNotExists(connectionSource, clazz)
        return articleORM
    }

    companion object {
        val objectMapper: ObjectMapper = ObjectMapper()
    }
}