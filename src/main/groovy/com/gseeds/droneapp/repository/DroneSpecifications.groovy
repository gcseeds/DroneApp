package com.gseeds.droneapp.repository

import com.gseeds.droneapp.model.entity.Drone
import com.gseeds.droneapp.model.entity.Model
import com.gseeds.droneapp.model.entity.Sensor
import com.gseeds.droneapp.model.enums.SensorType
import com.gseeds.droneapp.model.enums.Status
import jakarta.persistence.criteria.Join
import org.springframework.data.jpa.domain.Specification

class DroneSpecifications {

    static Specification<Drone> hasStatus(Status status) {
        (root, query, cb) ->
                cb.equal(root.<Status>get("status"), status)
    }

    static Specification<Drone> hasModelName(String name){
        (root, query, criteriaBuilder) -> {
            Join<Drone, Model> dronesModel = root.join("model")
            criteriaBuilder.equal(dronesModel.get("name"), name)
        }
    }

    static Specification<Drone> hasSensorType(SensorType sensorType){
        (root, query, criteriaBuilder) -> {
            Join<Sensor, Drone> sensorDroneJoin = root.join("drone_id")
            criteriaBuilder.equal(sensorDroneJoin.get("sensor_type"), sensorType)
        }
    }
}
