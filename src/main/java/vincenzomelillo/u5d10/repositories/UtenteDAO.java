package vincenzomelillo.u5d10.repositories;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import vincenzomelillo.u5d10.entities.Utente;


public interface UtenteDAO
        extends JpaRepository<Utente, UUID>, PagingAndSortingRepository<Utente, UUID> {

    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
    Optional<Utente> findByEmail(String email);
    Optional<Utente> findByUsername(String username);

}
