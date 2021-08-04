package aldora.spring.springrest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RefreshScope
@EnableFeignClients
public class SpringRestApplication {
    @Value("${token.secret}")
    private String secret;

    public static void main(String[] args) {
        SpringApplication.run(SpringRestApplication.class, args);
    }

    @RequestMapping(value = "/config", method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
    public String config() {
        return String.format("Token secret is '%s'.\n", secret);
    }
}
