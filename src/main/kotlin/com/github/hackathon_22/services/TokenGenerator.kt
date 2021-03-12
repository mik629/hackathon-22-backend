package com.github.hackathon_22.services

import java.math.BigInteger
import java.security.SecureRandom

fun generateToken(): String =
        BigInteger(128, SecureRandom()).toString(32) + System.currentTimeMillis()