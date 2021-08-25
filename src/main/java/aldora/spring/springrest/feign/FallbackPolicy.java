package aldora.spring.springrest.feign;

import aldora.spring.springrest.api.v1.model.CustomerDTO;

import java.util.Collections;
import java.util.List;

public class FallbackPolicy implements AccountFeignService {

    @Override
    public List<CustomerDTO> getCustomerList() {
        return null;
    }

    @Override
    public List<CustomerDTO> getCustomerException() {
        return null;
    }

    @Override
    public List<CustomerDTO> exceptionWithCircuitBreaker() {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName("kk");
        return Collections.singletonList(customerDTO);
    }
}

