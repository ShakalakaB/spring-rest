package aldora.spring.springrest.api.v1.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginUserDTO {
    @ApiModelProperty
    private String firstName;

    @ApiModelProperty
    private String password;
}
