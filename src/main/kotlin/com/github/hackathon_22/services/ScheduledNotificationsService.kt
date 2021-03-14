package com.github.hackathon_22.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class ScheduledNotificationsService(
        @Autowired val messageService: MessageService,
        @Autowired val lecturesService: LecturesService
) {
    //    @Scheduled(cron = "0 0 10 * * *")
//    @Scheduled(cron = "0 */1 * * * *")
    @Scheduled(cron = "0 */15 * * * *") // hourly
    fun sendNotificationsIfNeeded() {
        for ((lecture, tokens) in lecturesService.findFcmTokensToBeNotified()) {
            messageService.sendNotifications(lecture = lecture, fcmTokens = tokens)
        }
    }
}