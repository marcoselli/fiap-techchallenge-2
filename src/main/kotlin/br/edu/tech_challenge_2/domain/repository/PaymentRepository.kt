package br.edu.tech_challenge_2.domain.repository

import br.edu.tech_challenge_2.domain.entity.Payment

interface PaymentRepository {
    fun findById(id: String): Payment?
    fun save(payment: Payment): Payment
}