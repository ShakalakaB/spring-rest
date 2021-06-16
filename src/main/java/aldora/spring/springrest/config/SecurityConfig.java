package aldora.spring.springrest.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private Environment environment;

    public SecurityConfig(Environment environment) {
        this.environment = environment;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
//        http.authorizeRequests().antMatchers("/api/**").permitAll();
//        http.authorizeRequests().antMatchers("/api/**").permitAll();
        http.authorizeRequests().antMatchers("/**").hasIpAddress(environment.getProperty("gateway.ip"));
        http.headers().frameOptions().disable();
    }
}
