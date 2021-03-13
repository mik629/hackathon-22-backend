package com.github.hackathon_22

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = ["com.github.hackathon_22"])
class WinnerApp

fun main(args: Array<String>) {
	runApplication<WinnerApp>(*args)
}
