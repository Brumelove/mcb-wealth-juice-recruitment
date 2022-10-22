package mu.mcb.juice.recruitment.service;

import mu.mcb.juice.recruitment.dto.StudentDto;

import java.util.List;

/**
 * @author Brume
 **/
public interface StudentService {
     List<StudentDto> findStudentByInstructorId(Integer instructorId);
    StudentDto create(StudentDto courseDto);
    StudentDto update(StudentDto courseDto);
    StudentDto findById(Integer id);
    List<StudentDto> findAll();
    void delete(Integer id);
}
