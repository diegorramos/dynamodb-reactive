package br.com.diegorramos.dynamodbreactive

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DynamodbReactiveApplication

fun main(args: Array<String>) {
	runApplication<DynamodbReactiveApplication>(*args)
}
