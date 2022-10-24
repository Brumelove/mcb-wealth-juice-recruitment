package mu.mcb.juice.recruitment.service.impl;

import lombok.RequiredArgsConstructor;
import mu.mcb.juice.recruitment.dao.StudentDao;
import mu.mcb.juice.recruitment.mapper.JuiceMapper;
import mu.mcb.juice.recruitment.repository.StudentRepository;
import mu.mcb.juice.recruitment.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * @author Brume
 **/
@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final JuiceMapper mapper;
    private final StudentRepository repository;

    @Override
    public List<StudentDao> findStudentsByInstructorId(Integer instructorId) {
        return mapper.mapStudentModelListToDto(repository.findAllByCourses_instructorId(instructorId));
    }

    @Override
    public StudentDao create(StudentDao studentDao) {
        var student = mapper.mapStudentDtoToMapper(studentDao);

        return mapper.mapStudentModelToDto(repository.save(student));
    }

    @Override
    public StudentDao update(StudentDao studentDao) {
        findById(studentDao.getId());

        return create(studentDao);
    }

    @Override
    public StudentDao findById(Integer id) {
        var optionalStudent = repository.findById(id);
        if (optionalStudent.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Student with id not found");
        }
        return mapper.mapStudentModelToDto(optionalStudent.get());
    }

    @Override
    public List<StudentDao> findAll() {
        return mapper.mapStudentModelListToDto(repository.findAll());
    }

    @Override
    public void delete(Integer id) {
        findById(id);
        repository.deleteById(id);
    }
}
