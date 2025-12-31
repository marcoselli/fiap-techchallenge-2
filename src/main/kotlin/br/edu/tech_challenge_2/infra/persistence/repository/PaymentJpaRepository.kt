package br.edu.tech_challenge_2.infra.persistence.repository

import br.edu.tech_challenge_2.infra.persistence.entity.PaymentPersistenceEntity
import org.springframework.data.jpa.repository.JpaRepository

interface PaymentJpaRepository : JpaRepository<PaymentPersistenceEntity, String>
