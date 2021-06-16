package aldora.spring.springrest.controllers.v1;

import aldora.spring.springrest.api.v1.model.CustomerDTO;
import aldora.spring.springrest.api.v1.model.CustomerListDTO;
import aldora.spring.springrest.services.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = {"Customer"})
@SwaggerDefinition(tags = {
        @Tag(name = "Customer Resource", description = "Operations for customer")
})
@RestController
@RequestMapping(CustomerController.API_V_1_CUSTOMERS)
public class CustomerController {
    public static final String API_V_1_CUSTOMERS = "/api/v1/customers";
    private final CustomerService customerService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public CustomerController(CustomerService customerService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.customerService = customerService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping
    public CustomerListDTO getAllCustomers() {
        return new CustomerListDTO(customerService.getAllCustomers());
    }

    @GetMapping("/list")
    public List<CustomerDTO> getAllCategoriesList() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/{id}")
    public CustomerDTO getCategoryByName(@PathVariable Long id) {
        return customerService.getCustomerById(id);
    }

    @ApiOperation(value = "Create a customer", notes = "notes of the api")
    @PostMapping()
    public CustomerDTO createNewCustomer(CustomerDTO customerDTO) {
        customerDTO.setPassword(bCryptPasswordEncoder.encode(customerDTO.getPassword()));
        CustomerDTO savedCustomerDTO = customerService.store(customerDTO);

        return savedCustomerDTO;
    }

    @PutMapping("/{id}")
    public CustomerDTO updateCustomer(CustomerDTO customerDTO, @PathVariable Long id) {
        CustomerDTO savedCustomerDTO = customerService.update(id, customerDTO);

        return savedCustomerDTO;
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable Long id) {
        customerService.delete(id);
    }
}
