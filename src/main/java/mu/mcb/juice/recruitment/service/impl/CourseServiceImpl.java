package mu.mcb.juice.recruitment.service.impl;

import lombok.RequiredArgsConstructor;
import mu.mcb.juice.recruitment.dao.CourseDao;
import mu.mcb.juice.recruitment.entity.Course;
import mu.mcb.juice.recruitment.mapper.JuiceMapper;
import mu.mcb.juice.recruitment.repository.CourseRepository;
import mu.mcb.juice.recruitment.service.CourseService;
import mu.mcb.juice.recruitment.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;

/**
 * @author Brume
 **/
@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
    private final CourseRepository repository;
    private final StudentService studentService;
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
    public CourseDao create(Integer studentId, CourseDao courseDao) {
        findByUserNameOrEmail(courseDao.getDepartmentName(), courseDao.getName());

        return createNew(studentId, courseDao);
    }

    private CourseDao createNew(Integer studentId, CourseDao courseDao) {
        var student = studentService.findById(studentId);
        courseDao.setStudent(student);

        var newCourse = mapper.mapCourseDtoToModelMapper(courseDao);
        return mapper.mapCourseModelToDto(repository.save(newCourse));
    }

    private void findByUserNameOrEmail(String departmentName, String name) {
        var optionalCourse = repository.findByDepartmentNameAndName(departmentName, name);
        if (optionalCourse.isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Course with " + name + "for  " + departmentName + " Department already exists");
    }

    @Override
    public CourseDao update(Integer studentId, CourseDao courseDao) {

        var oldCourse = findById(courseDao.getId());
        if (Objects.isNull(oldCourse)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "course does not exist");
        } else {
            return createNew(studentId, courseDao);
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

    @Override
    public void delete(Integer id) {
        findById(id);
        repository.deleteById(id);
    }
}
