package com.springframework.kotlin.geo

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.core.io.ClassPathResource
import java.io.File

@SpringBootTest
class GeoDataTest {

    @Test
    fun `should load us-states geojson`() {
        val resource = ClassPathResource("static/geo/us-states.json")
        assertTrue(resource.exists(), "us-states.json should exist in static/geo")
        
        val content = resource.inputStream.bufferedReader().use { it.readText() }
        assertTrue(content.contains("FeatureCollection"), "Should be a valid GeoJSON")
        assertTrue(content.contains("Alabama"), "Should contain Alabama")
    }
}
