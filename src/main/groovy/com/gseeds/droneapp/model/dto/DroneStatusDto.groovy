package com.gseeds.droneapp.model.dto

import com.gseeds.droneapp.model.enums.Status
import jakarta.validation.constraints.NotNull

class DroneStatusDto {
    String registration

    @NotNull
    Status status
}
