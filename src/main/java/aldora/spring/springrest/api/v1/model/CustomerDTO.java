package aldora.spring.springrest.api.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class CustomerDTO {

    @ApiModelProperty(value = "This is the first name", required = true)
    @NotBlank(message = "can't be blank")
    private String firstName;

    @ApiModelProperty(required = true )
    private String lastName;

    @JsonProperty("customer_url")
    private String customerUrl;
}
