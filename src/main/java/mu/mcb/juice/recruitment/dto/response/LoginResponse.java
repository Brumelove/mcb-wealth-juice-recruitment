package mu.mcb.juice.recruitment.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author Brume
 **/
@Setter @Getter @Builder
public class LoginResponse {
    private String token;
    private UserDetails userDetails;
}
