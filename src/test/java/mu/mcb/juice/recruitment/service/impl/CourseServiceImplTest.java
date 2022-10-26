package mu.mcb.juice.recruitment.service.impl;

import mu.mcb.juice.recruitment.dao.CourseDao;
import mu.mcb.juice.recruitment.dao.StudentDao;
import mu.mcb.juice.recruitment.entity.Course;
import mu.mcb.juice.recruitment.entity.Student;
import mu.mcb.juice.recruitment.mapper.JuiceMapper;
import mu.mcb.juice.recruitment.repository.CourseRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

/**
 * @author Brume
 **/
@ExtendWith(MockitoExtension.class)
class CourseServiceImplTest {
    @Mock
    private CourseRepository repository;
    @Mock
    private JuiceMapper mapper;
    @Mock
    private StudentServiceImpl studentService;
    @Mock
    private InstructorServiceImpl instructorService;
    @Mock
    private DepartmentServiceImpl departmentService;
    @InjectMocks
    private CourseServiceImpl service;

    @Test
    void getCoursesByStudentId() {
        var courseList = createCourses();
        when(repository.findAllByStudent_Id(1)).thenReturn(courseList);
        when(mapper.mapCourseModelListToDto(courseList)).thenReturn(createCoursesDao());

        var response = service.getCoursesByStudentId(1);

        assertNotNull(response);
        assertEquals(2, response.size());
    }

    @Test
    void sumCourseDurationByStudentId() {
        when(repository.sumCourseDurationByStudentId(1)).thenReturn(30);
        var response = service.sumCourseDurationByStudentId(1);

        assertNotNull(response);
        assertEquals(30, response);
    }

    private List<CourseDao> createCoursesDao() {
        var student = new StudentDao();
        student.setId(1);

        var course1 = new CourseDao();
        course1.setDuration(20);
        course1.setId(1);
        course1.setInstructorId(1);

        var course2 = new CourseDao();
        course2.setDuration(30);
        course2.setId(1);

        return List.of(course1, course2);
    }

    private List<Course> createCourses() {
        var student = new Student();
        student.setId(1);

        var course1 = new Course();
        course1.setDuration(20);
        course1.setId(1);
        course1.setInstructorId(1);

        var course2 = new Course();
        course2.setDuration(30);
        course2.setId(1);
        course2.setStudent(student);

        return List.of(course1, course2);
    }

    @Test
    void update() {
        var course = createCourses().get(0);
        var courseDao = createCoursesDao().get(0);

        when(repository.findById(1)).thenReturn(Optional.ofNullable(course));
        when(instructorService.existsById(1)).thenReturn(true);
        when(departmentService.findByName(courseDao.getDepartmentName())).thenReturn(true);
        when(studentService.findById(1)).thenReturn(courseDao.getStudent());

        when(service.findById(1)).thenReturn(courseDao);


        when(mapper.mapCourseDtoToModelMapper(courseDao)).thenReturn(course);
        when(repository.save(course)).thenReturn(course);

        when(mapper.mapCourseModelToDto(course)).thenReturn(courseDao);

        var response = service.update(1, courseDao);

        assertNotNull(response);
        assertEquals(1, response.getId());
    }

    @Test
    void findById() {
    }

    @Test
    void findAll() {
    }

    @Test
    void delete() {
    }
}