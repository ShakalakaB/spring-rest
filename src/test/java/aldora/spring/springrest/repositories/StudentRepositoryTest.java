package aldora.spring.springrest.repositories;

import aldora.spring.springrest.SpringRestApplication;
import aldora.spring.springrest.domain.Student;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SpringRestApplication.class})
class StudentRepositoryTest {
    @Autowired
    StudentRepository studentRepository;

    @Test
    void save() {
        Student student = new Student(1L, "student name", Student.Gender.FEMALE, 3);
        studentRepository.save(student);
        Student retrivedStudent = studentRepository.findById(1L).get();
        assertEquals(1L, retrivedStudent.getId());
    }
}