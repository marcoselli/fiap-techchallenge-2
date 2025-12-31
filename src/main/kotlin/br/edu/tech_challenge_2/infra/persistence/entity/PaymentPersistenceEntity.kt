package br.edu.tech_challenge_2.infra.persistence.entity

import br.edu.tech_challenge_2.domain.entity.PaymentStatus
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "payments")
class PaymentPersistenceEntity (
    @Id
    val id: String,
    val saleId: String,
    @Enumerated(EnumType.STRING)
    val status: PaymentStatus
)