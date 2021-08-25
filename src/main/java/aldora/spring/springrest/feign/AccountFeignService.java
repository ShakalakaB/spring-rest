package aldora.spring.springrest.feign;

import aldora.spring.springrest.api.v1.model.CustomerDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@FeignClient(value = "ACCOUNT-WS", path = "/api/v1/customers", contextId = "AccountFeignService",
fallback = FallBack.class)
public interface AccountFeignService {
    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    List<CustomerDTO> getCustomerList();

    @GetMapping(value = "/listdd", produces = MediaType.APPLICATION_JSON_VALUE)
    List<CustomerDTO> getCustomerException();

    @GetMapping(value = "/listdd", produces = MediaType.APPLICATION_JSON_VALUE)
    List<CustomerDTO> exceptionWithCircuitBreaker();
}

class FallBack implements AccountFeignService {

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
