package aldora.spring.springrest.repositories;

import aldora.spring.springrest.domain.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student, Long> {
}
