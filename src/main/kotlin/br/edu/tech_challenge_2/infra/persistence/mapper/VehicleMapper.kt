package br.edu.tech_challenge_2.infra.persistence.mapper

import br.edu.tech_challenge_2.domain.entity.Vehicle
import br.edu.tech_challenge_2.domain.vo.Money
import br.edu.tech_challenge_2.infra.persistence.entity.VehiclePersistenceEntity

object VehicleMapper {

    fun toDomain(entity: VehiclePersistenceEntity) =
        Vehicle(
            id = entity.id,
            brand = entity.brand,
            model = entity.model,
            year = entity.year,
            color = entity.color,
            price = Money(entity.price),
            status = entity.status
        )

    fun toJpa(vehicle: Vehicle) =
        VehiclePersistenceEntity(
            id = vehicle.id,
            brand = vehicle.brand,
            model = vehicle.model,
            year = vehicle.year,
            color = vehicle.color,
            price = vehicle.price.value,
            status = vehicle.status
        )
}