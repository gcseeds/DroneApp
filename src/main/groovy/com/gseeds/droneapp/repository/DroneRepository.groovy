package com.gseeds.droneapp.repository

import com.gseeds.droneapp.model.entity.Drone
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface DroneRepository extends JpaRepository<Drone, Integer>{
    List<Drone> findAll()

    Optional<Drone> findByRegistration(String registration)
}