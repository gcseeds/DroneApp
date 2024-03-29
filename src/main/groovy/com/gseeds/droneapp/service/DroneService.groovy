package com.gseeds.droneapp.service

import com.gseeds.droneapp.model.dto.DroneDto
import com.gseeds.droneapp.model.dto.DroneStatusDto
import com.gseeds.droneapp.model.enums.SensorType
import com.gseeds.droneapp.model.enums.Status

interface DroneService {
    List<DroneDto> findAll()

    DroneDto findById(Integer droneId)

    DroneDto findByRegistration(String droneRegistration)

    DroneDto saveDrone(DroneDto droneToSave)

    DroneDto updateDrone(String currentRegistration, DroneDto droneToUpdate)

    deleteDrone(String droneRegistration)

    List<DroneDto> findByModelName(String modelName)

    List<DroneDto> findByStatusSensorTypeModelNameWeight(Status statusString,
                                                   SensorType sensorType,
                                                   String modelName,
                                                   BigDecimal maxWeight,
                                                   BigDecimal minWeight)

    DroneStatusDto updateDroneStatus(String registration, DroneStatusDto statusDto)

    DroneStatusDto getDroneStatus(String registration)
}