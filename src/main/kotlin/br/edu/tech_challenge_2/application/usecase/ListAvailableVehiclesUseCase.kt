package br.edu.tech_challenge_2.application.usecase

import br.edu.tech_challenge_2.domain.entity.VehicleStatus
import br.edu.tech_challenge_2.domain.repository.VehicleRepository
import org.springframework.stereotype.Service

@Service
class ListAvailableVehiclesUseCase(
    private val vehicleRepository: VehicleRepository
) {
    fun execute() = vehicleRepository.findByStatusOrderedByPrice(VehicleStatus.AVAILABLE)
}