package mu.mcb.juice.recruitment.service.impl;

import lombok.RequiredArgsConstructor;
import mu.mcb.juice.recruitment.dao.CourseDao;
import mu.mcb.juice.recruitment.entity.Course;
import mu.mcb.juice.recruitment.exception.BadRequestException;
import mu.mcb.juice.recruitment.exception.ElementNotFoundException;
import mu.mcb.juice.recruitment.mapper.JuiceMapper;
import mu.mcb.juice.recruitment.repository.CourseRepository;
import mu.mcb.juice.recruitment.service.CourseService;
import mu.mcb.juice.recruitment.service.DepartmentService;
import mu.mcb.juice.recruitment.service.InstructorService;
import mu.mcb.juice.recruitment.service.StudentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private final InstructorService instructorService;
    private final DepartmentService departmentService;
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

    @Transactional
    @Override
    public CourseDao create(Integer studentId, CourseDao courseDao) {
        if (courseDao.getId() > 0) {
            throw new BadRequestException("The ID must not be provided when creating a new Course");
        }
        return createNew(studentId, courseDao);
    }


    private CourseDao createNew(Integer studentId, CourseDao courseDao) {
        var instructor = instructorService.existsById(courseDao.getInstructorId());
        var dept = departmentService.findByName(courseDao.getDepartmentName());
        if (!instructor && !dept) {
            throw new ElementNotFoundException("Instructor id or Department Name does not exist");
        }

        var student = studentService.findById(studentId);

        courseDao.setStudent(student);
        var newCourse = mapper.mapCourseDtoToModelMapper(courseDao);
        return mapper.mapCourseModelToDto(repository.save(newCourse));
    }

    @Transactional
    @Override
    public CourseDao update(Integer studentId, CourseDao courseDao) {
        var oldCourse = findById(courseDao.getId());
        if (Objects.isNull(oldCourse)) {
            throw new ElementNotFoundException("course does not exist");
        } else {
            return createNew(studentId, courseDao);
        }
    }

    @Override
    public CourseDao findById(Integer id) {
        var optionalCourse = repository.findById(id);
        if (optionalCourse.isEmpty()) {
            throw new ElementNotFoundException("Course id not found");
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
