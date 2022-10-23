package mu.mcb.juice.recruitment.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mu.mcb.juice.recruitment.dao.CourseDao;
import mu.mcb.juice.recruitment.entity.Course;
import mu.mcb.juice.recruitment.mapper.JuiceMapper;
import mu.mcb.juice.recruitment.repository.CourseRepository;
import mu.mcb.juice.recruitment.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

/**
 * @author Brume
 **/
@Service
@RequiredArgsConstructor
@Slf4j
public class CourseServiceImpl implements CourseService {
    private final CourseRepository repository;
    private final JuiceMapper mapper;

    @Override
    public List<CourseDao> getCoursesByStudentId(Integer studentId) {
        List<Course> courseList = repository.findAllByStudent_Id(studentId);
        return mapper.mapCourseModelListToDto(courseList);
    }

    @Override
    public Integer sumCourseDurationByStudentId(Integer studentId) {
        return repository.sumCourseDurationByStudentId(studentId);
    }

    @Override
    public CourseDao create(CourseDao courseDao) {
//       var response = findByDepartmentNameAndName(courseDao.getDepartmentName(), courseDao.getName());
//       log.info("COURSES" +response);
        return createNew(courseDao);
    }

    private CourseDao createNew(CourseDao courseDao) {
        var newCourse = mapper.mapCourseDtoToModelMapper(courseDao);
        return mapper.mapCourseModelToDto(repository.save(newCourse));
    }

    private Optional<Course> findByDepartmentNameAndName(String departmentName, String name) {
        var optionalCourse = repository.findByDepartmentNameAndName(departmentName, name);
        if (optionalCourse.isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Course with " + name + "for  " + departmentName + " Department already exists");
        else return optionalCourse;
    }

    @Override
    public CourseDao update(CourseDao courseDao) {

        var oldCourse = findById(courseDao.getId());
        if (Objects.isNull(oldCourse)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "course does not exist");
        } else {
            return createNew(courseDao);
        }
    }

    @Override
    public CourseDao findById(Integer id) {
        var optionalCourse = repository.findById(id);
        if (optionalCourse.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Course id not found");
        }
        return mapper.mapCourseModelToDto(optionalCourse.get());
    }

    @Override
    public List<CourseDao> findAll() {
        return mapper.mapCourseModelListToDto(repository.findAll());
    }

    public Set<Course> findAllByIdIn(Collection<Integer> courseId) {

        var courses = repository.findAllByIdIn(courseId);
        if (CollectionUtils.isEmpty(courses))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "please check the ids properly");
        else return Set.copyOf(courses);
    }

    @Override
    public void delete(Integer id) {
        findById(id);
        repository.deleteById(id);
    }
}
