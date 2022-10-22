package mu.mcb.juice.recruitment.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import mu.mcb.juice.recruitment.UserRole;

/**
 * @author Brume
 **/
@Getter
@Setter
public class UserDto {
    @ApiModelProperty(hidden = true)
    private Long id;
    private  String userName;
    private  String email;
    private  String password;
    @ApiModelProperty(hidden = true)
    private UserRole userRole;
}
