package aldora.spring.springrest.controllers.v1;

//import aldora.spring.springrest.api.v1.model.CustomerDTO;
//import aldora.spring.springrest.api.v1.model.CustomerListDTO;
import aldora.spring.springrest.model.CustomerDTO;
import aldora.spring.springrest.model.CustomerListDTO;
import aldora.spring.springrest.services.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = {"Customer"})
@SwaggerDefinition(tags = {
        @Tag(name = "Customer Resource", description = "Operations for customer")
})
@Controller
@RequestMapping(CustomerController.API_V_1_CUSTOMERS)
public class CustomerController {
    public static final String API_V_1_CUSTOMERS = "/api/v1/customers";
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
        CustomerListDTO customerListDTO = new CustomerListDTO();
        customerListDTO.getCustomers().addAll(customerService.getAllCustomers());

        return new ResponseEntity<>(customerListDTO, HttpStatus.OK);
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

    @ApiOperation(value = "Create a customer", notes = "notes of the api")
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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        customerService.delete(id);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
