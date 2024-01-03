package ma.fstm.ilisi2.projects.biblio.gestionbibliospringboot.Controller;


import ma.fstm.ilisi2.projects.biblio.gestionbibliospringboot.Model.bo.Livre;
import ma.fstm.ilisi2.projects.biblio.gestionbibliospringboot.Model.service.ServiceLivre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping({"/Bibliotheque","/Bibliotheque/CrudLivre"})
public class LivreController {

    @Autowired
    ServiceLivre serviceLivre;

    @GetMapping("")
    public String showMainPage(Model model) {
        List<Livre> livres = (List<Livre>) serviceLivre.getAllLivres();
        model.addAttribute("livres", livres);
        return "Livre/pagePrincipale";
    }

    @GetMapping("/add")
    public String showAddPage(Model model) {
        Livre livre = new Livre();
        model.addAttribute("livre", livre);
        return "Livre/addLivre";
    }
//showEditPage
    @GetMapping("/edit/{isbn}")
    public ModelAndView showEditPage(@PathVariable String isbn, Model model) {
        ModelAndView mav = new ModelAndView("Livre/modifyLivre");
        Livre livre = serviceLivre.getLivre(isbn);
        mav.addObject("livre", livre);
        return mav;
    }
//addLivre
    @PostMapping("/save")
    public String addLivre(Livre livre) {
        serviceLivre.addLivre(livre);
        return "redirect:/Bibliotheque";
    }
//deleteLivre    
    @GetMapping("/delete/{isbn}")
    public String deleteLivre(@PathVariable String isbn) {
        serviceLivre.deleteLivre(isbn);
        return "redirect:/Bibliotheque";
    }
//searchLivre
    @PostMapping("")
    public String searchLivre(String auteur, Model model) {
        List<Livre> livres = (List<Livre>) serviceLivre.getLivresByAuteur(auteur);
        model.addAttribute("livres", livres);
        return "Livre/pagePrincipale";
    }


}
