package br.edu.tech_challenge_2.domain.entity

import br.edu.tech_challenge_2.domain.vo.CPF
import java.time.ZonedDateTime
import java.util.UUID

class Sale(
    val id: String = UUID.randomUUID().toString(),
    val vehicleId: String,
    val buyerCpf: CPF,
    val saleDate: ZonedDateTime = ZonedDateTime.now(),
    status: SaleStatus
){

    var status: SaleStatus = status
        private set

    fun complete() {
        require(status == SaleStatus.PENDING) {
            "Only pending sales can be completed"
        }
        status = SaleStatus.COMPLETED
    }

    fun cancel() {
        require(status == SaleStatus.PENDING) {
            "Only pending sales can be canceled"
        }
        status = SaleStatus.CANCELED
    }
}

enum class SaleStatus {
    PENDING,
    COMPLETED,
    CANCELED
}