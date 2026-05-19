package Projet_INTIA.INTIA.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String prenom;

    @Column(unique = true)
    private String email;

    private String telephone;
    private String adresse;

    // 🔥 STOP BOUCLE : on ignore clients côté succursale
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "succursale_id")
    @JsonIgnoreProperties("clients")
    private Succursale succursale;

    // 🔥 STOP BOUCLE : on ignore client dans assurance
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("client")
    private List<Assurance> assurances;

    public Client() {}

    public Client(Long id, String nom, String prenom, String email,
                  String telephone, String adresse,
                  Succursale succursale, List<Assurance> assurances) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
        this.adresse = adresse;
        this.succursale = succursale;
        this.assurances = assurances;
    }

    // ================= GETTERS / SETTERS =================
    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

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

    public Succursale getSuccursale() { return succursale; }
    public void setSuccursale(Succursale succursale) { this.succursale = succursale; }

    public List<Assurance> getAssurances() { return assurances; }
    public void setAssurances(List<Assurance> assurances) { this.assurances = assurances; }
}