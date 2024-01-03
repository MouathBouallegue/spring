package ma.fstm.ilisi2.projects.biblio.gestionbibliospringboot.Model.bo;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Exemplaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_exemp")
    private Long idExemp;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "isbn", nullable = false)
    private Livre livre;
    @OneToMany(mappedBy = "exemplaire", fetch = FetchType.EAGER)
    private Set<Emprunt> emprunts = new HashSet<>(0);

    public Exemplaire() {
    }

    public Exemplaire(Livre livre) {
        this.livre = livre;
    }

    public Exemplaire(Livre livre, Set<Emprunt> emprunts) {
        this.livre = livre;
        this.emprunts = emprunts;
    }

    public Long getIdExemp() {
        return idExemp;
    }

    public void setIdExemp(Long idExemp) {
        this.idExemp = idExemp;
    }

    public Livre getLivre() {
        return livre;
    }

    public void setLivre(Livre livre) {
        this.livre = livre;
    }

    public Set<Emprunt> getEmprunts() {
        return emprunts;
    }

    public void setEmprunts(Set<Emprunt> emprunts) {
        this.emprunts = emprunts;
    }
}
