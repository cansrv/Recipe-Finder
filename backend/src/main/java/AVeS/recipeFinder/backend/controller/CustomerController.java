package AVeS.recipeFinder.backend.controller;


import AVeS.recipeFinder.backend.entity.dto.CustomerDTO;
import AVeS.recipeFinder.backend.entity.model.Customer;
import AVeS.recipeFinder.backend.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/signin")
    public Customer signIn (@RequestBody CustomerDTO customerDTO )
    {
        return customerService.checkCredentials(customerDTO.getMail(), customerDTO.getPassword());
    }

    @PostMapping("/register")
    public ResponseEntity<?> signUp (@RequestBody CustomerDTO customerDTO )
    {
        Customer customer = Customer.builder()
                .username(customerDTO.getUsername())
                .password(customerDTO.getPassword())
                .mail(customerDTO.getMail())
                .build();

        customerService.addCustomer(customer);

        return  ResponseEntity.ok("User registered successfully!");
    }
}
