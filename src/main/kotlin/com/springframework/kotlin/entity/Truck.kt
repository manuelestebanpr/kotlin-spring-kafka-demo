package com.springframework.kotlin.entity

import jakarta.persistence.*
import java.util.UUID

@Entity
@Table(name = "trucks")
class Truck(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: UUID? = null,

    @Column(nullable = false)
    var longitude: Double,

    @Column(nullable = false)
    var latitude: Double,

    @Column(nullable = false)
    var numberOfPassengers: Int
) {
    @ManyToMany(cascade = [CascadeType.PERSIST, CascadeType.MERGE])
    @JoinTable(
        name = "truck_commodities",
        joinColumns = [JoinColumn(name = "truck_id")],
        inverseJoinColumns = [JoinColumn(name = "commodity_id")]
    )
    var commodities: MutableSet<Commodity> = mutableSetOf()

}
