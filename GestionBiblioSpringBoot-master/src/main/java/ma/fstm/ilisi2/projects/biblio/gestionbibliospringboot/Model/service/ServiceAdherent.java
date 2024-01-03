package ma.fstm.ilisi2.projects.biblio.gestionbibliospringboot.Model.service;

import ma.fstm.ilisi2.projects.biblio.gestionbibliospringboot.Model.bo.Adherent;
import ma.fstm.ilisi2.projects.biblio.gestionbibliospringboot.Model.dao.AdherentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ServiceAdherent {

    @Autowired
    AdherentRepo adherentRepo;
//méthode ajoute un adhérent à la base de données
    public void addAdherent(Adherent adherent) {
        if(adherent == null && adherent.getCin().isEmpty())
            return;
        if(adherentRepo.findByCin(adherent.getCin()).size() > 1)
            return;
        adherentRepo.save(adherent);
    }
//méthode supprime un adhérent de la base de données
    public void deleteAdherent(Long id) {
        Adherent adherent = adherentRepo.findById(id).get();
        if(adherent == null)
            return;
        if(adherent.getEmprunts().size() > 0)
            return;

        adherentRepo.deleteById(id);
    }
//récupère un adhérent de la base de données en fonction de son identifiant
    public Adherent getAdherent(Long id) {
        return adherentRepo.findById(id).get();
    }

    public Iterable<Adherent> getAllAdherents() {
        return adherentRepo.findAll();
    }


    public List<Adherent> getAdherentsByCin(String cin) {
        if (cin == null || cin.isEmpty())
            return adherentRepo.findAll();
        return adherentRepo.findByCin(cin);
    }
}
