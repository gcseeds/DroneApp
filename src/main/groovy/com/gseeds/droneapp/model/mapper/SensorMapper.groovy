package com.gseeds.droneapp.model.mapper

import com.gseeds.droneapp.model.dto.SensorDto
import com.gseeds.droneapp.model.entity.Sensor

class SensorMapper {
    static SensorDto mapEntity(Sensor entity){
        new SensorDto(name: entity.name, sensorType: entity.sensorType)
    }

    static Sensor mapDto(SensorDto dto){
        new Sensor(name: dto.name, sensorType: dto.sensorType)
    }
}
