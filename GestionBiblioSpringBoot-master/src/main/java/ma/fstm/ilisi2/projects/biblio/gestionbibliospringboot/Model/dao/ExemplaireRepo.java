package ma.fstm.ilisi2.projects.biblio.gestionbibliospringboot.Model.dao;

import ma.fstm.ilisi2.projects.biblio.gestionbibliospringboot.Model.bo.Exemplaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
//sélectionner tous les exemplaires disponibles d'un livre spécifique
public interface ExemplaireRepo extends JpaRepository<Exemplaire, Long> {
    @Query("SELECT e FROM Exemplaire e WHERE e.livre.isbn = :isbnValue AND e.idExemp NOT IN (SELECT m.exemplaire.idExemp FROM Emprunt m WHERE  m.exemplaire.livre.isbn = :isbnValue and  m.status = 0)")
    ArrayList<Exemplaire> getExemplaireDispoByIsbn(@Param("isbnValue") String isbn);
//compter le nombre d'exemplaires d'un livre spécifique
    @Query("SELECT COUNT(m) FROM Emprunt m WHERE  m.exemplaire.livre.isbn = :isbnValue")
    int NbrExemplaireEmpruntee(@Param("isbnValue")String isbn);
}
