package com.springframework.kotlin.entity

import jakarta.persistence.*
import java.util.UUID

@Entity
@Table(name = "trucks")
class Truck(
    @Id
    val id: UUID = UUID.randomUUID(),

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

    // Second constructor as requested: "when updating the Truck with the commodities we use a second constructor that adds this with kotlin"
    constructor(
        id: UUID = UUID.randomUUID(),
        longitude: Double,
        latitude: Double,
        numberOfPassengers: Int,
        commodities: MutableSet<Commodity>
    ) : this(id, longitude, latitude, numberOfPassengers) {
        this.commodities = commodities
    }
}
