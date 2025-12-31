package br.edu.tech_challenge_2.infra.persistence.mapper

import br.edu.tech_challenge_2.domain.entity.Sale
import br.edu.tech_challenge_2.domain.vo.CPF
import br.edu.tech_challenge_2.infra.persistence.entity.SalePersistenceEntity

object SaleMapper {

    fun toDomain(entity: SalePersistenceEntity) =
        Sale(
            id = entity.id,
            vehicleId = entity.vehicleId,
            buyerCpf = CPF.of(entity.buyerCpf),
            saleDate = entity.saleDate,
            status = entity.status
        )

    fun toJpa(sale: Sale) =
        SalePersistenceEntity(
            id = sale.id,
            vehicleId = sale.vehicleId,
            buyerCpf = sale.buyerCpf.value,
            saleDate = sale.saleDate,
            status = sale.status
        )
}