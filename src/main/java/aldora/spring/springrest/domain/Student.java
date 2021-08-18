package aldora.spring.springrest.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@RedisHash
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Student implements Serializable {
    public enum Gender {
        MALE, FEMALE;
    }

    private Long id;

    private String name;

    private Gender gender;

    private int grade;
}
