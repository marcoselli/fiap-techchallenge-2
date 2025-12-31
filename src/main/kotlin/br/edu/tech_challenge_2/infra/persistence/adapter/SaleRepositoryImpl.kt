package br.edu.tech_challenge_2.infra.persistence.adapter

import br.edu.tech_challenge_2.domain.entity.Sale
import br.edu.tech_challenge_2.domain.repository.SaleRepository
import br.edu.tech_challenge_2.infra.persistence.mapper.SaleMapper
import br.edu.tech_challenge_2.infra.persistence.repository.SaleJpaRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

@Repository
class SaleRepositoryImpl(
    private val jpaRepository: SaleJpaRepository
) : SaleRepository {

    override fun save(sale: Sale): Sale {
        val entity = SaleMapper.toJpa(sale)
        jpaRepository.save(entity)
        return sale
    }

    override fun findById(id: String): Sale? =
        jpaRepository.findByIdOrNull(id)
            ?.let { SaleMapper.toDomain(it) }

    override fun findByVehicleId(vehicleId: String): Sale? =
        jpaRepository.findByVehicleId(vehicleId)
            ?.let(SaleMapper::toDomain)
}