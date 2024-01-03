package ma.fstm.ilisi2.projects.biblio.gestionbibliospringboot.Controller;

import ma.fstm.ilisi2.projects.biblio.gestionbibliospringboot.Model.bo.Emprunt;
import ma.fstm.ilisi2.projects.biblio.gestionbibliospringboot.Model.service.ServiceEmprunt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/Bibliotheque/CrudEmprunt")

public class EmpruntController {
    @Autowired
    ServiceEmprunt serviceEmprunt;

    @GetMapping("")
    public String showEmpruntPage(Model model) {
        List<Emprunt> emprunts = (List<Emprunt>) serviceEmprunt.getAllEmprunts();
        model.addAttribute("emprunts", emprunts);
        return "Emprunt/pagePrincipale";
    }

    @GetMapping("/add")
    public String showAddEmpruntPage() {
        return "Emprunt/addEmprunt";
    }
//addEmprunt
    @PostMapping("/save")
    public String addEmprunt(@RequestParam("cin") String cin, @RequestParam("isbn") String isbn) {
        serviceEmprunt.addEmprunt(cin, isbn);
        return "redirect:/Bibliotheque/CrudEmprunt";
    }
//searchEmprunt
    @PostMapping("")
    public String searchEmprunt(@RequestParam("cin") String cin, Model model) {
        List<Emprunt> emprunts = serviceEmprunt.getEmpruntsByCin(cin);
        model.addAttribute("emprunts", emprunts);
        return "Emprunt/pagePrincipale";
    }
//methode rendreEmprunt
    @GetMapping("/rendre")
    public String rendreEmprunt(@RequestParam("idEmp") Long idEmp, @RequestParam("isbn") String isbn) {
        serviceEmprunt.rendreEmprunt(idEmp, isbn);
        return "redirect:/Bibliotheque/CrudEmprunt";
    }
}
