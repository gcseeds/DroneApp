package com.gseeds.droneapp.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table

@Entity
@Table(name = 'model')
class Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id

    @Column
    String name

    @Column
    String manufacturer
}
