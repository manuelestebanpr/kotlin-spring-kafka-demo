package com.springframework.kotlin.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.springframework.kotlin.dto.TruckDto
import com.springframework.kotlin.facade.TruckFacade
import com.springframework.kotlin.request.TruckRequest
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.context.bean.override.mockito.MockitoBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.util.*

@WebMvcTest(TruckController::class)
class TruckControllerTest @Autowired constructor(private val mockMvc: MockMvc) {

    @MockitoBean
    private lateinit var truckFacade: TruckFacade

    private val objectMapper = ObjectMapper()

    @Test
    fun `should get all trucks`() {
        val truck = TruckDto(UUID.randomUUID(), 1.0, 2.0, 1)
        `when`(truckFacade.getAll()).thenReturn(listOf(truck))

        mockMvc.perform(get(TruckController.BASE_URL))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$[0].longitude").value(1.0))
    }

    @Test
    fun `should create truck`() {
        val request = TruckRequest(10.0, 20.0, 2)
        val response = TruckDto(UUID.randomUUID(), 10.0, 20.0, 2)
        `when`(truckFacade.create(request)).thenReturn(response)

        mockMvc.perform(post(TruckController.BASE_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isCreated)
            .andExpect(jsonPath("$.longitude").value(10.0))
    }
}
