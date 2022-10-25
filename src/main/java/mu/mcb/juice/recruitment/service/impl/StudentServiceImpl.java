package mu.mcb.juice.recruitment.service.impl;

import lombok.RequiredArgsConstructor;
import mu.mcb.juice.recruitment.dao.StudentDao;
import mu.mcb.juice.recruitment.exception.BadRequestException;
import mu.mcb.juice.recruitment.exception.ElementNotFoundException;
import mu.mcb.juice.recruitment.mapper.JuiceMapper;
import mu.mcb.juice.recruitment.repository.StudentRepository;
import mu.mcb.juice.recruitment.service.StudentService;
import org.springframework.stereotype.Service;

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
        if (studentDao.getId() > 0) {
            throw new BadRequestException("The ID must not be provided when creating a new Student");
        }
        return createNew(studentDao);
    }

    private StudentDao createNew(StudentDao studentDao) {
        var student = mapper.mapStudentDtoToMapper(studentDao);

        return mapper.mapStudentModelToDto(repository.save(student));
    }

    @Override
    public StudentDao update(StudentDao studentDao) {
        findById(studentDao.getId());

        return createNew(studentDao);
    }

    @Override
    public StudentDao findById(Integer id) {
        var optionalStudent = repository.findById(id);
        if (optionalStudent.isEmpty()) {
            throw new ElementNotFoundException( "Student with id not found");
        }
        return mapper.mapStudentModelToDto(optionalStudent.get());
    }

    @Override
    public boolean existsById(Integer id) {
        return repository.existsById(id);
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
