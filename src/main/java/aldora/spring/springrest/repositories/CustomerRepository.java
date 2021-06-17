package aldora.spring.springrest.repositories;

import aldora.spring.springrest.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByFirstName(String firstName);
}
