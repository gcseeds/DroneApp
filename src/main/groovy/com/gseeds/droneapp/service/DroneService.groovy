package com.gseeds.droneapp.service

import com.gseeds.droneapp.entity.Drone

interface DroneService {
    List<Drone> findAll()

    Drone findById(Integer droneId)

    Drone findByRegistration(String droneRegistration)

    Drone saveDrone(Drone droneToSave)
}