package br.edu.tech_challenge_2.infra.persistence.entity

import br.edu.tech_challenge_2.domain.entity.SaleStatus
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.ZonedDateTime

@Entity
@Table(name = "sales")
data class SalePersistenceEntity(
    @Id
    val id: String,
    @Column(nullable = false)
    val vehicleId: String,
    @Column(nullable = false, length = 11)
    val buyerCpf: String,
    @Column(nullable = false)
    val saleDate: ZonedDateTime,
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var status: SaleStatus
)