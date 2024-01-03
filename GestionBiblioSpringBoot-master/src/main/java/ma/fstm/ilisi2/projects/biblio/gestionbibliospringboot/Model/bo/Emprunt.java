package ma.fstm.ilisi2.projects.biblio.gestionbibliospringboot.Model.bo;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;

@Entity
public class Emprunt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_emp")
    private Long idEmp;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_exemp", nullable = false)
    private Exemplaire exemplaire;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idClient", nullable = false)
    private Adherent adherent;
    @Temporal(TemporalType.DATE)
    private Date dateEmp;

    private Integer status;
//dateRetour
    @Transient
    private LocalDate dateRetour;
    public Emprunt() {
    }

    public Emprunt( Adherent adherent ,Exemplaire exemplaire) {
        this.exemplaire = exemplaire;
        this.adherent = adherent;
        this.dateEmp = new Date();
        this.status = 0;
    }


    public Long getIdEmp() {
        return this.idEmp;
    }

    public void setIdEmp(Long idEmp) {
        this.idEmp = idEmp;
    }
    public Exemplaire getExemplaire() {
        return this.exemplaire;
    }

    public void setExemplaire(Exemplaire exemplaire) {
        this.exemplaire = exemplaire;
    }
    public Adherent getAdherent() {
        return this.adherent;
    }

    public void setAdherent(Adherent adherent) {
        this.adherent = adherent;
    }
    public Date getDateEmp() {
        return this.dateEmp;
    }

    public void setDateEmp(Date dateEmp) {
        this.dateEmp = dateEmp;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public LocalDate getDateRetour() {
        return dateRetour;
    }
//méthode calcule et définit la date de retour en ajoutant 15 jours à la date d'emprunt
    @PostLoad
    public void setDateRetour() {
        this.dateRetour = LocalDate.parse(this.dateEmp.toString()).plusDays(15);
    }
}
