package aldora.spring.springrest.feign;

import aldora.spring.springrest.api.v1.model.CustomerDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(value = "ACCOUNT-WS", path = "/api/v1/customers", contextId = "AccountFeignService")
public interface AccountFeignService {
    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    List<CustomerDTO> getCustomerList();

    @GetMapping(value = "/listdd", produces = MediaType.APPLICATION_JSON_VALUE)
    List<CustomerDTO> getCustomerException();

    @GetMapping(value = "/listdd", produces = MediaType.APPLICATION_JSON_VALUE)
    List<CustomerDTO> exceptionWithCircuitBreaker();
}