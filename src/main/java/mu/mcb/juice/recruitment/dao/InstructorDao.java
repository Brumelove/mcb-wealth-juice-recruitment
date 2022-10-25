package mu.mcb.juice.recruitment.dao;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Brume
 **/
@Getter
@Setter
public class InstructorDao {
    @ApiModelProperty(hidden = true)
    private Integer id;
    private String departmentName;
    private String headedBy;
    private String firstName;
    private String lastName;
    private String phone;
}
