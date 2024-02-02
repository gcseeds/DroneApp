package com.gseeds.droneapp.service.impl

import com.gseeds.droneapp.model.dto.DroneDto
import com.gseeds.droneapp.model.entity.Drone
import com.gseeds.droneapp.model.enums.SensorType
import com.gseeds.droneapp.model.enums.Status
import com.gseeds.droneapp.model.mapper.DroneMapper
import com.gseeds.droneapp.repository.DroneRepository
import com.gseeds.droneapp.repository.DroneSpecifications
import com.gseeds.droneapp.repository.ModelRepository
import com.gseeds.droneapp.repository.SensorRepository
import com.gseeds.droneapp.service.DroneService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.domain.Example
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class DroneServiceImpl implements  DroneService{
    @Autowired
    DroneRepository droneRepository

    @Autowired
    ModelRepository modelRepository

    @Autowired
    SensorRepository sensorRepository

    @Override
    List<DroneDto> findAll() {
        droneRepository.findAll().stream().map { DroneMapper.mapEntity(it)}.toList()
    }

    @Override
    DroneDto findById(Integer droneId) {
        DroneMapper.mapEntity(droneRepository.findById droneId get())
    }

    @Override
    DroneDto findByRegistration(String droneRegistration) {
        DroneMapper.mapEntity(droneRepository.findByRegistration droneRegistration get())
    }

//    @Override
//    List<DroneDto> findByStatusAndModelName(Status status, String modelName){
////        Specification<Drone> specification = DroneSpecifications.hasModelName(modelName);
////        droneRepository.findAll(specification).stream().map {DroneMapper.mapEntity(it)}.toList()
//        //droneRepository.findAllByModelName(modelName).stream().map {DroneMapper.mapEntity(it)}.toList()
//
//    }

    @Override
    DroneDto saveDrone(DroneDto droneToSave){
        Drone entity = DroneMapper.mapDto droneToSave
        //Save model
        var foundModel = modelRepository.findByNameIgnoreCase(entity.model.name)
        if (foundModel.isPresent()){
            if (!(foundModel.get().manufacturer == entity.model.manufacturer)){
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Manufacturer name does not match record.")
            }
            else entity.setModel(foundModel.get())
        }
        else{
            entity.setModel(modelRepository.save(entity.model))
        }

        Drone savedEntity = droneRepository.save entity
        savedEntity.sensors = entity.sensors.stream().map {it.setDroneId(savedEntity.id)
            sensorRepository.save(it)}.toList()
        DroneMapper.mapEntity(savedEntity)
    }

    @Override
    List<DroneDto> findByModelName(String modelName) {
        droneRepository.findAllByModelName(modelName).stream().map {DroneMapper.mapEntity(it)}.toList()
    }

    @Override
    List<DroneDto> findByStatusSensorTypeModelName(Status status, SensorType sensorType, String modelName) {
        System.out.println("Status ${status}")
        System.out.println("SensorType ${sensorType}")
        System.out.println("Model name ${modelName}")
        droneRepository.findAllByStatusAndSensorTypeAndModelName(status, sensorType, modelName)
            .stream().map {DroneMapper.mapEntity(it)}.toList()
    }
}
