package br.edu.tech_challenge_2.infra.persistence.adapter

import br.edu.tech_challenge_2.domain.entity.Vehicle
import br.edu.tech_challenge_2.domain.entity.VehicleStatus
import br.edu.tech_challenge_2.domain.repository.VehicleRepository
import br.edu.tech_challenge_2.infra.persistence.mapper.VehicleMapper
import br.edu.tech_challenge_2.infra.persistence.repository.VehicleJpaRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

@Repository
class VehicleRepositoryImpl (
    private val jpaRepository: VehicleJpaRepository
) : VehicleRepository {

    override fun save(vehicle: Vehicle): Vehicle {
        val entity = VehicleMapper.toJpa(vehicle)
        jpaRepository.save(entity)
        return vehicle
    }

    override fun findById(id: String): Vehicle? =
        jpaRepository.findByIdOrNull(id)
            ?.let { VehicleMapper.toDomain(it) }

    override fun findByStatusOrderedByPrice(status: VehicleStatus): List<Vehicle> =
        jpaRepository.findByStatusOrderByPriceAsc(status)
            .map(VehicleMapper::toDomain)
}