package com.gseeds.droneapp.service.impl

import com.gseeds.droneapp.model.dto.SensorDto
import com.gseeds.droneapp.model.entity.Drone
import com.gseeds.droneapp.model.entity.Sensor
import com.gseeds.droneapp.model.mapper.SensorMapper
import com.gseeds.droneapp.repository.DroneRepository
import com.gseeds.droneapp.repository.SensorRepository
import com.gseeds.droneapp.service.SensorService
import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
@Transactional
class SensorServiceImpl implements  SensorService{
    @Autowired
    SensorRepository sensorRepository

    @Autowired
    DroneRepository droneRepository

    @Override
    List<SensorDto> saveSensor(String droneRegistration, SensorDto dto) {
        Integer droneId = droneRepository.findByRegistration(droneRegistration).get().getId()
        Sensor entity = SensorMapper.mapDto(dto)
        entity.setDroneId(droneId)
        sensorRepository.save(entity)
        droneRepository.findByRegistration(droneRegistration).get()
                .getSensors().stream().map {SensorMapper.mapEntity(it)}.toList()
    }

    @Override
    def deleteSensor(String droneRegistration, String sensorName) {
        Drone drone = droneRepository.findByRegistration(droneRegistration).get()
        sensorRepository.deleteAllByNameIgnoreCaseAndDroneId(sensorName, drone.getId())
    }
}
