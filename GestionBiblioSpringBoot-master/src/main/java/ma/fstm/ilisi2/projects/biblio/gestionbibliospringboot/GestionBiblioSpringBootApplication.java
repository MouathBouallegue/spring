package ma.fstm.ilisi2.projects.biblio.gestionbibliospringboot;

import ma.fstm.ilisi2.projects.biblio.gestionbibliospringboot.Model.dao.ExemplaireRepo;
import ma.fstm.ilisi2.projects.biblio.gestionbibliospringboot.Model.service.ServiceEmprunt;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GestionBiblioSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(GestionBiblioSpringBootApplication.class, args);
    }


}
