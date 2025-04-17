package br.com.aponteaqui.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "spring.security.oauth2.client.registration.google")
class GoogleAuthProperties {
    lateinit var clientId: String
    lateinit var clientSecret: String
    lateinit var redirectUri: String
}