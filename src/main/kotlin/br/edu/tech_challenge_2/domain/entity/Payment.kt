package br.edu.tech_challenge_2.domain.entity

import java.util.UUID

data class Payment(
    val id: String = UUID.randomUUID().toString(),
    val saleId: String,
    var status: PaymentStatus
) {

    fun confirm() {
        status = PaymentStatus.CONFIRMED
    }

    fun cancel() {
        status = PaymentStatus.CANCELED
    }
}

enum class PaymentStatus {
    PENDING,
    CONFIRMED,
    CANCELED
}