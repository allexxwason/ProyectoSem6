package com.proyectosem6.controller;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class EventController {

    @GetMapping("/events")
    public String getEvents() {
        return "Listado de eventos desde memoria temporal";
    }
}
