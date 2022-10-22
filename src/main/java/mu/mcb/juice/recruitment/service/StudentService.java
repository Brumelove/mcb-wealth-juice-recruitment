package mu.mcb.juice.recruitment.service;

import mu.mcb.juice.recruitment.dao.StudentDao;

import java.util.List;

/**
 * @author Brume
 **/
public interface StudentService {
     List<StudentDao> findStudentsByInstructorId(Integer instructorId);
    StudentDao create(StudentDao courseDto);
    StudentDao update(StudentDao courseDto);
    StudentDao findById(Integer id);
    List<StudentDao> findAll();
    void delete(Integer id);
}
