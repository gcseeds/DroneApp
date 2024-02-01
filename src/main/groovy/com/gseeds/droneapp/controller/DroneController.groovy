package com.gseeds.droneapp.controller

import com.gseeds.droneapp.model.dto.DroneDto
import com.gseeds.droneapp.service.DroneService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus

@RestController
@RequestMapping('drones')
class DroneController {
    @Autowired
    DroneService service

    @GetMapping
    List<DroneDto> getAllDrones(){
        service.findAll();
    }

    @GetMapping('/{droneRegistration}')
    DroneDto getDroneByRegistration(@PathVariable('droneRegistration') String droneRegistration){
        try{
            service.findByRegistration(droneRegistration)
        }
        catch (NoSuchElementException noSuchElementException){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Drone not found", noSuchElementException);
        }
    }

    @PostMapping
    DroneDto saveDrone(@RequestBody DroneDto drone){
        service.saveDrone drone
    }
}
