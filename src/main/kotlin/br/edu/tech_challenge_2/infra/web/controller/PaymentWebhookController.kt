package br.edu.tech_challenge_2.infra.web.controller

import br.edu.tech_challenge_2.application.usecase.ProcessPaymentWebhookUseCase
import br.edu.tech_challenge_2.domain.entity.PaymentStatus
import br.edu.tech_challenge_2.infra.web.dto.PaymentWebhookRequest
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("v1/payments")
class PaymentWebhookController(
    private val processPaymentWebhookUseCase: ProcessPaymentWebhookUseCase
) {

    @PostMapping("/webhook")
    @ResponseStatus(HttpStatus.OK)
    fun process(
        @RequestBody request: PaymentWebhookRequest
    ) {
        processPaymentWebhookUseCase.execute(request.paymentId, request.status)
    }
}