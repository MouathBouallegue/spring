package ma.fstm.ilisi2.projects.biblio.gestionbibliospringboot.Model.service;
import ma.fstm.ilisi2.projects.biblio.gestionbibliospringboot.Model.bo.Exemplaire;
import ma.fstm.ilisi2.projects.biblio.gestionbibliospringboot.Model.bo.Livre;
import ma.fstm.ilisi2.projects.biblio.gestionbibliospringboot.Model.dao.ExemplaireRepo;
import ma.fstm.ilisi2.projects.biblio.gestionbibliospringboot.Model.dao.LivreRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@Transactional
public class ServiceLivre {

    @Autowired
    LivreRepo livreRepo;
    @Autowired
    ExemplaireRepo exemplaireRepo;
    public void addLivre(Livre livre) {
        livreRepo.save(livre);
        for (int i=0 ; i<livre.getNbrexemp() ; i++) {
            exemplaireRepo.save(new Exemplaire(livre));
        }
    }
 /* Cette méthode supprime un livre de la base de données en utilisant son numéro ISBN.
Elle récupère le livre et les exemplaires disponibles associés au livre.
Elle vérifie si tous les exemplaires sont disponibles (non empruntés) et si aucun exemplaire n'a été emprunté.
Si les conditions sont remplies, elle supprime tous les exemplaires associés au livre avec deleteAll de ExemplaireRepo et ensuite elle supprime le livre avec deleteById de LivreRepo.*/   

    public void deleteLivre(String isbn) {
        Livre livre = livreRepo.findById(isbn).get();
        List<Exemplaire> exemplairesDispo = exemplaireRepo.getExemplaireDispoByIsbn(isbn);
        if(exemplairesDispo == null)
            return ;

        Integer nbrexempDispo = exemplairesDispo.size();
        System.out.println("nbrexempDispo = " + nbrexempDispo);
        System.out.println("nb emprunts =  " + exemplaireRepo.NbrExemplaireEmpruntee(isbn));
        if(livre.getNbrexemp() == nbrexempDispo  && exemplaireRepo.NbrExemplaireEmpruntee(isbn) == 0){
            exemplaireRepo.deleteAll(livre.getExemplaires());
            livreRepo.deleteById(isbn);
        }
    }

    public Livre getLivre(String isbn) {
        return livreRepo.findById(isbn).get();
    }

    public Iterable<Livre> getAllLivres() {
        return livreRepo.findAll();
    }
//récupère les livres de la base de données en fonction de l'auteur. Si l'auteur est nul ou vide, elle renvoie tous les livres
    public Iterable<Livre> getLivresByAuteur(String auteur) {
        if (auteur == null || auteur.isEmpty())
            return livreRepo.findAll();
        return livreRepo.findByAuteur(auteur);
    }
//méthode met à jour les informations d'un livre dans la base de données en utilisant la méthode save de LivreRepo
    public void updateLivre(Livre livre) {
        livreRepo.save(livre);
    }
}
