package aldora.spring.springrest.api.v1.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDTO {
    private Long id;

    private String firstName;
    private String lastName;
}
