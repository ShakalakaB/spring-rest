package aldora.spring.springrest.feign;

import aldora.spring.springrest.api.v1.model.CustomerDTO;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.List;

@Slf4j
public class FallbackPolicy implements AccountFeignService {
    private Exception exception;

    public FallbackPolicy(Exception exception) {
        this.exception = exception;
    }

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
        log.error("exception happend");
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName("kk");
        return Collections.singletonList(customerDTO);
    }
}

