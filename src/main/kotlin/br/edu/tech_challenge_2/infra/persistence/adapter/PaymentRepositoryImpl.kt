package br.edu.tech_challenge_2.infra.persistence.adapter

import br.edu.tech_challenge_2.domain.entity.Payment
import br.edu.tech_challenge_2.domain.repository.PaymentRepository
import br.edu.tech_challenge_2.infra.persistence.mapper.PaymentMapper
import br.edu.tech_challenge_2.infra.persistence.repository.PaymentJpaRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

@Repository
class PaymentRepositoryImpl(
    private val jpaRepository: PaymentJpaRepository
) : PaymentRepository {

    override fun findById(id: String): Payment? =
        jpaRepository.findByIdOrNull(id)
            ?.let { PaymentMapper.toDomain(it) }

    override fun save(payment: Payment): Payment {
        val entity = PaymentMapper.toJpa(payment)
        jpaRepository.save(entity)
        return payment
    }
}