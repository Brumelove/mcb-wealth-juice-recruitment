package mu.mcb.juice.recruitment.service.impl;

import mu.mcb.juice.recruitment.dao.StudentDao;
import mu.mcb.juice.recruitment.entity.Student;
import mu.mcb.juice.recruitment.mapper.JuiceMapper;
import mu.mcb.juice.recruitment.repository.StudentRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

/**
 * @author Brume
 **/
@ExtendWith(MockitoExtension.class)
class StudentServiceImplTest {
    @Mock
    private StudentRepository repository;
    @Mock
    private JuiceMapper mapper;
    @InjectMocks
    private StudentServiceImpl service;

    @Test
    void findStudentsByInstructorId() {
        var student = student();
        var studentDao = studentDao();

        when(repository.findAllByCourses_instructorId(1)).thenReturn(List.of(student));
        when(mapper.mapStudentModelListToDto(List.of(student))).thenReturn(List.of(studentDao));

        var response = service.findStudentsByInstructorId(1);
        assertNotNull(response);
        assertEquals(1, response.size());
    }

    @Test
    void create() {
    }

    @Test
    void update() {
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

    private Student student() {
        var student = new Student();
        student.setId(1);
        student.setFirstName(RandomStringUtils.randomAlphabetic(2));

        return student;
    }
    private StudentDao studentDao() {
        var student = new StudentDao();
        student.setId(1);
        student.setFirstName(RandomStringUtils.randomAlphabetic(2));

        return student;
    }
}