package vincenzomelillo.u5d10.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record NewUtenteDTO(
        @NotEmpty(message = "Il nome è un campo obbligatorio!")
        @Size(min = 3, max = 30, message = "Il nome deve essere compreso tra 3 e 30 caratteri!")
        String nome,
        @NotEmpty(message = "Il cognome è un campo obbligatorio!")
        @Size(min = 3, max = 30, message = "Il cognome deve essere compreso tra 3 e 30 caratteri!")
        String cognome,
        @Email(message = "L'indirizzo inserito non è un indirizzo valido")
        @NotEmpty(message = "La mail è un campo obbligatorio!")
        String email,
        @NotEmpty(message = "Username è un campo obbligatorio!")
        @Size(min = 5, message = "L'Username deve avere minimo 5 caratteri!")
        String username) {
}
