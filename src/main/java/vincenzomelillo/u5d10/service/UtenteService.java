package vincenzomelillo.u5d10.service;

import vincenzomelillo.u5d10.exceptions.BadRequestException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import vincenzomelillo.u5d10.exceptions.NotFoundException;
import vincenzomelillo.u5d10.payloads.NewUtenteDTO;
import vincenzomelillo.u5d10.repositories.DispositivoDAO;
import vincenzomelillo.u5d10.repositories.UtenteDAO;
import vincenzomelillo.u5d10.entities.Utente;

import java.util.UUID;

@Service
public class UtenteService {

	@Autowired
	  public  UtenteDAO utenteRepo;

	@Autowired
	public  DispositivoDAO dispositivoRepo;


	public Page<Utente> getUtente(int page, int size, String orderBy) {
		if (size >= 100) size = 100;
		Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy));
		return utenteRepo.findAll(pageable);
	}


	public Utente salvaUtente(NewUtenteDTO body) {
		utenteRepo.findByEmail(body.email()).ifPresent(user -> {
			throw new BadRequestException("L'email " + user.getEmail() + " è già in uso!");
		});

		Utente newUtente = new Utente();
		newUtente.setCognome(body.cognome());
		newUtente.setNome(body.nome());
		newUtente.setEmail(body.email());
		newUtente.setUsername(body.username());
		return utenteRepo.save(newUtente);
	}


	public  void findByIdAndDelete(UUID id) {
		Utente found = this.findById(id);
		utenteRepo.delete(found);
	}







	public Utente findById(UUID id) {
		return utenteRepo.findById(id).orElseThrow(() -> new NotFoundException(id));
	}

	public Utente findByIdAndUpdate(UUID id, Utente body) {
		Utente found = this.findById(id);
		found.setCognome(body.getCognome());
		found.setNome(body.getNome());
		found.setEmail(body.getEmail());
		found.setUsername(body.getUsername());
		return utenteRepo.save(found);
	}


}
