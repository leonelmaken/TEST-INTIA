package Projet_INTIA.INTIA.service;

import Projet_INTIA.INTIA.dto.ClientDTO;
import Projet_INTIA.INTIA.model.Client;

import java.util.List;

/**
 * =========================================================
 * SERVICE : Client
 * =========================================================
 *
 * Rôle :
 *  - Définir les opérations métier sur les clients
 *  - CRUD + recherche + filtrage par succursale
 *
 * INTIA REQUIREMENTS :
 *  - Ajouter / Modifier / Supprimer / Consulter clients
 * =========================================================
 */
public interface ClientService {

    List<Client> findAll();

    Client findById(Long id);

    List<Client> findBySuccursale(Long succursaleId);

    List<Client> search(String query);

    Client create(ClientDTO dto);

    Client update(Long id, ClientDTO dto);

    void delete(Long id);
}