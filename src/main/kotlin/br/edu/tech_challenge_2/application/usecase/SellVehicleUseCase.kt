package br.edu.tech_challenge_2.application.usecase

import br.edu.tech_challenge_2.domain.entity.Payment
import br.edu.tech_challenge_2.domain.entity.PaymentStatus
import br.edu.tech_challenge_2.domain.entity.Sale
import br.edu.tech_challenge_2.domain.entity.SaleStatus
import br.edu.tech_challenge_2.domain.repository.PaymentRepository
import br.edu.tech_challenge_2.domain.repository.SaleRepository
import br.edu.tech_challenge_2.domain.repository.VehicleRepository
import br.edu.tech_challenge_2.domain.vo.CPF
import org.springframework.stereotype.Service
import java.time.ZonedDateTime
import java.util.UUID

@Service
class SellVehicleUseCase(
    private val vehicleRepository: VehicleRepository,
    private val saleRepository: SaleRepository,
    private val paymentRepository: PaymentRepository
) {

    fun execute(vehicleId: String, buyerCpf: CPF): Payment {
        val vehicle = vehicleRepository.findById(vehicleId)
            ?: throw IllegalArgumentException("Vehicle not found")

        require(vehicle.isAvailable()) {
            "Vehicle is not available for sale"
        }

        val sale = Sale(
            id = UUID.randomUUID().toString(),
            vehicleId = vehicle.id,
            buyerCpf = buyerCpf,
            saleDate = ZonedDateTime.now(),
            status = SaleStatus.PENDING
        )
        saleRepository.save(sale)

        val payment = Payment(
            id = UUID.randomUUID().toString(),
            saleId = sale.id,
            status = PaymentStatus.PENDING
        )
        paymentRepository.save(payment)
        return payment
    }
}