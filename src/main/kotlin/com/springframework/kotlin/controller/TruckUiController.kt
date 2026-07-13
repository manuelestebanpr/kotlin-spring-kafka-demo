package com.springframework.kotlin.controller

import com.springframework.kotlin.facade.TruckFacade
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class TruckUiController(private val truckFacade: TruckFacade) {

    @GetMapping("/")
    fun listTrucks(model: Model): String {
        model.addAttribute("trucks", truckFacade.getAll())
        return "trucks"
    }
}
