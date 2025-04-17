package br.com.aponteaqui.presentation.review.oauth

import br.com.aponteaqui.config.GoogleAuthProperties
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.util.UriComponentsBuilder

@RestController
class GoogleOAuthController(
    private val googleAuthProperties: GoogleAuthProperties
) {
    @GetMapping("/login")
    fun redirectToGoogle(): ResponseEntity<Void>{
        val googleAuthUrl = UriComponentsBuilder
            .fromUriString("https://accounts.google.com/o/oauth2/v2/auth")
            .queryParam("client_id", googleAuthProperties.clientId)
            .queryParam("redirect_uri", googleAuthProperties.redirectUri)
            .queryParam("response_type","code")
            .queryParam("scope", "openid email profile")
            .queryParam("access_type","offline")
            .build()
            .toUri()

        return ResponseEntity.status(302)
            .location(googleAuthUrl)
            .build()
    }
}