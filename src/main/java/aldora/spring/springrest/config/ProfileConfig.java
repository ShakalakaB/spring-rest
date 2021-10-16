package aldora.spring.springrest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;

@Configuration
public class ProfileConfig {
    private final Environment environment;

    public ProfileConfig(Environment environment) {
        this.environment = environment;
    }

    @Bean
    @Profile("default")
    public String defaultBean() {
        String result = "default bean from " + environment.getProperty("myapplication.environment");
        System.out.println(result);
        return result;
    }

    @Bean
    @Profile("production")
    public String productionBean() {
        String result = "production bean from " + environment.getProperty("myapplication.environment");
        System.out.println(result);
        return result;
    }

    @Bean
    @Profile("!production")
    public String excludeProductionBean() {
        System.out.println("exclude production bean from " + environment.getProperty("myapplication.environment"));
        return "exclude production bean from " + environment.getProperty("myapplication.environment");
    }
}
