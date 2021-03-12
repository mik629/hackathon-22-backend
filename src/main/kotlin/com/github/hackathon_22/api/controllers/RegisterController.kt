package com.github.hackathon_22.api.controllers

import com.github.hackathon_22.api.models.RegisterRequestDTO
import com.github.hackathon_22.api.models.RegisterResponseDTO
import com.github.hackathon_22.db.models.toUserProfileDTO
import com.github.hackathon_22.services.RegisterService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/register")
class RegisterController(
        @Autowired val registerService: RegisterService
) {

    @PostMapping
    fun register(
            @RequestBody registerRequestDTO: RegisterRequestDTO
    ): RegisterResponseDTO {
        val registerResult = registerService.register(
                username = registerRequestDTO.username,
                pwd = registerRequestDTO.pwd,
                name = registerRequestDTO.name,
                isMentor = registerRequestDTO.isMentor
        )

        return RegisterResponseDTO(
                token = registerResult.token,
                registerResult.user.toUserProfileDTO()
        )
    }
}