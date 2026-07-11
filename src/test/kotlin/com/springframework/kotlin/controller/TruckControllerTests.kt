package com.springframework.kotlin.controller

import com.springframework.kotlin.dto.TruckDto
import com.springframework.kotlin.facade.TruckFacade
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.context.bean.override.mockito.MockitoBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import tools.jackson.databind.ObjectMapper
import java.util.*

@WebMvcTest(TruckController::class)
class TruckControllerTests @Autowired constructor(
    val mockMvc: MockMvc,
    val objectMapper: ObjectMapper
) {

    @MockitoBean
    lateinit var truckFacade: TruckFacade

    @Test
    fun `should get all trucks`() {
        val truckDto = TruckDto(id = UUID.randomUUID(), longitude = 1.0, latitude = 2.0, numberOfPassengers = 3)
        `when`(truckFacade.getAll()).thenReturn(listOf(truckDto))

        mockMvc.perform(get("/api/trucks"))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$[0].longitude").value(1.0))
    }

    @Test
    fun `should create truck`() {
        val truckDto = TruckDto(longitude = 1.0, latitude = 2.0, numberOfPassengers = 3)
        `when`(truckFacade.create(truckDto)).thenReturn(truckDto)

        mockMvc.perform(post("/api/trucks")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(truckDto)))
            .andExpect(status().isCreated)
            .andExpect(jsonPath("$.longitude").value(1.0))
    }

    @Test
    fun `should update truck`() {
        val id = UUID.randomUUID()
        val truckDto = TruckDto(id = id, longitude = 10.0, latitude = 20.0, numberOfPassengers = 5)
        `when`(truckFacade.update(id, truckDto)).thenReturn(truckDto)

        mockMvc.perform(put("/api/trucks/$id")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(truckDto)))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.longitude").value(10.0))
    }

    @Test
    fun `should delete truck`() {
        val id = UUID.randomUUID()
        
        mockMvc.perform(delete("/api/trucks/$id"))
            .andExpect(status().isNoContent)
    }
}
