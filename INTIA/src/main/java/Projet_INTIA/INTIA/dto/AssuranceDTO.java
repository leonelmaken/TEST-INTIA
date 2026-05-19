package Projet_INTIA.INTIA.dto;

import java.time.LocalDate;

/**
 * DTO ASSURANCE
 */
public class AssuranceDTO {

    private String typeAssurance;
    private Double montant;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private Long clientId;

    public AssuranceDTO() {}

    public String getTypeAssurance() { return typeAssurance; }
    public void setTypeAssurance(String typeAssurance) { this.typeAssurance = typeAssurance; }

    public Double getMontant() { return montant; }
    public void setMontant(Double montant) { this.montant = montant; }

    public LocalDate getDateDebut() { return dateDebut; }
    public void setDateDebut(LocalDate dateDebut) { this.dateDebut = dateDebut; }

    public LocalDate getDateFin() { return dateFin; }
    public void setDateFin(LocalDate dateFin) { this.dateFin = dateFin; }

    public Long getClientId() { return clientId; }
    public void setClientId(Long clientId) { this.clientId = clientId; }
}