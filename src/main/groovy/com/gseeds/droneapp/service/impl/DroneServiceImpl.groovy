package com.gseeds.droneapp.service.impl

import com.gseeds.droneapp.entity.Drone
import com.gseeds.droneapp.repository.DroneRepository
import com.gseeds.droneapp.service.DroneService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class DroneServiceImpl implements  DroneService{
    @Autowired
    DroneRepository droneRepository;

    @Override
    List<Drone> findAll() {
        droneRepository.findAll()
    }

    @Override
    Drone findById(Integer droneId) {
        droneRepository.findById droneId get()
    }

    @Override
    Drone findByRegistration(String droneRegistration) {
        droneRepository.findByRegistration droneRegistration get()
    }

    @Override
    Drone saveDrone(Drone droneToSave){
        droneRepository.save droneToSave
    }
}
