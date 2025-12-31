package br.edu.tech_challenge_2.infra.persistence.repository

import br.edu.tech_challenge_2.domain.entity.VehicleStatus
import br.edu.tech_challenge_2.infra.persistence.entity.VehiclePersistenceEntity
import org.springframework.data.jpa.repository.JpaRepository

interface VehicleJpaRepository : JpaRepository<VehiclePersistenceEntity, String> {

    fun findByStatusOrderByPriceAsc(status: VehicleStatus): List<VehiclePersistenceEntity>
}
