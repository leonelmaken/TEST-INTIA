package Projet_INTIA.INTIA.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "succursales")
public class Succursale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String ville;

    // 🔥 STOP boucle infinie
    @OneToMany(mappedBy = "succursale", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("succursale")
    private List<Client> clients;

    public Succursale() {}

    public Succursale(Long id, String nom, String ville, List<Client> clients) {
        this.id = id;
        this.nom = nom;
        this.ville = ville;
        this.clients = clients;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getVille() { return ville; }
    public void setVille(String ville) { this.ville = ville; }

    public List<Client> getClients() { return clients; }
    public void setClients(List<Client> clients) { this.clients = clients; }
}