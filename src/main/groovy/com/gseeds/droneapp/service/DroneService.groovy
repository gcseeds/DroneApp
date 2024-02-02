package com.gseeds.droneapp.service

import com.gseeds.droneapp.model.dto.DroneDto
import com.gseeds.droneapp.model.enums.SensorType
import com.gseeds.droneapp.model.enums.Status

interface DroneService {
    List<DroneDto> findAll()

    DroneDto findById(Integer droneId)

    DroneDto findByRegistration(String droneRegistration)

    DroneDto saveDrone(DroneDto droneToSave)

    List<DroneDto> findByModelName(String modelName)

    List<DroneDto> findByStatusSensorTypeModelName(Status statusString,
                                                   SensorType sensorType,
                                                   String modelName)
}