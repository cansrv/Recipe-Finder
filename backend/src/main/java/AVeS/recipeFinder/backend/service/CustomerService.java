package AVeS.recipeFinder.backend.service;

import AVeS.recipeFinder.backend.entity.model.Customer;
import AVeS.recipeFinder.backend.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CustomerService {

    private CustomerRepository customerRepository;

    @Autowired
    public CustomerService (CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    public Customer addCustomer (Customer customer)
    {
        return customerRepository.save(customer);
    }

    public Customer checkCredentials (String username, String password){
        Customer customer = customerRepository.findByUsername(username);
        if (customer.getPassword().equals(password))
            return customer;

        else
            return null;
    }
}
