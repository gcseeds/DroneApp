package com.gseeds.droneapp.controller

import com.gseeds.droneapp.model.dto.DroneDto
import com.gseeds.droneapp.model.dto.DroneStatusDto
import com.gseeds.droneapp.model.dto.ModelDto
import com.gseeds.droneapp.model.dto.SensorDto
import com.gseeds.droneapp.model.enums.SensorType
import com.gseeds.droneapp.model.enums.Status
import com.gseeds.droneapp.service.DroneService
import com.gseeds.droneapp.service.ModelService
import com.gseeds.droneapp.service.SensorService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException
import org.springframework.http.HttpStatus

@CrossOrigin(origins = "http://localhost")
@RestController
@RequestMapping('droneapp/api')
class DroneController {
    @Autowired
    DroneService service

    @Autowired
    ModelService modelService

    @Autowired
    SensorService sensorService

    @GetMapping('/drones')
    List<DroneDto> getDrones(@RequestParam(name = "status", required = false) Status status,
                             @RequestParam(name = "sensorType", required = false) SensorType sensorType,
                             @RequestParam(name = "modelName",required = false) String modelName,
                             @RequestParam(name = "maxWeight",required = false) BigDecimal maxWeight,
                             @RequestParam(name = "minWeight",required = false) BigDecimal minWeight){
        service.findByStatusSensorTypeModelNameWeight(status, sensorType, modelName, maxWeight, minWeight)
    }

    @GetMapping('/drones/{droneRegistration}')
    DroneDto getDroneByRegistration(@PathVariable(name = 'droneRegistration', required = true) String droneRegistration){
        try{
            service.findByRegistration(droneRegistration)
        }
        catch (NoSuchElementException noSuchElementException){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Drone not found", noSuchElementException)
        }
    }

    @PostMapping('/drones')
    DroneDto saveDrone(@RequestBody DroneDto drone){
        service.saveDrone drone
    }

    @PatchMapping('/drones/{droneRegistration}')
    DroneDto updateDrone(@RequestBody DroneDto drone,
                         @PathVariable(name = 'droneRegistration') String droneRegistration){
        service.updateDrone(droneRegistration, drone)
    }

    @DeleteMapping('/drones/{droneRegistration}')
    deleteDrone(@PathVariable(name = 'droneRegistration') String droneRegistration) {
        service.deleteDrone(droneRegistration)
    }

    @GetMapping('/drones/{droneRegistration}/status')
    DroneStatusDto getDroneStatus(@PathVariable(name = 'droneRegistration') String droneRegistration){
        service.getDroneStatus(droneRegistration)
    }

    @PatchMapping('/drones/{droneRegistration}/status')
    DroneStatusDto updateDroneStatus(@RequestBody DroneStatusDto statusDto,
                                     @PathVariable(name = 'droneRegistration') String droneRegistration){
        service.updateDroneStatus(droneRegistration, statusDto)
    }

    @GetMapping('/drones/{droneRegistration}/sensors')
    List<SensorDto> getDroneSensors(@PathVariable(name = 'droneRegistration') String droneRegistration){
        service.findByRegistration(droneRegistration).sensors
    }

    @PostMapping('/drones/{droneRegistration}/sensors')
    List<SensorDto> postDroneSensor(@RequestBody SensorDto sensorDto,
                                    @PathVariable(name = 'droneRegistration') String droneRegistration){
        sensorService.saveSensor(droneRegistration, sensorDto)
    }

    @DeleteMapping('/drones/{droneRegistration}/sensors/{sensorName}')
    deleteSensor(@PathVariable(name = 'droneRegistration') String droneRegistration,
                 @PathVariable(name = 'sensorName') String sensorName){
        sensorService.deleteSensor(droneRegistration, sensorName)
    }

    @GetMapping('/statusTypes')
    List<String> getAllStatusTypes(){
        Status.values().toList().stream().map {it.toString()}.toList()
    }

    @GetMapping('/sensorTypes')
    List<String> getAllSensorTypes(){
        SensorType.values().toList().stream().map {it.toString()}.toList()
    }

    @GetMapping('/models')
    List<ModelDto> getAllModels(){
        modelService.findAllDto()
    }

    @ExceptionHandler([NoSuchElementException.class])
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ResponseEntity<String> handleNoSuchElementException(NoSuchElementException exception) {
        ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage())
    }
}
