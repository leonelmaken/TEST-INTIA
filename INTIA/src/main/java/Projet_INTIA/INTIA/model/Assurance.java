package Projet_INTIA.INTIA.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

/**
 * ENTITÉ ASSURANCE
 */
@Entity
@Table(name = "assurances")
public class Assurance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "type_assurance", nullable = false)
    private String typeAssurance;

    @NotNull
    @Positive
    private Double montant;

    @NotNull
    private LocalDate dateDebut;

    @NotNull
    private LocalDate dateFin;

    /**
     * ❌ On coupe la boucle ici
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    @JsonIgnoreProperties({"assurances", "succursale"})
    private Client client;

    public Assurance() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTypeAssurance() { return typeAssurance; }
    public void setTypeAssurance(String typeAssurance) { this.typeAssurance = typeAssurance; }

    public Double getMontant() { return montant; }
    public void setMontant(Double montant) { this.montant = montant; }

    public LocalDate getDateDebut() { return dateDebut; }
    public void setDateDebut(LocalDate dateDebut) { this.dateDebut = dateDebut; }

    public LocalDate getDateFin() { return dateFin; }
    public void setDateFin(LocalDate dateFin) { this.dateFin = dateFin; }

    public Client getClient() { return client; }
    public void setClient(Client client) { this.client = client; }
}