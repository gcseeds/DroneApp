package com.gseeds.droneapp.controller

import com.gseeds.droneapp.model.dto.DroneDto
import com.gseeds.droneapp.model.entity.Drone
import com.gseeds.droneapp.service.DroneService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping('drones')
class DroneController {
    @Autowired
    DroneService service

    @GetMapping
    List<DroneDto> getAllDrones(){
        service.findAll();
    }

    @PostMapping
    DroneDto saveDrone(@RequestBody DroneDto drone){
        service.saveDrone drone
    }
}
