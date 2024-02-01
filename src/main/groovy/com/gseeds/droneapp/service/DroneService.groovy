package com.gseeds.droneapp.service

import com.gseeds.droneapp.model.dto.DroneDto

interface DroneService {
    List<DroneDto> findAll()

    DroneDto findById(Integer droneId)

    DroneDto findByRegistration(String droneRegistration)

    DroneDto saveDrone(DroneDto droneToSave)
}