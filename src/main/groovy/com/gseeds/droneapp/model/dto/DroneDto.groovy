package com.gseeds.droneapp.model.dto

import com.gseeds.droneapp.model.enums.Status
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

class DroneDto {
    @NotBlank(message = "Drone must have a registration code.")
    String registration
    Status status = Status.ready
    @NotNull(message = "Drone must have a model.")
    ModelDto model
    List<SensorDto> sensors
    BigDecimal weightKg
    BigDecimal latitude
    BigDecimal longitude
}
