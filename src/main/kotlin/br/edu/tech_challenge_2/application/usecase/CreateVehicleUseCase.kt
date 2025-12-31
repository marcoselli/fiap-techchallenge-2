package br.edu.tech_challenge_2.application.usecase

import br.edu.tech_challenge_2.domain.entity.Vehicle
import br.edu.tech_challenge_2.domain.repository.VehicleRepository
import br.edu.tech_challenge_2.domain.vo.Money
import br.edu.tech_challenge_2.infra.web.dto.CreateVehicleRequest
import org.springframework.stereotype.Service

@Service
class CreateVehicleUseCase(
    private val vehicleRepository: VehicleRepository
) {
    fun execute(request: CreateVehicleRequest): Vehicle {
        val vehicle = Vehicle(
            brand = request.brand,
            model = request.model,
            year = request.year,
            color = request.color,
            price = Money(request.price)
        )
        return vehicleRepository.save(vehicle)
    }
}