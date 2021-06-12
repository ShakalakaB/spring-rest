package aldora.spring.springrest.repositories;

import aldora.spring.springrest.domain.Payment;
import org.springframework.data.repository.CrudRepository;

public interface PaymentRepository extends CrudRepository<Payment, Long> {
}
