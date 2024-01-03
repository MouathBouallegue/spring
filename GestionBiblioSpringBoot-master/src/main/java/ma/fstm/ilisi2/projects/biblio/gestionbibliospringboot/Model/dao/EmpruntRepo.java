package ma.fstm.ilisi2.projects.biblio.gestionbibliospringboot.Model.dao;

import ma.fstm.ilisi2.projects.biblio.gestionbibliospringboot.Model.bo.Emprunt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmpruntRepo extends JpaRepository<Emprunt, Long> {

    List<Emprunt> getEmpruntsByAdherent_Cin(String cin);
}
