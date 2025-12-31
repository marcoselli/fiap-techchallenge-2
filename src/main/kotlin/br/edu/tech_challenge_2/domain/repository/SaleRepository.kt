package br.edu.tech_challenge_2.domain.repository

import br.edu.tech_challenge_2.domain.entity.Sale

interface SaleRepository {
    fun save(sale: Sale): Sale
    fun findById(id: String): Sale?
    fun findByVehicleId(vehicleId: String): Sale?
}