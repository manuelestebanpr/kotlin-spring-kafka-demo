package com.springframework.kotlin.repository

import com.springframework.kotlin.entity.Truck
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID
import org.springframework.stereotype.Repository

@Repository
interface TruckRepository : JpaRepository<Truck, UUID>
