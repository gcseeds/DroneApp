package com.gseeds.droneapp.service

import com.gseeds.droneapp.entity.Model

interface ModelService {
    List<Model> findAll()
    Model findById(Integer id)
    Model findByName(String name)
}