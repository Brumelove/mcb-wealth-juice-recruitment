package mu.mcb.juice.recruitment.service;

import mu.mcb.juice.recruitment.dto.CourseDto;

import java.util.List;

/**
 * @author Brume
 **/
public interface CourseService {
    /**
     * @param studentId
     * @return  List<CourseDto>
     */
    List<CourseDto> getCoursesByStudentId(Integer studentId);

    Integer countCourseDurationByStudentId(Integer studentId);

    CourseDto create(CourseDto courseDto);
    CourseDto update(CourseDto courseDto);
    CourseDto findById(Integer id);
    List<CourseDto> findAll();
    void delete(Integer id);

}
