package aldora.spring.springrest.services;

import aldora.spring.springrest.api.v1.model.CustomerDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface CustomerService extends UserDetailsService {
    List<CustomerDTO> getAllCustomers();

    CustomerDTO getCustomerById(Long id);

    CustomerDTO store(CustomerDTO customerDTO);

    CustomerDTO update(Long id, CustomerDTO customerDTO);

    void delete(Long id);
}
