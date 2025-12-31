package br.edu.tech_challenge_2.domain.entity

import br.edu.tech_challenge_2.domain.vo.Money
import com.fasterxml.jackson.annotation.JsonIgnore
import java.util.UUID

data class Vehicle(
    val id: String = UUID.randomUUID().toString(),
    var brand: String,
    var model: String,
    var year: Int,
    var color: String,
    var price: Money,
    var status: VehicleStatus = VehicleStatus.AVAILABLE
) {

    fun updateDetails(
        brand: String,
        model: String,
        year: Int,
        color: String,
        price: Money
    ) {
        this.brand = brand
        this.model = model
        this.year = year
        this.color = color
        this.price = price
    }

    fun markAsSold() {
        require(status == VehicleStatus.AVAILABLE) {
            "Vehicle already sold"
        }
        status = VehicleStatus.SOLD
    }

    @JsonIgnore
    fun isAvailable(): Boolean = this.status == VehicleStatus.AVAILABLE
}

enum class VehicleStatus {
    AVAILABLE,
    SOLD
}