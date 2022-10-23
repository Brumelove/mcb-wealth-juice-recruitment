package mu.mcb.juice.recruitment.dao;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Brume
 **/
@Getter
@Setter
public class CourseDao {
    @ApiModelProperty(hidden = true)
    private Integer id;
    private  String name;
    private  String departmentName;
    private  Integer instructorId;
    private  Integer duration;
    private StudentDao student;
}
