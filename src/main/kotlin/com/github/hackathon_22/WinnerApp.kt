package com.github.hackathon_22

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication(scanBasePackages = ["com.github.hackathon_22"])
@EnableScheduling
class WinnerApp

fun main(args: Array<String>) {
	runApplication<WinnerApp>(*args)
}
