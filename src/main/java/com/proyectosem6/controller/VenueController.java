package com.proyectosem6.controller;
import com.proyectosem6.dto.VenueDTO;
import com.proyectosem6.service.VenueService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/venues")
public class VenueController {
    private final VenueService service;
    public VenueController(VenueService service) { this.service = service; }
    @GetMapping public ResponseEntity<List<VenueDTO>> getAll() { return ResponseEntity.ok(service.getAllVenues()); }
    @GetMapping("/{id}") public ResponseEntity<VenueDTO> getById(@PathVariable Long id) {
        return service.getVenueById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build()); }
    @PostMapping public ResponseEntity<VenueDTO> create(@RequestBody VenueDTO v) {
        if (v.getName()==null || v.getName().isBlank()) return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(service.createVenue(v)); }
    @PutMapping("/{id}") public ResponseEntity<VenueDTO> update(@PathVariable Long id, @RequestBody VenueDTO v) {
        return service.updateVenue(id,v).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build()); }
    @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id) {
        return service.deleteVenue(id)?ResponseEntity.noContent().build():ResponseEntity.notFound().build(); }
}