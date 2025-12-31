package br.edu.tech_challenge_2.infra.web.dto

import br.edu.tech_challenge_2.domain.entity.PaymentStatus

data class PaymentWebhookRequest (
    val paymentId: String,
    val status: PaymentStatus
)