package aldora.spring.springrest.services;

import aldora.spring.springrest.api.v1.mapper.CustomerMapper;
import aldora.spring.springrest.api.v1.model.CustomerDTO;
import aldora.spring.springrest.domain.Customer;
import aldora.spring.springrest.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return customerRepository.findAll().stream()
                .map(customer -> {
                    CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);
                    customerDTO.setCustomerUrl("/api/v1/customers/" + customer.getId());
                    return customerDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDTO getCustomerById(Long id) {
        return customerRepository.findById(id)
                .map(customer -> {
                    CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);
                    customerDTO.setCustomerUrl("/api/v1/customers/" + customer.getId());
                    return customerDTO;
                })
                .orElseThrow(RuntimeException::new);
    }

    @Override
    public CustomerDTO store(CustomerDTO customerDTO) {
        Customer customer = customerMapper.customerDTOToCustomer(customerDTO);
        return saveCustomerDTO(customer);
    }

    @Override
    public CustomerDTO update(Long id, CustomerDTO customerDTO) {
        Optional<Customer> customerOptional = customerRepository.findById(id);

        if (customerOptional.isEmpty()) {
            throw new RuntimeException("cutomer not found");
        }

        Customer customer = customerMapper.customerDTOToCustomer(customerDTO);
        customer.setId(id);
        return saveCustomerDTO(customer);
    }

    private CustomerDTO saveCustomerDTO(Customer customer) {
        Customer savedCustomer = customerRepository.save(customer);
        CustomerDTO savedCustomerDTO = customerMapper.customerToCustomerDTO(savedCustomer);
        savedCustomerDTO.setCustomerUrl("/api/v1/customers/" + savedCustomer.getId());
        return savedCustomerDTO;
    }
}