package br.edu.tech_challenge_2.infra.persistence.entity

import br.edu.tech_challenge_2.domain.entity.VehicleStatus
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.math.BigDecimal

@Entity
@Table(name = "vehicles")
data class VehiclePersistenceEntity(
    @Id
    val id: String,
    val brand: String,
    val model: String,
    @Column(name = "manufacture_year", nullable = false)
    val year: Int,
    val color: String,
    val price: BigDecimal,
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val status: VehicleStatus
)