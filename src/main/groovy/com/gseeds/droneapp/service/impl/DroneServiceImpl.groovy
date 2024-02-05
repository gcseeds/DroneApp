package com.gseeds.droneapp.service.impl

import com.gseeds.droneapp.model.dto.DroneDto
import com.gseeds.droneapp.model.dto.DroneStatusDto
import com.gseeds.droneapp.model.entity.Drone
import com.gseeds.droneapp.model.enums.SensorType
import com.gseeds.droneapp.model.enums.Status
import com.gseeds.droneapp.model.mapper.DroneMapper
import com.gseeds.droneapp.repository.DroneRepository
import com.gseeds.droneapp.repository.ModelRepository
import com.gseeds.droneapp.repository.SensorRepository
import com.gseeds.droneapp.service.DroneService
import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
@Transactional
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
        DroneMapper.mapEntity(saveDroneEntity(entity))
    }

    @Override
    DroneDto updateDrone(String currentRegistration, DroneDto droneToUpdate) {
        Optional<Drone> currentEntity = droneRepository.findByRegistration(currentRegistration)
        Drone newEntity = DroneMapper.mapDto(droneToUpdate)

        if (currentEntity.isPresent()){
            Drone entity = currentEntity.get()
            newEntity.id = entity.id
            (entity.sensors - newEntity.sensors)
                    .forEach {sensorRepository.deleteAllByNameIgnoreCaseAndDroneId(it.name, entity.id)}
            newEntity.sensors = (newEntity.sensors - entity.sensors)
            System.out.println(newEntity.sensors.toListString())
        }
        DroneMapper.mapEntity(saveDroneEntity(newEntity))
    }

    Drone saveDroneEntity(Drone entity){
        var foundModel = modelRepository.findByNameIgnoreCase(entity.model.name)
        if (foundModel.isPresent()){
            entity.setModel(foundModel.get())
        }
        else{
            entity.setModel(modelRepository.save(entity.model))
        }
        if (null == entity.weightKg) entity.weightKg = entity.model.weightKg
        Drone savedEntity = droneRepository.save entity
        savedEntity.sensors = entity.sensors.stream().map {it.setDroneId(savedEntity.id)
            sensorRepository.save(it)}.toList()
        savedEntity
    }

    @Override
    def deleteDrone(String droneRegistration) {
        droneRepository.deleteByRegistration(droneRegistration)
    }

    @Override
    List<DroneDto> findByModelName(String modelName) {
        droneRepository.findAllByModelName(modelName).stream().map {DroneMapper.mapEntity(it)}.toList()
    }

    @Override
    List<DroneDto> findByStatusSensorTypeModelNameWeight(Status status,
                                                   SensorType sensorType,
                                                   String modelName,
                                                   BigDecimal maxWeight,
                                                   BigDecimal minWeight) {
        System.out.println("Status ${status}")
        System.out.println("SensorType ${sensorType}")
        System.out.println("Model name ${modelName}")
        droneRepository.findAllByStatusAndSensorTypeAndModelNameWeight(status,
                sensorType, modelName,
                maxWeight,
                minWeight)
            .stream().map {DroneMapper.mapEntity(it)}.toList()
    }

    @Override
    DroneStatusDto updateDroneStatus(String registration, DroneStatusDto statusDto) {
        Drone entity = droneRepository.findByRegistration(registration).orElseThrow()
        entity.setStatus(statusDto.status)
        if (null != statusDto.latitude) entity.setLatitude(statusDto.latitude)
        if (null != statusDto.longitude) entity.setLongitude(statusDto.longitude)
        entity = droneRepository.save(entity)
        new DroneStatusDto(registration: entity.getRegistration(),
                status: entity.getStatus(),
                latitude: entity.latitude,
                longitude: entity.longitude)
    }

    @Override
    DroneStatusDto getDroneStatus(String registration) {
        Drone entity = droneRepository.findByRegistration(registration).orElseThrow()
        new DroneStatusDto(registration: entity.getRegistration(),
                status: entity.getStatus(),
                latitude: entity.getLatitude(),
                longitude: entity.getLongitude())
    }
}
