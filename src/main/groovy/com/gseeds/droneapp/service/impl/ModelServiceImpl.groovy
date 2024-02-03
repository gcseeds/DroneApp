package com.gseeds.droneapp.service.impl

import com.gseeds.droneapp.model.dto.ModelDto
import com.gseeds.droneapp.model.entity.Model
import com.gseeds.droneapp.model.mapper.ModelMapper
import com.gseeds.droneapp.repository.ModelRepository
import com.gseeds.droneapp.service.ModelService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ModelServiceImpl implements ModelService{
    @Autowired
    ModelRepository modelRepository

    @Override
    List<Model> findAll() {
        modelRepository.findAll()
    }

    @Override
    List<ModelDto> findAllDto() {
        modelRepository.findAll().stream().map { ModelMapper.mapEntity(it)}.toList()
    }

    @Override
    Model findById(Integer id) {
        modelRepository.findById id get()
    }

    @Override
    Model findByName(String name) {
        modelRepository.findByNameIgnoreCase name get()
    }
}
