package aldora.spring.springrest.config;

import aldora.spring.springrest.api.filters.AuthenticationFilter;
import aldora.spring.springrest.services.CustomerService;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private Environment environment;
    private final CustomerService customerService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public SecurityConfig(Environment environment, CustomerService customerService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.environment = environment;
        this.customerService = customerService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests().antMatchers("/api/**").permitAll()
                .and()
                .addFilter(getAuthenticationFilter());
//        http.authorizeRequests().antMatchers("/**").hasIpAddress(environment.getProperty("gateway.ip"));
        http.headers().frameOptions().disable();
    }

    private AuthenticationFilter getAuthenticationFilter() throws Exception
    {
        AuthenticationFilter authenticationFilter = new AuthenticationFilter(customerService, environment, authenticationManager());
//        authenticationFilter.setAuthenticationManager(authenticationManager());
        authenticationFilter.setFilterProcessesUrl(environment.getProperty("login.url.path"));
        return authenticationFilter;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customerService).passwordEncoder(bCryptPasswordEncoder);
    }
}
