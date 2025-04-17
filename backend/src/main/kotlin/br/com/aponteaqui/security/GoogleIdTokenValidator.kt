package br.com.aponteaqui.security

import br.com.aponteaqui.config.GoogleAuthProperties
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport
import com.google.api.client.json.gson.GsonFactory
import org.springframework.stereotype.Component
import java.util.*

@Component
class GoogleIdTokenValidator(
    private val googleAuthProperties: GoogleAuthProperties
) {
    private val jsonFactory = GsonFactory.getDefaultInstance()
    private val transport = GoogleNetHttpTransport.newTrustedTransport()

    private val verifier: GoogleIdTokenVerifier = GoogleIdTokenVerifier.Builder(transport, jsonFactory)
        .setAudience(Collections.singletonList(googleAuthProperties.clientId))
        .build()

    fun verify(idTokenString: String): GoogleIdToken.Payload? {
        val idToken: GoogleIdToken? = verifier.verify(idTokenString)
        return idToken?.payload
    }
}