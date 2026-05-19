package Projet_INTIA.INTIA.controller;

import Projet_INTIA.INTIA.model.Succursale;
import Projet_INTIA.INTIA.service.SuccursaleService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * =========================================================
 * CONTROLLER : SUCCURSALE
 * =========================================================
 */
@RestController
@RequestMapping("/api/succursales")
@CrossOrigin("*")
public class SuccursaleController {

    private final SuccursaleService succursaleService;

    public SuccursaleController(SuccursaleService succursaleService) {
        this.succursaleService = succursaleService;
    }

    @GetMapping
    public ResponseEntity<List<Succursale>> getAll() {
        return ResponseEntity.ok(succursaleService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Succursale> getById(@PathVariable Long id) {
        return ResponseEntity.ok(succursaleService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Succursale> create(@Valid @RequestBody Succursale succursale) {
        return ResponseEntity.status(201).body(succursaleService.create(succursale));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Succursale> update(@PathVariable Long id,
                                             @Valid @RequestBody Succursale succursale) {
        return ResponseEntity.ok(succursaleService.update(id, succursale));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        succursaleService.delete(id);
        return ResponseEntity.noContent().build();
    }
}