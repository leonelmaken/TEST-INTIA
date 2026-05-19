package Projet_INTIA.INTIA.service.serviceimpl;

import Projet_INTIA.INTIA.model.Succursale;
import Projet_INTIA.INTIA.repository.SuccursaleRepository;
import Projet_INTIA.INTIA.service.SuccursaleService;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * =========================================================
 * SERVICE : SUCCURSALE
 * =========================================================
 * Gestion des agences INTIA
 * =========================================================
 */
@Service
public class SuccursaleServiceImpl implements SuccursaleService {

    private static final Logger log =
            LoggerFactory.getLogger(SuccursaleServiceImpl.class);

    private final SuccursaleRepository succursaleRepository;

    public SuccursaleServiceImpl(SuccursaleRepository succursaleRepository) {
        this.succursaleRepository = succursaleRepository;
    }

    @Override
    public List<Succursale> findAll() {
        log.info("Récupération toutes succursales");
        return succursaleRepository.findAll();
    }

    @Override
    public Succursale findById(Long id) {
        log.info("Recherche succursale id={}", id);

        return succursaleRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Succursale introuvable id=" + id));
    }

    @Override
    @Transactional
    public Succursale create(Succursale s) {

        if (succursaleRepository.existsByNomAndVille(s.getNom(), s.getVille())) {
            throw new IllegalArgumentException(
                    "Succursale déjà existante dans cette ville");
        }

        log.info("Création succursale {} - {}", s.getNom(), s.getVille());
        return succursaleRepository.save(s);
    }

    @Override
    @Transactional
    public Succursale update(Long id, Succursale s) {

        Succursale existing = findById(id);

        existing.setNom(s.getNom());
        existing.setVille(s.getVille());

        log.info("Mise à jour succursale id={}", id);

        return succursaleRepository.save(existing);
    }

    @Override
    @Transactional
    public void delete(Long id) {

        Succursale s = findById(id);

        log.warn("Suppression succursale id={}", id);

        succursaleRepository.delete(s);
    }
}