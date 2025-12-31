package br.edu.tech_challenge_2.application.usecase

import br.edu.tech_challenge_2.domain.entity.Payment
import br.edu.tech_challenge_2.domain.entity.PaymentStatus
import br.edu.tech_challenge_2.domain.repository.PaymentRepository
import br.edu.tech_challenge_2.domain.repository.SaleRepository
import br.edu.tech_challenge_2.domain.repository.VehicleRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class ProcessPaymentWebhookUseCase(
    private val paymentRepository: PaymentRepository,
    private val saleRepository: SaleRepository,
    private val vehicleRepository: VehicleRepository
) {

    @Transactional
    fun execute(paymentId: String, status: PaymentStatus) {
        val payment = paymentRepository.findById(paymentId)
            ?: throw IllegalArgumentException("Payment not found")

        when (status) {
            PaymentStatus.CONFIRMED -> handleConfirmed(payment)
            PaymentStatus.CANCELED -> handleCanceled(payment)
            else -> throw IllegalArgumentException("Status not mapped for this operation")
        }
    }

    private fun handleConfirmed(payment: Payment) {
        payment.confirm()
        paymentRepository.save(payment)

        val sale = saleRepository.findById(payment.saleId)
            ?: throw IllegalStateException("Sale not found")

        sale.complete()
        saleRepository.save(sale)

        val vehicle = vehicleRepository.findById(sale.vehicleId)
            ?: throw IllegalStateException("Vehicle not found")

        vehicle.markAsSold()
        vehicleRepository.save(vehicle)
    }

    private fun handleCanceled(payment: Payment) {
        payment.cancel()
        paymentRepository.save(payment)

        val sale = saleRepository.findById(payment.saleId)
            ?: throw IllegalArgumentException("Vehicle not found")

        sale.cancel()
        saleRepository.save(sale)
    }
}
