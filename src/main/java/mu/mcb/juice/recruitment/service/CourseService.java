package mu.mcb.juice.recruitment.service;

import mu.mcb.juice.recruitment.dao.CourseDao;
import mu.mcb.juice.recruitment.entity.Course;

import java.util.Collection;
import java.util.List;
import java.util.Set;

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

    CourseDao create(CourseDao courseDao);
    CourseDao update(CourseDao courseDao);
    CourseDao findById(Integer id);
    List<CourseDao> findAll();
    Set<Course> findAllByIdIn(Collection<Integer> courseId);
    void delete(Integer id);

}
