package ma.fstm.ilisi2.projects.biblio.gestionbibliospringboot.Controller;


import ma.fstm.ilisi2.projects.biblio.gestionbibliospringboot.Model.bo.Adherent;
import ma.fstm.ilisi2.projects.biblio.gestionbibliospringboot.Model.service.ServiceAdherent;
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
@RequestMapping("/Bibliotheque/CrudAdherent")
public class AdherentController {
    @Autowired
    ServiceAdherent serviceAdherent;
    @GetMapping("")
    public String showAdherentPage(Model model) {
        List<Adherent> adherents = (List<Adherent>) serviceAdherent.getAllAdherents();
        model.addAttribute("adherents", adherents);
        return "Adherent/pagePrincipale";
    }

    @GetMapping("/add")
    public String showAddAdherentPage(Model model) {
        Adherent adherent = new Adherent();
        model.addAttribute("adherent", adherent);
        return "Adherent/addAdherent";
    }
//showEditAdherentPage
    @GetMapping("/edit/{id}")
    public ModelAndView showEditAdherentPage(@PathVariable Long id, Model model) {
        ModelAndView mav = new ModelAndView("Adherent/modifyAdherent");
        Adherent adherent = serviceAdherent.getAdherent(id);
        mav.addObject("adherent", adherent);
        return mav;
    }
//methode add adherent
    @PostMapping("/save")
    public String addAdherent(Adherent adherent) {
        serviceAdherent.addAdherent(adherent);
        return "redirect:/Bibliotheque/CrudAdherent";
    }
//methode delete adherent
    @GetMapping("/delete/{id}")
    public String deleteAdherent(@PathVariable Long id) {
        serviceAdherent.deleteAdherent(id);
        return "redirect:/Bibliotheque/CrudAdherent";
    }
    
//search adherent
    @PostMapping("")
    public String searchAdherent(String cin, Model model) {
        List<Adherent> adherents = serviceAdherent.getAdherentsByCin(cin);
        model.addAttribute("adherents", adherents);
        return "Adherent/pagePrincipale";
    }
}
