package AVeS.recipeFinder.backend.entity.dto;


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
