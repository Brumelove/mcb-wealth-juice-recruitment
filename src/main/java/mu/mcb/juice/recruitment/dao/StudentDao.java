package mu.mcb.juice.recruitment.dao;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Brume
 **/
@Getter
@Setter
public class StudentDao {
    @ApiModelProperty(hidden = true)
    private Integer id;
    private String firstName;
    private String lastName;
    private String phone;
}
