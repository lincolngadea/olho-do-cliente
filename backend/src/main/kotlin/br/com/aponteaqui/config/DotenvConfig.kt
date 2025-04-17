package br.com.aponteaqui.config

import io.github.cdimascio.dotenv.Dotenv
import jakarta.annotation.PostConstruct
import org.springframework.context.annotation.Configuration

@Configuration
class DotenvConfig {
    @PostConstruct
    fun loadEnv(){
        val dotenv = Dotenv.configure()
            .directory("./")
            .ignoreIfMalformed()
            .ignoreIfMissing()
            .load()

        dotenv.entries().forEach{
            System.setProperty(it.key, it.value)
        }
    }
}