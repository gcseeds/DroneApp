package com.gseeds.droneapp.repository

import com.gseeds.droneapp.entity.Model
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ModelRepository extends JpaRepository<Model, Integer>{
    Optional<Model> findByNameIgnoreCase(String name)
}