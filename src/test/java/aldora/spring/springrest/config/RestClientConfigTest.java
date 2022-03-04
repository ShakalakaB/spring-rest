package aldora.spring.springrest.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RestClientConfigTest {
    @Autowired
    RestTemplate restTemplate;

    @Test
    void restTemplate() {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("https://www.baidu.com", String.class);
        assertEquals("bar", responseEntity.getHeaders().get("x-foo").get(0));
    }
}