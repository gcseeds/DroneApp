package com.gseeds.droneapp.model.entity

import com.gseeds.droneapp.model.enums.Status
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp

@Entity
@Table(name = 'drone')
class Drone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id

    @Column
    String registration

    @ManyToOne
    @JoinColumn(name="model_id", nullable=false)
    Model model

    @Enumerated(EnumType.STRING)
    Status status

    @OneToMany(mappedBy = "droneId")
    List<Sensor> sensors;

    @Column
    BigDecimal latitude

    @Column
    BigDecimal longitude

    @Column
    BigDecimal weightKg

    @UpdateTimestamp
    @Column
    Date modifiedDate;

    @CreationTimestamp
    @Column
    Date createdDate;
}
