package Projet_INTIA.INTIA.repository;
import Projet_INTIA.INTIA.model.Succursale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SuccursaleRepository extends JpaRepository<Succursale, Long> {
    Optional<Succursale> findByNom(String nom);
    boolean existsByNomAndVille(String nom, String ville);
}