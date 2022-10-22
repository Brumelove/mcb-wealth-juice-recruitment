package mu.mcb.juice.recruitment.dao;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

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
    private Set<CourseDao> courses;
}
