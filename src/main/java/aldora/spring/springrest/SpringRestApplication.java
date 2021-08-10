package aldora.spring.springrest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class SpringRestApplication {
    @Autowired
    private DispatcherServlet servlet;

    public static void main(String[] args) {
        SpringApplication.run(SpringRestApplication.class, args);
    }

    @Bean
    public CommandLineRunner getCommandLineRunner(ApplicationContext context) {
        servlet.setThrowExceptionIfNoHandlerFound(true);
        return args -> {};
    }

}
