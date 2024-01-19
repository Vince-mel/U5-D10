package vincenzomelillo.u5d10.repositories;


import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import vincenzomelillo.u5d10.entities.Dispositivo;
import vincenzomelillo.u5d10.entities.StatoDispositivo;
import vincenzomelillo.u5d10.entities.TipoDispositivo;
import vincenzomelillo.u5d10.entities.Utente;

public interface DispositivoDAO
        extends JpaRepository<Dispositivo, Long>, PagingAndSortingRepository<Dispositivo, Long> {

    Page<Dispositivo> findByStatoDispositivo(StatoDispositivo statoDispositivo, Pageable pageable);
    Page<Dispositivo> findByTipo(TipoDispositivo tipoDispositivo, Pageable pageable);
    List<Dispositivo> findByUtente(Utente utente);

}
