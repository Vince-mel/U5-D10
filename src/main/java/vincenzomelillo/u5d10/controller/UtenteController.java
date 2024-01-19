package vincenzomelillo.u5d10.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import vincenzomelillo.u5d10.exceptions.BadRequestException;
import vincenzomelillo.u5d10.payloads.NewUtenteDTO;
import vincenzomelillo.u5d10.payloads.NewUtenteResponseDTO;
import vincenzomelillo.u5d10.service.UtenteService;
import vincenzomelillo.u5d10.entities.Utente;

import java.util.UUID;

@RestController
@RequestMapping("/utenti")
public class UtenteController {

    @Autowired
    UtenteService utenteService;

    @GetMapping
    public Page<Utente> getUtenti(@RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "10") int size,
                                  @RequestParam(defaultValue = "id") String orderBy) {
        return  utenteService.getUtente(page, size, orderBy);
    }
    @GetMapping("/{id}")
    public Utente getUtenteById(@PathVariable UUID id) {
        return utenteService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public NewUtenteResponseDTO createUtente(@RequestBody @Validated NewUtenteDTO newUtentePayload, BindingResult validation) {
        System.out.println(validation);
        if (validation.hasErrors()) {
            System.out.println(validation.getAllErrors());
            throw new BadRequestException("Ci sono errori nel payload!");
        } else {
            Utente newUtente = utenteService.salvaUtente(newUtentePayload);

            return new NewUtenteResponseDTO(newUtente.getId());
        }
    }







    @DeleteMapping("/{Id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void getUserByIdAndDelete(@PathVariable UUID userId) {
        utenteService.findByIdAndDelete(userId);
    }





}
