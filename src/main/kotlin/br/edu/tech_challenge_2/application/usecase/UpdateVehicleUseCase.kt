package br.edu.tech_challenge_2.application.usecase

import br.edu.tech_challenge_2.domain.entity.Vehicle
import br.edu.tech_challenge_2.domain.repository.VehicleRepository
import br.edu.tech_challenge_2.domain.vo.Money
import br.edu.tech_challenge_2.infra.web.dto.UpdateVehicleRequest
import org.springframework.stereotype.Service

@Service
class UpdateVehicleUseCase(
    private val vehicleRepository: VehicleRepository
) {
    fun execute(id: String, request: UpdateVehicleRequest): Vehicle {
        val vehicle = vehicleRepository.findById(id)
            ?: throw IllegalArgumentException("Vehicle not found")

        vehicle.updateDetails(
            brand = request.brand,
            model = request.model,
            year = request.year,
            color = request.color,
            price = Money(request.price)
        )

        vehicleRepository.save(vehicle)
        return vehicle
    }
}