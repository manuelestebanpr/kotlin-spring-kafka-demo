package com.springframework.kotlin.controller

import com.springframework.kotlin.dto.CommodityDto
import com.springframework.kotlin.facade.CommodityFacade
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

@WebMvcTest(CommodityController::class)
class CommodityControllerTests @Autowired constructor(
    val mockMvc: MockMvc,
    val objectMapper: ObjectMapper
) {

    @MockitoBean
    lateinit var commodityFacade: CommodityFacade

    @Test
    fun `should get all commodities`() {
        val dto = CommodityDto(id = UUID.randomUUID(), name = "Oil")
        `when`(commodityFacade.getAll()).thenReturn(listOf(dto))

        mockMvc.perform(get("/api/commodities"))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$[0].name").value("Oil"))
    }

    @Test
    fun `should create commodity`() {
        val dto = CommodityDto(name = "Oil")
        `when`(commodityFacade.create(dto)).thenReturn(dto.copy(id = UUID.randomUUID()))

        mockMvc.perform(post("/api/commodities")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(dto)))
            .andExpect(status().isCreated)
            .andExpect(jsonPath("$.name").value("Oil"))
    }

    @Test
    fun `should update commodity`() {
        val id = UUID.randomUUID()
        val dto = CommodityDto(id = id, name = "Gas")
        `when`(commodityFacade.update(id, dto)).thenReturn(dto)

        mockMvc.perform(put("/api/commodities/$id")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(dto)))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.name").value("Gas"))
    }

    @Test
    fun `should delete commodity`() {
        val id = UUID.randomUUID()

        mockMvc.perform(delete("/api/commodities/$id"))
            .andExpect(status().isNoContent)
    }
}
