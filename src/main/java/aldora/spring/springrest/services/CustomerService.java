package aldora.spring.springrest.services;

import aldora.spring.springrest.api.v1.model.CustomerDTO;
import aldora.spring.springrest.domain.Customer;

import java.util.List;

public interface CustomerService {
    List<CustomerDTO> getAllCustomers();

    CustomerDTO getCustomerById(Long id);

    CustomerDTO store(CustomerDTO customerDTO);

    CustomerDTO update(Long id, CustomerDTO customerDTO);

    void delete(Long id);
}
