package mu.mcb.juice.recruitment.dao;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Brume
 **/

@Data
public class DepartmentDao {
    @ApiModelProperty(hidden = true)
    private Integer id;
    private String name;
    private String location;
}
