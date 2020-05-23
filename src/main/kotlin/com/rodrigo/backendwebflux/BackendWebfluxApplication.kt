package com.rodrigo.backendwebflux

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.data.mongo.MongoReactiveDataAutoConfiguration

@SpringBootApplication(exclude = [MongoReactiveDataAutoConfiguration::class])
class BackendWebfluxApplication

fun main(args: Array<String>) {
	SpringApplication.run(BackendWebfluxApplication::class.java, *args)
}
