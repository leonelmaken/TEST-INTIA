package Projet_INTIA.INTIA.controller;

import Projet_INTIA.INTIA.dto.AssuranceDTO;
import Projet_INTIA.INTIA.model.Assurance;
import Projet_INTIA.INTIA.service.AssuranceService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * =========================================================
 * CONTROLLER : ASSURANCE
 * =========================================================
 */
@RestController
@RequestMapping("/api/assurances")
@CrossOrigin("*")
public class AssuranceController {

    private final AssuranceService assuranceService;

    public AssuranceController(AssuranceService assuranceService) {
        this.assuranceService = assuranceService;
    }

    @GetMapping
    public ResponseEntity<List<Assurance>> getAll() {
        return ResponseEntity.ok(assuranceService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Assurance> getById(@PathVariable Long id) {
        return ResponseEntity.ok(assuranceService.findById(id));
    }

    @GetMapping("/client/{id}")
    public ResponseEntity<List<Assurance>> getByClient(@PathVariable Long id) {
        return ResponseEntity.ok(assuranceService.findByClient(id));
    }

    @GetMapping("/expirees")
    public ResponseEntity<List<Assurance>> getExpirees() {
        return ResponseEntity.ok(assuranceService.findExpirees());
    }

    @PostMapping
    public ResponseEntity<Assurance> create(@Valid @RequestBody AssuranceDTO dto) {
        return ResponseEntity.status(201).body(assuranceService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Assurance> update(@PathVariable Long id,
                                           @Valid @RequestBody AssuranceDTO dto) {
        return ResponseEntity.ok(assuranceService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        assuranceService.delete(id);
        return ResponseEntity.noContent().build();
    }
}