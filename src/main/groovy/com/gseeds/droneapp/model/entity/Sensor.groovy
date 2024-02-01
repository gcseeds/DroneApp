package com.gseeds.droneapp.model.entity

import com.gseeds.droneapp.model.enums.SensorType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = 'sensor')
class Sensor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id

    @Column
    String name

    @Enumerated(EnumType.STRING)
    SensorType sensorType

    @Column
    Integer droneId
}
