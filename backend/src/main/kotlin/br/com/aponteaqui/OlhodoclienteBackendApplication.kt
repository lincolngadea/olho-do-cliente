package br.com.aponteaqui

import io.github.cdimascio.dotenv.dotenv
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class OlhodoclienteBackendApplication

fun main(args: Array<String>) {
	runApplication<OlhodoclienteBackendApplication>(*args)
}
