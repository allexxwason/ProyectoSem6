package com.proyectosem6.controller;
import com.proyectosem6.dto.EventDTO;
import com.proyectosem6.service.EventService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/events")
public class EventController {
    private final EventService service;
    public EventController(EventService service) { this.service = service; }
    @GetMapping public ResponseEntity<List<EventDTO>> getAll() { return ResponseEntity.ok(service.getAllEvents()); }
    @GetMapping("/{id}") public ResponseEntity<EventDTO> getById(@PathVariable Long id) {
        return service.getEventById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build()); }
    @PostMapping public ResponseEntity<EventDTO> create(@RequestBody EventDTO e) {
        if (e.getName()==null || e.getName().isBlank()) return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(service.createEvent(e)); }
    @PutMapping("/{id}") public ResponseEntity<EventDTO> update(@PathVariable Long id, @RequestBody EventDTO e) {
        return service.updateEvent(id,e).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build()); }
    @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id) {
        return service.deleteEvent(id)?ResponseEntity.noContent().build():ResponseEntity.notFound().build(); }
}