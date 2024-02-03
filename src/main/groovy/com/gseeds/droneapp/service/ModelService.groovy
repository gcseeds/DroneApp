package com.gseeds.droneapp.service

import com.gseeds.droneapp.model.dto.ModelDto
import com.gseeds.droneapp.model.entity.Model

interface ModelService {
    List<Model> findAll()
    List<ModelDto> findAllDto()
    Model findById(Integer id)
    Model findByName(String name)
}