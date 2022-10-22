package mu.mcb.juice.recruitment.service;

import mu.mcb.juice.recruitment.dao.CourseDao;

import java.util.List;

/**
 * @author Brume
 **/
public interface CourseService {
    /**
     * @param studentId
     * @return  List<CourseDto>
     */
    List<CourseDao> getCoursesByStudentId(Integer studentId);

    Integer countCourseDurationByStudentId(Integer studentId);

    CourseDao create(CourseDao courseDao);
    CourseDao update(CourseDao courseDao);
    CourseDao findById(Integer id);
    List<CourseDao> findAll();
    void delete(Integer id);

}
