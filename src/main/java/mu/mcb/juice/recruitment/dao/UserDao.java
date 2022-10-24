package mu.mcb.juice.recruitment.dao;

import lombok.Getter;
import lombok.Setter;
import mu.mcb.juice.recruitment.enumeration.UserRole;

/**
 * @author Brume
 **/
@Getter
@Setter
public class UserDao {
   // @ApiModelProperty(hidden = true)
    private Long id;
    private  String userName;
    private  String email;
    private  String password;
   // @ApiModelProperty(hidden = true)
    private UserRole userRole;
}
