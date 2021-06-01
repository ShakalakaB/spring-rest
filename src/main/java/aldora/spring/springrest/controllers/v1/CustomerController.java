package aldora.spring.springrest.controllers.v1;

import aldora.spring.springrest.api.v1.mapper.CustomerMapper;
import aldora.spring.springrest.api.v1.model.CustomerDTO;
import aldora.spring.springrest.api.v1.model.CustomerListDTO;
import aldora.spring.springrest.domain.Customer;
import aldora.spring.springrest.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/v1/customers")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping
    public ResponseEntity<CustomerListDTO> getAllCustomers() {
        return new ResponseEntity<CustomerListDTO>(
                new CustomerListDTO(customerService.getAllCustomers()), HttpStatus.OK
        );
    }

    @GetMapping("/list")
    public ResponseEntity<List<CustomerDTO>> getAllCategoriesList() {
        return new ResponseEntity<>(
                customerService.getAllCustomers(), HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCategoryByName(@PathVariable Long id) {
        return new ResponseEntity<CustomerDTO>(
                customerService.getCustomerById(id), HttpStatus.OK
        );
    }

    @PostMapping()
    public ResponseEntity<CustomerDTO> createNewCustomer(CustomerDTO customerDTO) {
        CustomerDTO savedCustomerDTO = customerService.store(customerDTO);

        return new ResponseEntity<>(savedCustomerDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO> updateCustomer(CustomerDTO customerDTO, @PathVariable Long id) {
        CustomerDTO savedCustomerDTO = customerService.update(id, customerDTO);

        return new ResponseEntity<>(savedCustomerDTO, HttpStatus.CREATED);
    }
}
