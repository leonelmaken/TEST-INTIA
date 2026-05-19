package Projet_INTIA.INTIA.controller;

import Projet_INTIA.INTIA.dto.ClientDTO;
import Projet_INTIA.INTIA.model.Client;
import Projet_INTIA.INTIA.service.ClientService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * =========================================================
 * CONTROLLER : CLIENT
 * =========================================================
 * Expose les APIs REST pour la gestion des clients
 * =========================================================
 */
@RestController
@RequestMapping("/api/clients")
@CrossOrigin("*")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    // GET ALL
    @GetMapping
    public ResponseEntity<List<Client>> getAll() {
        return ResponseEntity.ok(clientService.findAll());
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Client> getById(@PathVariable Long id) {
        return ResponseEntity.ok(clientService.findById(id));
    }

    // GET BY SUCCURSALE
    @GetMapping("/succursale/{id}")
    public ResponseEntity<List<Client>> getBySuccursale(@PathVariable Long id) {
        return ResponseEntity.ok(clientService.findBySuccursale(id));
    }

    // SEARCH
    @GetMapping("/search")
    public ResponseEntity<List<Client>> search(@RequestParam String query) {
        return ResponseEntity.ok(clientService.search(query));
    }

    // CREATE
    @PostMapping
    public ResponseEntity<Client> create(@Valid @RequestBody ClientDTO dto) {
        return ResponseEntity.ok(clientService.create(dto));
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Client> update(@PathVariable Long id,
                                         @Valid @RequestBody ClientDTO dto) {
        return ResponseEntity.ok(clientService.update(id, dto));
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        clientService.delete(id);
        return ResponseEntity.noContent().build();
    }
}