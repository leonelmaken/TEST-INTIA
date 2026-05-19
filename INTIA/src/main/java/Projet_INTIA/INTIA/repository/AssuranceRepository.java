package Projet_INTIA.INTIA.repository;

import Projet_INTIA.INTIA.model.Assurance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AssuranceRepository extends JpaRepository<Assurance, Long> {
    List<Assurance> findByClientId(Long clientId);
    List<Assurance> findByDateFinBefore(LocalDate date);   // contrats expirés
    List<Assurance> findByTypeAssurance(String typeAssurance);
}