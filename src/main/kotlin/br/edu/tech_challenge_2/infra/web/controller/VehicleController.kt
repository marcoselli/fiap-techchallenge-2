package br.edu.tech_challenge_2.infra.web.controller

import br.edu.tech_challenge_2.application.usecase.CreateVehicleUseCase
import br.edu.tech_challenge_2.application.usecase.ListAvailableVehiclesUseCase
import br.edu.tech_challenge_2.application.usecase.ListSoldVehiclesUseCase
import br.edu.tech_challenge_2.application.usecase.SellVehicleUseCase
import br.edu.tech_challenge_2.application.usecase.UpdateVehicleUseCase
import br.edu.tech_challenge_2.domain.entity.Payment
import br.edu.tech_challenge_2.domain.vo.CPF
import br.edu.tech_challenge_2.infra.web.dto.CreateVehicleRequest
import br.edu.tech_challenge_2.infra.web.dto.SellVehicleRequest
import br.edu.tech_challenge_2.infra.web.dto.UpdateVehicleRequest
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/vehicles")
@Tag(
    name = "Vehicles",
    description = "Operations related to vehicle management"
)class VehicleController(
    private val createVehicleUseCase: CreateVehicleUseCase,
    private val updateVehicleUseCase: UpdateVehicleUseCase,
    private val sellVehicleUseCase: SellVehicleUseCase,
    private val listAvailableVehiclesUseCase: ListAvailableVehiclesUseCase,
    private val listSoldVehiclesUseCase: ListSoldVehiclesUseCase
) {

    @Operation(
        summary = "Create vehicle",
        description = "Registers a new vehicle in the system"
    )
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "201", description = "Vehicle successfully created"),
            ApiResponse(responseCode = "400", description = "Invalid request data")
        ]
    )
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody request: CreateVehicleRequest) =
        createVehicleUseCase.execute(request)


    @Operation(
        summary = "Update vehicle",
        description = "Updates an existing vehicle data"
    )
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "204", description = "Vehicle successfully updated"),
            ApiResponse(responseCode = "404", description = "Vehicle not found"),
            ApiResponse(responseCode = "400", description = "Invalid request data")
        ]
    )
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun update(
        @PathVariable id: String,
        @RequestBody request: UpdateVehicleRequest
    ) = updateVehicleUseCase.execute(id, request)


    @Operation(
        summary = "Sell vehicle",
        description = "Performs the sale of an available vehicle"
    )
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Vehicle successfully sold"),
            ApiResponse(responseCode = "404", description = "Vehicle not found"),
            ApiResponse(responseCode = "400", description = "Vehicle not available for sale")
        ]
    )
    @PostMapping("/{id}/sell")
    fun sell(
        @PathVariable id: String,
        @RequestBody request: SellVehicleRequest
    ): ResponseEntity<Payment> {
        val cpf = CPF.of(request.buyerCpf)
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(sellVehicleUseCase.execute(id, cpf))
    }


    @Operation(
        summary = "List available vehicles",
        description = "Returns all vehicles currently available for sale"
    )
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "List of available vehicles returned successfully")
        ]
    )
    @GetMapping("/available")
    fun listAvailable() =
        listAvailableVehiclesUseCase.execute()

    @Operation(
        summary = "List sold vehicles",
        description = "Returns all vehicles sold"
    )
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "List of available vehicles returned successfully")
        ]
    )
    @GetMapping("/sold")
    fun listSold() =
        listSoldVehiclesUseCase.execute()
}