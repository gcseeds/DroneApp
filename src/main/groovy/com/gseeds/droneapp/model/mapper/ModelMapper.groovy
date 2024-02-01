package com.gseeds.droneapp.model.mapper

import com.gseeds.droneapp.model.dto.ModelDto
import com.gseeds.droneapp.model.entity.Model

class ModelMapper {
    static ModelDto mapEntity(Model entity){
        new ModelDto(name: entity.name, manufacturer: entity.manufacturer)
    }

    static Model mapDto(ModelDto dto){
        new Model(name: dto.name, manufacturer: dto.manufacturer)
    }
}
