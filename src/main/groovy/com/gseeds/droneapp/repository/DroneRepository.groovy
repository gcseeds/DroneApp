package com.gseeds.droneapp.repository

import com.gseeds.droneapp.model.entity.Drone
import com.gseeds.droneapp.model.enums.SensorType
import com.gseeds.droneapp.model.enums.Status
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface DroneRepository extends JpaRepository<Drone, Integer>, JpaSpecificationExecutor<Drone> {
    List<Drone> findAll()

    Optional<Drone> findByRegistration(String registration)
    List<Drone> findAllByStatus(Status status)

    List<Drone> findAllByModelName(String modelName)

    @Query('''SELECT d FROM Drone d 
INNER JOIN Sensor s ON s.droneId = d.id 
INNER JOIN Model m ON m.id = d.model.id
WHERE (:sVal is null or d.status = :sVal) 
AND (:sType is null or s.sensorType = :sType)
AND (:nVal is null or m.name = :nVal)
AND (:maxW is null or d.weightKg <= :maxW)
AND (:minW is null or d.weightKg >= :minW)''')
    List<Drone> findAllByStatusAndSensorTypeAndModelNameWeight(@Param("sVal") Status status,
                                                               @Param("sType") SensorType sensorType,
                                                               @Param("nVal") String modelName,
                                                               @Param('maxW') BigDecimal maxWeight,
                                                               @Param('minW') BigDecimal minWeight);
    deleteByRegistration(String registration)
}