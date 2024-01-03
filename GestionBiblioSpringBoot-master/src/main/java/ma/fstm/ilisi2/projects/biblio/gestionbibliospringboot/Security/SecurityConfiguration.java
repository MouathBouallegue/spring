package ma.fstm.ilisi2.projects.biblio.gestionbibliospringboot.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfiguration {

    @Bean
/* Cette méthode crée deux utilisateurs en mémoire (user et admin) avec des rôles différents (USER et ADMIN).
Les mots de passe sont encodés à l'aide de l'encodeur de mot de passe défini dans la méthode passwordEncoder().*/    
    public UserDetailsService userDetailsService() {
        UserDetails user =
                User.builder()
                        .username("user")
                        .password(passwordEncoder().encode("1234"))
                        .roles("USER")
                        .build();
        UserDetails admin =
                User.builder()
                        .username("admin")
                        .password(passwordEncoder().encode("4567"))
                        .roles("ADMIN")
                        .build();

        return new InMemoryUserDetailsManager(user,admin);
    }


    @Bean
// méthode crée et retourne un encodeur de mot de passe BCrypt.    
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
