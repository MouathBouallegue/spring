package ma.fstm.ilisi2.projects.biblio.gestionbibliospringboot.Model.bo;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Adherent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_client")
    private Long idClient;
    private String nom;
    private String prenom;
    private String cin;
    @OneToMany(mappedBy = "adherent", fetch = FetchType.EAGER)
    private Set<Emprunt> emprunts = new HashSet<>(0);

    public Adherent() {
    }

    public Adherent(Long idClient, String nom, String prenom, String cin, Set<Emprunt> emprunts) {
        this.idClient = idClient;
        this.nom = nom;
        this.prenom = prenom;
        this.cin = cin;
        this.emprunts = emprunts;
    }
    public Adherent(Long idClient) {
        this.idClient = idClient;
    }

    public Adherent(String nom, String prenom, String cin) {
        this.nom = nom;
        this.prenom = prenom;
        this.cin = cin;
    }
    public Long getIdClient() {
        return idClient;
    }

    public void setIdClient(Long idClient) {
        this.idClient = idClient;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public Set<Emprunt> getEmprunts() {
        return emprunts;
    }

    public void setEmprunts(Set<Emprunt> emprunts) {
        this.emprunts = emprunts;
    }
}
