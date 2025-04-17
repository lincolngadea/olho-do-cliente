package br.com.aponteaqui.presentation.review.oauth.dto

import br.com.aponteaqui.config.GoogleAuthProperties
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate

@RestController
class GoogleOAuthCallbackController(
    private val googleAuthProperties: GoogleAuthProperties
) {

    @GetMapping("/oauth2/callback")
    fun handleGoogleCallback(
        @RequestParam code: String
    ): ResponseEntity<GoogleTokenResponse> {
        val restTemplate = RestTemplate()

        val body: MultiValueMap<String, String> = LinkedMultiValueMap()
        body.add("code", code)
        body.add("client_id", googleAuthProperties.clientId)
        body.add("client_secret", googleAuthProperties.clientSecret)
        body.add("redirect_uri", googleAuthProperties.redirectUri)
        body.add("grant_type", "authorization_code")

        val response = restTemplate.postForEntity(
            "https://oauth2.googleapis.com/token",
            org.springframework.http.RequestEntity
                .post("https://oauth2.googleapis.com/token")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(body),
            GoogleTokenResponse::class.java
        )
        return ResponseEntity.ok(response.body)
    }
}