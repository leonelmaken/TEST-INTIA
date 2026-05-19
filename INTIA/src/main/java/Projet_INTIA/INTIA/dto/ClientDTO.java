package Projet_INTIA.INTIA.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/**
 * DTO CLIENT
 * Sert à transporter les données entre frontend et backend
 */
public class ClientDTO {

    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private String adresse;
    private Long succursaleId;

    // ================= CONSTRUCTEUR =================
    public ClientDTO() {}

    public ClientDTO(String nom, String prenom, String email,
                     String telephone, String adresse, Long succursaleId) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
        this.adresse = adresse;
        this.succursaleId = succursaleId;
    }

    // ================= GETTERS / SETTERS =================

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTelephone() { return telephone; }
    public void setTelephone(String telephone) { this.telephone = telephone; }

    public String getAdresse() { return adresse; }
    public void setAdresse(String adresse) { this.adresse = adresse; }

    public Long getSuccursaleId() { return succursaleId; }
    public void setSuccursaleId(Long succursaleId) { this.succursaleId = succursaleId; }
}