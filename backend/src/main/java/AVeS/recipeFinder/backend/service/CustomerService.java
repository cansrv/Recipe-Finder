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

    public void deleteCustomer (Long id){
        customerRepository.deleteById(id);
    }

    public void updateCustomer (Customer customer){
        customerRepository.deleteById(customer.getId());
        customerRepository.save(customer);
    }

    public Customer findCustomerById(Long id){
        return customerRepository.findCustomerById(id);
    }

    public Customer findCustomerByUsername(String username){
        return customerRepository.findByUsername(username);
    }

    public Customer findCustomerByMail(String mail){
        return customerRepository.findByMail(mail);
    }

    public Customer checkCredentials (String email, String password){
        Customer customer = customerRepository.findByMail(email);
        if (customer.getPassword().equals(password))
            return customer;

        else
            return null;
    }

    public Boolean existsByUsername(String username){
        return customerRepository.existsByUsername(username);
    }
}

