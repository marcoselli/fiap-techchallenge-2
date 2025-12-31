package br.edu.tech_challenge_2.infra.persistence.mapper

import br.edu.tech_challenge_2.domain.entity.Payment
import br.edu.tech_challenge_2.domain.entity.PaymentStatus
import br.edu.tech_challenge_2.infra.persistence.entity.PaymentPersistenceEntity

object  PaymentMapper {

    fun toDomain(entity: PaymentPersistenceEntity) =
        Payment(
            id = entity.id,
            saleId = entity.saleId,
            status = entity.status
        )

    fun toJpa(payment: Payment) =
        PaymentPersistenceEntity(
            id = payment.id,
            saleId = payment.saleId,
            status = payment.status
        )
}