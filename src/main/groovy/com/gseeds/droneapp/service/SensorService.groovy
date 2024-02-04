package com.gseeds.droneapp.service

import com.gseeds.droneapp.model.dto.SensorDto

interface SensorService {
    List<SensorDto> saveSensor(String droneRegistration, SensorDto dto)

    deleteSensor(String droneRegistration, String sensorName)
}