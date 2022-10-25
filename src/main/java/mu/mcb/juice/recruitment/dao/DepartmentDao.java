package mu.mcb.juice.recruitment.dao;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Brume
 **/

@Getter
@Setter
public class DepartmentDao {
    @ApiModelProperty(hidden = true)
    private Integer id;
    private String name;
    private String location;
}
