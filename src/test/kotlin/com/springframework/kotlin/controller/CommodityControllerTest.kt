package com.springframework.kotlin.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.springframework.kotlin.dto.CommodityDto
import com.springframework.kotlin.facade.CommodityFacade
import com.springframework.kotlin.request.CommodityRequest
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

@WebMvcTest(CommodityController::class)
class CommodityControllerTest @Autowired constructor(private val mockMvc: MockMvc) {

    @MockitoBean
    private lateinit var commodityFacade: CommodityFacade

    private val objectMapper = ObjectMapper()

    @Test
    fun `should get all commodities`() {
        val commodity = CommodityDto(UUID.randomUUID(), "Test")
        `when`(commodityFacade.getAll()).thenReturn(listOf(commodity))

        mockMvc.perform(get(CommodityController.BASE_URL))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$[0].name").value("Test"))
    }

    @Test
    fun `should create commodity`() {
        val request = CommodityRequest("New")
        val response = CommodityDto(UUID.randomUUID(), "New")
        `when`(commodityFacade.create(request)).thenReturn(response)

        mockMvc.perform(post(CommodityController.BASE_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isCreated)
            .andExpect(jsonPath("$.name").value("New"))
    }
}
