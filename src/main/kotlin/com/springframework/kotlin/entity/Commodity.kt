package com.springframework.kotlin.entity

import jakarta.persistence.*
import java.util.UUID

@Entity
@Table(name = "commodities")
class Commodity(
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: UUID? = null,

    @Column(nullable = false)
    var name: String
) {
    @ManyToMany(mappedBy = "commodities")
    var trucks: MutableSet<Truck> = mutableSetOf()
}
