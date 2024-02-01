package com.gseeds.droneapp.repository

import com.gseeds.droneapp.model.entity.Sensor
import org.springframework.data.jpa.repository.JpaRepository

interface SensorRepository extends JpaRepository<Sensor, Integer>{
    Optional<Sensor> findByNameIgnoreCase(String name)
}
