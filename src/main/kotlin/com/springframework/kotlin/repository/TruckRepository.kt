package com.springframework.kotlin.repository

import com.springframework.kotlin.entity.Truck
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.CrudRepository
import java.util.Optional
import java.util.UUID

interface TruckRepository : JpaRepository<Truck, UUID>
