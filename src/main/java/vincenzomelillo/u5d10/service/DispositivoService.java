package vincenzomelillo.u5d10.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import vincenzomelillo.u5d10.entities.Dispositivo;
import vincenzomelillo.u5d10.entities.StatoDispositivo;
import vincenzomelillo.u5d10.entities.TipoDispositivo;
import vincenzomelillo.u5d10.repositories.DispositivoDAO;

@Service
public class DispositivoService {

    @Autowired
    private static DispositivoDAO dispositivoRepo;

    public Dispositivo salveDispositivo(Dispositivo d) {
      dispositivoRepo.save(d);
        return d;
    }

    public static void rimuoviDispositivo(Long id) {
        if (!dispositivoRepo.existsById(id)) {
            throw new EntityNotFoundException("Impossibile eliminare, dispositivo non trovato!");
        }
        dispositivoRepo.deleteById(id);
        System.out.println("DISPOSITIVO eliminato!");
    }

    public Dispositivo aggiornaDispositivo(Dispositivo d) {
        if (!dispositivoRepo.existsById(d.getId())) {
            throw new EntityNotFoundException("Impossibile aggiornare, dispositivo non trovato!");
        }
       dispositivoRepo.save(d);
        return d;
    }

    public Dispositivo findById(Long id) {
        if (!dispositivoRepo.existsById(id)) {
            throw new EntityNotFoundException("DISPOSITIVO INESISTENTE!");
        }
        return dispositivoRepo.findById(id).get();
    }

    public Page<Dispositivo> findAll(Pageable pageable) {
        return (Page<Dispositivo>) dispositivoRepo.findAll(pageable);
    }

    public Page<Dispositivo> findByStatoDispositivo(StatoDispositivo statoDispositivo, Pageable pageable) {
        return (Page<Dispositivo>) dispositivoRepo.findByStatoDispositivo(statoDispositivo, pageable);
    }

    public Page<Dispositivo> findByTipoDispositivo(TipoDispositivo tipoDispositivo, Pageable pageable) {
        return (Page<Dispositivo>) dispositivoRepo.findByTipoDispositivo(tipoDispositivo, pageable);
    }

}
