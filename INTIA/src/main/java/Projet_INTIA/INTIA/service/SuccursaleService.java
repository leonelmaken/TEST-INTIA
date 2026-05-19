package Projet_INTIA.INTIA.service;

import Projet_INTIA.INTIA.model.Succursale;
import java.util.List;

public interface SuccursaleService {

    List<Succursale> findAll();

    Succursale findById(Long id);

    Succursale create(Succursale succursale);

    Succursale update(Long id, Succursale succursale);

    void delete(Long id);
}