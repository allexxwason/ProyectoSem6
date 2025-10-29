package com.proyectosem6.controller;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class VenueController {

    @GetMapping("/venues")
    public String getVenues() {
        return "Listado de venues desde memoria temporal";
    }
}
