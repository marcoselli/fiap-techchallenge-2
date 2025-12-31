package br.edu.tech_challenge_2

import br.edu.tech_challenge_2.common.util.ScopeUtils
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.core.env.AbstractEnvironment

@SpringBootApplication
class TechChallenge2Application

fun main(args: Array<String>) {
	val profile = ScopeUtils.getProfileFromScope()
	System.setProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, profile)
	runApplication<TechChallenge2Application>(*args)
}
