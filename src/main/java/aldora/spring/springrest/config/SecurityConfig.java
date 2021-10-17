package aldora.spring.springrest.config;

import aldora.spring.springrest.api.filters.AuthorizationFilter;
import aldora.spring.springrest.api.filters.UserPasswordAuthenticationFilter;
import aldora.spring.springrest.services.CustomerService;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
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
//        http.authorizeRequests()
//                .antMatchers("/api/**").permitAll()
//                .antMatchers("/actuator/**").permitAll()
//                .and()
//                .addFilter(getAuthenticationFilter());
        http.authorizeRequests()
                .antMatchers(HttpMethod.POST, "/users").hasIpAddress(environment.getProperty("gateway.ip"))
                .antMatchers("/h2-console/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilter(getAuthenticationFilter())
                .addFilter(new AuthorizationFilter(authenticationManager(), environment));
        http.headers().frameOptions().disable();
    }

    private UserPasswordAuthenticationFilter getAuthenticationFilter() throws Exception
    {
        UserPasswordAuthenticationFilter authenticationFilter = new UserPasswordAuthenticationFilter(customerService, environment, authenticationManager());
//        authenticationFilter.setAuthenticationManager(authenticationManager());
        authenticationFilter.setFilterProcessesUrl(environment.getProperty("login.url.path"));
        return authenticationFilter;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customerService).passwordEncoder(bCryptPasswordEncoder);
    }
}
