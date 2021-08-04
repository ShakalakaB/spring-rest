package aldora.spring.springrest.controllers.v1;

import aldora.spring.springrest.api.v1.model.CustomerDTO;
import aldora.spring.springrest.feign.AccountFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/api/v1/microservice")
public class MicroServiceController {
    private final RestTemplate restTemplate;

    private final AccountFeignService accountFeignService;

    public MicroServiceController(RestTemplate restTemplate, AccountFeignService accountFeignService) {
        this.restTemplate = restTemplate;
        this.accountFeignService = accountFeignService;
    }

    @GetMapping("/getList")
    public List<CustomerDTO> getCustomers() {
        String url = "http://ACCOUNT-WS/api/v1/customers/list";
        ResponseEntity<List<CustomerDTO>> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<CustomerDTO>>() {}
        );

        List<CustomerDTO> customerDTOList = responseEntity.getBody();

        return customerDTOList;
    }

    @GetMapping("/feign/getList")
    public List<CustomerDTO> getFeignCustomers() {
        List<CustomerDTO> customerDTOList = accountFeignService.getCustomerList();

        return customerDTOList;
    }
}
