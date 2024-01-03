package ma.fstm.ilisi2.projects.biblio.gestionbibliospringboot.Model.bo;

import jakarta.persistence.*;


import java.util.HashSet;
import java.util.Set;

@Entity
public class Livre {

    @Id
    private String isbn;
    private String titre;
    private Integer nbrexemp;
    private String auteur;

    @OneToMany(mappedBy = "livre", fetch = FetchType.EAGER)
    private Set<Exemplaire> exemplaires = new HashSet<>(0);

    public Livre() {
    }
    public Livre(String isbn) {
        this.isbn = isbn;
    }
    public Livre(String isbn, String titre, String auteur,Integer nbrexemp) {
        this.isbn = isbn;
        this.titre = titre;
        this.nbrexemp = nbrexemp;
        this.auteur = auteur;
    }
    public Livre(String isbn, String titre, Integer nbrexemp, String auteur, Set<Exemplaire> exemplaires) {
        this.isbn = isbn;
        this.titre = titre;
        this.nbrexemp = nbrexemp;
        this.auteur = auteur;
        this.exemplaires = exemplaires;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Integer getNbrexemp() {
        return nbrexemp;
    }

    public void setNbrexemp(Integer nbrexemp) {
        this.nbrexemp = nbrexemp;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public Set<Exemplaire> getExemplaires() {
        return exemplaires;
    }

    public void setExemplaires(Set<Exemplaire> exemplaires) {
        this.exemplaires = exemplaires;
    }
}
