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

    Integer sumCourseDurationByStudentId(Integer studentId);

    CourseDao create(Integer studentId,CourseDao courseDao);
    CourseDao update(Integer studentId,CourseDao courseDao);
    CourseDao findById(Integer id);
    List<CourseDao> findAll();
    void delete(Integer id);

}
