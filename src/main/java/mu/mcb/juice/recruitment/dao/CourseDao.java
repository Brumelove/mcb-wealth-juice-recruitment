package mu.mcb.juice.recruitment.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Brume
 **/
@Getter
@Setter
public class CourseDao {
    private Integer id;
    private String name;
    private String departmentName;
    private Integer instructorId;
    private Integer duration;
    @JsonIgnore
    private StudentDao student;
}
