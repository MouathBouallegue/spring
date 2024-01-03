package ma.fstm.ilisi2.projects.biblio.gestionbibliospringboot.Model.dao;

import ma.fstm.ilisi2.projects.biblio.gestionbibliospringboot.Model.bo.Adherent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface AdherentRepo extends JpaRepository<Adherent, Long> {

    @Query("SELECT a FROM Adherent a WHERE a.cin = :cin")
    List<Adherent> findByCin(@Param("cin") String cin);
}
