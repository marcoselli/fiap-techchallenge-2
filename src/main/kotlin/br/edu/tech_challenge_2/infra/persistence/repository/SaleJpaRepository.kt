package br.edu.tech_challenge_2.infra.persistence.repository

import br.edu.tech_challenge_2.infra.persistence.entity.SalePersistenceEntity
import org.springframework.data.jpa.repository.JpaRepository

interface SaleJpaRepository : JpaRepository<SalePersistenceEntity, String> {
    fun findByVehicleId(vehicleId: String): SalePersistenceEntity?
}