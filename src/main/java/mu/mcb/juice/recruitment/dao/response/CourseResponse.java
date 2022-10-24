package mu.mcb.juice.recruitment.dao.response;

import lombok.Getter;
import lombok.Setter;
import mu.mcb.juice.recruitment.dao.StudentDao;

/**
 * @author Brume
 **/
@Getter
@Setter
public class CourseResponse {
    private Integer id;
    private String name;
    private String departmentName;
    private Integer instructorId;
    private Integer duration;
    private StudentDao student;
}
