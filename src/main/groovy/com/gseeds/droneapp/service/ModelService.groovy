package com.gseeds.droneapp.service

import com.gseeds.droneapp.model.entity.Model

interface ModelService {
    List<Model> findAll()
    Model findById(Integer id)
    Model findByName(String name)
}