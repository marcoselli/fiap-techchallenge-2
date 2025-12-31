package br.edu.tech_challenge_2.infra.web.dto

import java.math.BigDecimal

data class CreateVehicleRequest(
    val brand: String,
    val model: String,
    val year: Int,
    val color: String,
    val price: BigDecimal
)