package com.springframework.kotlin.repository

import com.springframework.kotlin.entity.Commodity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID
import org.springframework.stereotype.Repository

@Repository
interface CommodityRepository : JpaRepository<Commodity, UUID>
