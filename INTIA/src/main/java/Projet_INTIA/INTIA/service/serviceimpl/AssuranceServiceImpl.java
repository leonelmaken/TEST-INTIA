package Projet_INTIA.INTIA.service.serviceimpl;

import Projet_INTIA.INTIA.dto.AssuranceDTO;
import Projet_INTIA.INTIA.model.Assurance;
import Projet_INTIA.INTIA.model.Client;
import Projet_INTIA.INTIA.repository.AssuranceRepository;
import Projet_INTIA.INTIA.repository.ClientRepository;
import Projet_INTIA.INTIA.service.AssuranceService;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

/**
 * =========================================================
 * SERVICE : ASSURANCE
 * =========================================================
 * Gestion des contrats d’assurance :
 * - CRUD
 * - validation des dates
 * - lien avec client
 * =========================================================
 */
@Service
public class AssuranceServiceImpl implements AssuranceService {

    // Logger manuel (remplace @Slf4j Lombok)
    private static final Logger log =
            LoggerFactory.getLogger(AssuranceServiceImpl.class);

    private final AssuranceRepository assuranceRepository;
    private final ClientRepository clientRepository;

    /**
     * Constructeur explicite
     * (évite les erreurs Lombok + injection Spring)
     */
    public AssuranceServiceImpl(AssuranceRepository assuranceRepository,
                                ClientRepository clientRepository) {
        this.assuranceRepository = assuranceRepository;
        this.clientRepository = clientRepository;
    }

    @Override
    public List<Assurance> findAll() {
        log.info("Récupération de toutes les assurances");
        return assuranceRepository.findAll();
    }

    @Override
    public Assurance findById(Long id) {
        log.info("Recherche assurance id={}", id);

        return assuranceRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Assurance introuvable id=" + id));
    }

    @Override
    public List<Assurance> findByClient(Long clientId) {
        log.info("Recherche assurances client id={}", clientId);

        return assuranceRepository.findByClientId(clientId);
    }

    @Override
    public List<Assurance> findExpirees() {
        log.info("Recherche assurances expirées");

        return assuranceRepository.findByDateFinBefore(LocalDate.now());
    }

    @Override
    @Transactional
    public Assurance create(AssuranceDTO dto) {

        log.info("Création assurance type={} clientId={}",
                dto.getTypeAssurance(), dto.getClientId());

        validerDates(dto.getDateDebut(), dto.getDateFin());

        Client client = resolveClient(dto.getClientId());

        Assurance assurance = new Assurance();
        assurance.setTypeAssurance(dto.getTypeAssurance());
        assurance.setMontant(dto.getMontant());
        assurance.setDateDebut(dto.getDateDebut());
        assurance.setDateFin(dto.getDateFin());
        assurance.setClient(client);

        return assuranceRepository.save(assurance);
    }

    @Override
    @Transactional
    public Assurance update(Long id, AssuranceDTO dto) {

        Assurance existing = findById(id);

        log.info("Mise à jour assurance id={}", id);

        validerDates(dto.getDateDebut(), dto.getDateFin());

        existing.setTypeAssurance(dto.getTypeAssurance());
        existing.setMontant(dto.getMontant());
        existing.setDateDebut(dto.getDateDebut());
        existing.setDateFin(dto.getDateFin());
        existing.setClient(resolveClient(dto.getClientId()));

        return assuranceRepository.save(existing);
    }

    @Override
    @Transactional
    public void delete(Long id) {

        Assurance assurance = findById(id);

        log.warn("Suppression assurance id={} type={}",
                id, assurance.getTypeAssurance());

        assuranceRepository.delete(assurance);
    }

    /**
     * =========================================================
     * Méthode utilitaire : vérifie l’existence du client
     * =========================================================
     */
    private Client resolveClient(Long clientId) {

        return clientRepository.findById(clientId)
                .orElseThrow(() ->
                        new EntityNotFoundException("Client introuvable id=" + clientId));
    }

    /**
     * =========================================================
     * Règle métier : validation des dates assurance
     * =========================================================
     * - date début obligatoire
     * - date fin obligatoire
     * - date fin > date début
     */
    private void validerDates(LocalDate debut, LocalDate fin) {

        if (debut == null || fin == null) {
            throw new IllegalArgumentException(
                    "Les dates de début et fin sont obligatoires");
        }

        if (!fin.isAfter(debut)) {
            throw new IllegalArgumentException(
                    "La date de fin doit être après la date de début");
        }
    }
}