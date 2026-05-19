package Projet_INTIA.INTIA.service;

import Projet_INTIA.INTIA.dto.AssuranceDTO;
import Projet_INTIA.INTIA.model.Assurance;
import java.util.List;

public interface AssuranceService {

    List<Assurance> findAll();

    Assurance findById(Long id);

    List<Assurance> findByClient(Long clientId);

    List<Assurance> findExpirees();

    Assurance create(AssuranceDTO dto);

    Assurance update(Long id, AssuranceDTO dto);

    void delete(Long id);
}