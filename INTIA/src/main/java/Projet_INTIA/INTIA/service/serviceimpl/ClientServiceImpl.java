package Projet_INTIA.INTIA.service.serviceimpl;

import Projet_INTIA.INTIA.dto.ClientDTO;
import Projet_INTIA.INTIA.model.Client;
import Projet_INTIA.INTIA.model.Succursale;
import Projet_INTIA.INTIA.repository.ClientRepository;
import Projet_INTIA.INTIA.repository.SuccursaleRepository;
import Projet_INTIA.INTIA.service.ClientService;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * =========================================================
 * SERVICE : CLIENT
 * =========================================================
 * Gestion complète des clients :
 * - CRUD
 * - validation email unique
 * - liaison succursale
 * =========================================================
 */
@Service
public class ClientServiceImpl implements ClientService {

    private static final Logger log =
            LoggerFactory.getLogger(ClientServiceImpl.class);

    private final ClientRepository clientRepository;
    private final SuccursaleRepository succursaleRepository;

    // Constructeur explicite (évite erreur injection Lombok)
    public ClientServiceImpl(ClientRepository clientRepository,
                             SuccursaleRepository succursaleRepository) {
        this.clientRepository = clientRepository;
        this.succursaleRepository = succursaleRepository;
    }

    @Override
    public List<Client> findAll() {
        log.info("Récupération de tous les clients");
        return clientRepository.findAll();
    }

    @Override
    public Client findById(Long id) {
        log.info("Recherche client id={}", id);

        return clientRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Client introuvable id=" + id));
    }

    @Override
    public List<Client> findBySuccursale(Long succursaleId) {
        log.info("Clients succursale id={}", succursaleId);
        return clientRepository.findBySuccursaleId(succursaleId);
    }

    @Override
    public List<Client> search(String query) {
        log.info("Recherche client : {}", query);

        return clientRepository
                .findByNomContainingIgnoreCaseOrPrenomContainingIgnoreCase(query, query);
    }

    @Override
    @Transactional
    public Client create(ClientDTO dto) {

        log.info("Création client {} {}", dto.getNom(), dto.getPrenom());

        if (clientRepository.existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException("Email déjà utilisé : " + dto.getEmail());
        }

        Succursale succursale = resolveSuccursale(dto.getSuccursaleId());

        Client client = new Client();
        client.setNom(dto.getNom());
        client.setPrenom(dto.getPrenom());
        client.setEmail(dto.getEmail());
        client.setTelephone(dto.getTelephone());
        client.setAdresse(dto.getAdresse());
        client.setSuccursale(succursale);

        return clientRepository.save(client);
    }

    @Override
    @Transactional
    public Client update(Long id, ClientDTO dto) {

        Client existing = findById(id);

        log.info("Mise à jour client id={}", id);

        if (!existing.getEmail().equals(dto.getEmail())
                && clientRepository.existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException("Email déjà utilisé : " + dto.getEmail());
        }

        existing.setNom(dto.getNom());
        existing.setPrenom(dto.getPrenom());
        existing.setEmail(dto.getEmail());
        existing.setTelephone(dto.getTelephone());
        existing.setAdresse(dto.getAdresse());
        existing.setSuccursale(resolveSuccursale(dto.getSuccursaleId()));

        return clientRepository.save(existing);
    }

    @Override
    @Transactional
    public void delete(Long id) {

        Client client = findById(id);

        log.warn("Suppression client id={} email={}", id, client.getEmail());

        clientRepository.delete(client);
    }

    private Succursale resolveSuccursale(Long id) {
        if (id == null) return null;

        return succursaleRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Succursale introuvable id=" + id));
    }
}