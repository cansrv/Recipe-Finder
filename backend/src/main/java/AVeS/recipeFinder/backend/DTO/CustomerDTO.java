package AVeS.recipeFinder.backend.DTO;


import jakarta.persistence.Entity;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerDTO {

    private String username;
    private String password;
    private String mail;
}
