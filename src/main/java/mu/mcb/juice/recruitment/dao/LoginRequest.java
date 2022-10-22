package mu.mcb.juice.recruitment.dao;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Brume
 **/
@Getter
@Setter
public class LoginRequest {
    private String userName;
    private String password;
}
