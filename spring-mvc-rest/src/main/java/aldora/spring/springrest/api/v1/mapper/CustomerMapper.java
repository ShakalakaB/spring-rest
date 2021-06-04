package aldora.spring.springrest.api.v1.mapper;

//import aldora.spring.springrest.api.v1.model.CustomerDTO;
import aldora.spring.springrest.controllers.v1.CustomerController;
import aldora.spring.springrest.domain.Customer;
import aldora.spring.springrest.model.CustomerDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {
    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    @Mapping(source = "id", target = "customerUrl", qualifiedByName = "idTocCustomerUrl")
    CustomerDTO customerToCustomerDTO(Customer customer);

    Customer customerDTOToCustomer(CustomerDTO customerDTO);

    @Named("idTocCustomerUrl")
    public static String idTocCustomerUrl(Long id) {
        return CustomerController.API_V_1_CUSTOMERS + "/" + id;
    }
}
