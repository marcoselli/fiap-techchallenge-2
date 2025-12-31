package br.edu.tech_challenge_2.domain.repository

import br.edu.tech_challenge_2.domain.entity.Vehicle
import br.edu.tech_challenge_2.domain.entity.VehicleStatus

interface VehicleRepository {
    fun save(vehicle: Vehicle): Vehicle
    fun findById(id: String): Vehicle?
    fun findByStatusOrderedByPrice(status: VehicleStatus): List<Vehicle>
}