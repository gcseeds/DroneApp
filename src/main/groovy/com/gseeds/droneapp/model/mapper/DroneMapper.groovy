package com.gseeds.droneapp.model.mapper

import com.gseeds.droneapp.model.dto.DroneDto
import com.gseeds.droneapp.model.entity.Drone

class DroneMapper {
    static DroneDto mapEntity(Drone entity){
        new DroneDto(registration: entity.registration,
                status: entity.status,
                model: ModelMapper.mapEntity(entity.model),
                sensors: entity.sensors.stream().map {SensorMapper.mapEntity(it)}.toList()
        )
    }

    static Drone mapDto(DroneDto dto){
        new Drone(registration: dto.registration,
                status: dto.status,
                model: ModelMapper.mapDto(dto.model),
                sensors: dto.sensors.stream().map {SensorMapper.mapDto(it)}.toList()
        )
    }

}
