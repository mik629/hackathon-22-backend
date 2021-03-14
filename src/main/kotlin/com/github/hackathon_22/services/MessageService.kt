package com.github.hackathon_22.services

import com.github.hackathon_22.db.models.Lecture
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.Message
import com.google.firebase.messaging.Notification
import org.springframework.beans.factory.annotation.Autowired

class MessageService(
        @Autowired val messenger: FirebaseMessaging
) {
    fun sendNotifications(lecture: Lecture, fcmTokens: List<String?>) {
        for (fcmToken in fcmTokens) {
            messenger.send(
                    Message.builder()
                            .setToken(fcmToken)
                            .setNotification(
                                    Notification.builder()
                                            .setTitle(lecture.title)
//                                            .setBody(lecture.youtubeUrl)
                                            .build()
                            )
                            .putData("click_action", "https://www.bestapp/show?courseId=${lecture.courseId}&lectureId=${lecture.id}")
                            .build()

            )
        }
    }
}