package com.springframework.kotlin.service

import com.springframework.kotlin.dto.CommodityDto
import com.springframework.kotlin.entity.Commodity
import com.springframework.kotlin.repository.CommodityRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.bean.override.mockito.MockitoBean
import java.util.*

@SpringBootTest
class CommodityServiceTests @Autowired constructor(
    val commodityService: CommodityService
) {

    @MockitoBean
    lateinit var commodityRepository: CommodityRepository

    @Test
    fun `should create commodity`() {
        val dto = CommodityDto(name = "Oil")
        val entity = Commodity(id = UUID.randomUUID(), name = "Oil")
        
        `when`(commodityRepository.save(any(Commodity::class.java))).thenReturn(entity)

        val result = commodityService.create(dto)

        assertEquals("Oil", result.name)
        verify(commodityRepository).save(any(Commodity::class.java))
    }

    @Test
    fun `should update commodity`() {
        val id = UUID.randomUUID()
        val existing = Commodity(id = id, name = "Old Name")
        val dto = CommodityDto(id = id, name = "New Name")
        
        `when`(commodityRepository.findById(id)).thenReturn(Optional.of(existing))
        `when`(commodityRepository.save(any(Commodity::class.java))).thenReturn(existing)

        val result = commodityService.update(id, dto)

        assertEquals("New Name", result?.name)
        verify(commodityRepository).save(existing)
    }
}
