package aldora.spring.springrest.api.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDTO {

    @ApiModelProperty(value = "This is the first name", required = true)
    private String firstName;
    @ApiModelProperty(required = true )
    private String lastName;

    @JsonProperty("customer_url")
    private String customerUrl;

    private String password;
}
