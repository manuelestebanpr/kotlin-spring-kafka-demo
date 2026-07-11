package com.springframework.kotlin.facade

import com.springframework.kotlin.dto.TruckDto
import com.springframework.kotlin.service.TruckService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.bean.override.mockito.MockitoBean
import java.util.*

@SpringBootTest
class FacadeTests @Autowired constructor(
    val truckFacade: TruckFacade
) {

    @MockitoBean
    lateinit var truckService: TruckService

    @Test
    fun `facade should call service`() {
        val truckDto = TruckDto(longitude = 1.0, latitude = 2.0, numberOfPassengers = 3)
        `when`(truckService.getAll()).thenReturn(listOf(truckDto))

        val result = truckFacade.getAll()

        assertEquals(1, result.size)
        verify(truckService).getAll()
    }
}
