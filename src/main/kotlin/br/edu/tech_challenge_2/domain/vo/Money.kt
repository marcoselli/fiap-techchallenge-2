package br.edu.tech_challenge_2.domain.vo

import java.math.BigDecimal

data class Money(
    val value: BigDecimal
) {
    init {
        require(value >= BigDecimal.ZERO) {
            "Valor monetário não pode ser negativo"
        }
    }
}