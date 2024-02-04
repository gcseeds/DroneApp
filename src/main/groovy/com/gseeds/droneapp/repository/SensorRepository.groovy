package com.gseeds.droneapp.repository

import com.gseeds.droneapp.model.entity.Sensor
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SensorRepository extends JpaRepository<Sensor, Integer>{
    deleteAllByNameIgnoreCaseAndDroneId(String name, Integer droneId)
    List<Sensor> findByDroneId(Integer droneId)
}
