package com.github.hackathon_22

import com.fasterxml.jackson.databind.ObjectMapper
import com.github.hackathon_22.db.dao.AuthInfoDao
import com.github.hackathon_22.db.dao.CoursesDao
import com.github.hackathon_22.db.dao.UsersDao
import com.github.hackathon_22.db.models.AuthInfo
import com.github.hackathon_22.db.models.Course
import com.github.hackathon_22.db.models.User
import com.github.hackathon_22.db.models.UsersCourses
import com.github.hackathon_22.services.CoursesService
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
    fun userDao(): UsersDao =
            UsersDao(
                    delegateDAO = createDao(clazz = User::class.java)
            )

    @Bean
    fun authInfoDAO(): AuthInfoDao =
            AuthInfoDao(delegateDAO = createDao(clazz = AuthInfo::class.java))

    @Bean
    fun coursesDao(): CoursesDao =
            CoursesDao(
                    coursesDelegateDAO = createDao(clazz = Course::class.java),
                    userCoursesDelegateDao = createDao(clazz = UsersCourses::class.java)
            )

    @Bean
    fun loginService(
            userDAO: UsersDao,
            authInfoDao: AuthInfoDao
    ): LoginService =
            LoginService(userDAO = userDAO, authInfoDao = authInfoDao)

    @Bean
    fun registerService(
            userDAO: UsersDao,
            authInfoDao: AuthInfoDao
    ): RegisterService =
            RegisterService(userDAO = userDAO, authInfoDao = authInfoDao)

    @Bean
    fun coursesService(): CoursesService =
            CoursesService(
                    coursesDao = CoursesDao(
                            coursesDelegateDAO = createDao(clazz = Course::class.java),
                            userCoursesDelegateDao = createDao(clazz = UsersCourses::class.java)
                    )
            )

    private fun <T, I> createDao(clazz: Class<T>): Dao<T, I> {
        val connectionSource = JdbcConnectionSource(url)
        val orm: Dao<T, I> = DaoManager.createDao(
                connectionSource,
                clazz
        )
        TableUtils.createTableIfNotExists(connectionSource, clazz)
        return orm
    }

    companion object {
        val objectMapper: ObjectMapper = ObjectMapper()
    }
}