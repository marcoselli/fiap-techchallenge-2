package br.edu.tech_challenge_2.domain.vo

class CPF private constructor(
    val value: String
) {

    companion object {
        fun of(value: String): CPF {
            val clean = value.replace(Regex("[^0-9]"), "")
            require(clean.length == 11) { "CPF inválido" }
            return CPF(clean)
        }
    }
}