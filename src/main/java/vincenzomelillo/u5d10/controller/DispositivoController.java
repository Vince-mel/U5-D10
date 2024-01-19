package vincenzomelillo.u5d10.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import vincenzomelillo.u5d10.entities.Dispositivo;
import vincenzomelillo.u5d10.entities.StatoDispositivo;
import vincenzomelillo.u5d10.service.DispositivoService;
import vincenzomelillo.u5d10.service.UtenteService;
import org.springframework.data.domain.Pageable;

import java.util.List;

@RestController
@RequestMapping("/dispositivi")
public class DispositivoController {

    @Autowired
    DispositivoService dispositivoService;

    @Autowired
    UtenteService utenteService;

    @GetMapping
    public List<Dispositivo> getAllDevices(Pageable pageable) {
        return dispositivoService.findAll(pageable).getContent();
    }

    @GetMapping(path = "/{id}")
    public Dispositivo getById(@PathVariable(name = "id") Long id) {
        return dispositivoService.findById(id);
    }

    @GetMapping(path = "/stato_dispositivo/{stato}")
    public List<Dispositivo> getStatusDevices(@PathVariable(name = "stato") String stato, Pageable pageable) {
        return dispositivoService.findByStatoDispositivo(StatoDispositivo.valueOf(stato.toUpperCase()), pageable).getContent();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Dispositivo createDevice(@RequestBody Dispositivo d) {
        return dispositivoService.salveDispositivo(d);
    }

    @PutMapping
    public Dispositivo updateDevice(@RequestBody Dispositivo d) {
        if (!d.getStatoDispositivo().equals(StatoDispositivo.ASSEGNATO)) {
            d.setUtente(null);
        }
        return dispositivoService.aggiornaDispositivo(d);
    }

}
