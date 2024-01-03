package ma.fstm.ilisi2.projects.biblio.gestionbibliospringboot.Model.service;


import ma.fstm.ilisi2.projects.biblio.gestionbibliospringboot.Model.bo.Adherent;
import ma.fstm.ilisi2.projects.biblio.gestionbibliospringboot.Model.bo.Emprunt;
import ma.fstm.ilisi2.projects.biblio.gestionbibliospringboot.Model.bo.Exemplaire;
import ma.fstm.ilisi2.projects.biblio.gestionbibliospringboot.Model.bo.Livre;
import ma.fstm.ilisi2.projects.biblio.gestionbibliospringboot.Model.dao.EmpruntRepo;
import ma.fstm.ilisi2.projects.biblio.gestionbibliospringboot.Model.dao.ExemplaireRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ServiceEmprunt {
    @Autowired
    EmpruntRepo empruntRepo;

    @Autowired
    ServiceAdherent serviceAdherent;

    @Autowired
    ServiceLivre serviceLivre;

    @Autowired
    ExemplaireRepo exemplaireRepo;
    public List<Emprunt> getAllEmprunts() {
        return empruntRepo.findAll();
    }
// méthode ajoute un nouvel emprunt à la base de données en utilisant le numéro de CIN de l'adhérent et le numéro ISBN du livre
    public void addEmprunt(String cin , String isbn) {
        if(cin.isEmpty() || isbn.isEmpty())
            return;


        Adherent adherent = serviceAdherent.getAdherentsByCin(cin).get(0);
        Exemplaire exemplaire = exemplaireRepo.getExemplaireDispoByIsbn(isbn).get(0);

        if(adherent == null || exemplaire == null)
            return;

        Livre L = serviceLivre.getLivre(isbn);
        if((L == null) || (L.getNbrexemp() == 0) )
            return;

        empruntRepo.save(new Emprunt(adherent,exemplaire));
        L.setNbrexemp(L.getNbrexemp() -1);
        serviceLivre.updateLivre(L);
    }
//récupère les emprunts d'un adhérent en utilisant son numéro de CIN. Si le numéro de CIN est vide, elle renvoie tous les emprunts
    public List<Emprunt> getEmpruntsByCin(String cin) {
        if(cin.isEmpty())
            return empruntRepo.findAll();
        return empruntRepo.getEmpruntsByAdherent_Cin(cin);
    }


    public void rendreEmprunt(Long idEmp, String isbn) {
        if(idEmp == null || isbn.isEmpty())
            return;

        Emprunt emprunt = empruntRepo.findById(idEmp).get();
        if(emprunt == null)
            return;

        if(emprunt.getStatus() == -1 || emprunt.getStatus() == 1)
            return;

        LocalDate currentDate = new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();;
        if(emprunt.getDateRetour().compareTo(currentDate) < 0)
            emprunt.setStatus(-1);
        else
            emprunt.setStatus(1);

        Livre L = serviceLivre.getLivre(isbn);
        if(L == null)
            return;

        empruntRepo.save(emprunt);
        L.setNbrexemp(L.getNbrexemp() +1);
        serviceLivre.updateLivre(L);
    }
}
/* Cette méthode gère le retour d'un emprunt en fonction de l'ID de l'emprunt et du numéro ISBN du livre.
Elle effectue plusieurs vérifications avant de marquer l'emprunt comme rendu :
Vérifie si l'ID de l'emprunt et le numéro ISBN ne sont pas nuls ou vides.
Récupère l'emprunt correspondant à l'ID.
Vérifie si l'emprunt existe et s'il n'a pas déjà été rendu ou marqué comme en retard.
Compare la date de retour de l'emprunt avec la date actuelle pour déterminer son statut.
Sauvegarde l'emprunt mis à jour et met à jour le nombre d'exemplaires disponibles dans le livre associé.*/
