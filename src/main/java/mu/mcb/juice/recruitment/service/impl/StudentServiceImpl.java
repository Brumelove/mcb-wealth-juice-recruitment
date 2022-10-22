package mu.mcb.juice.recruitment.service.impl;

import lombok.RequiredArgsConstructor;
import mu.mcb.juice.recruitment.dto.StudentDto;
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
    public List<StudentDto> findStudentByInstructorId(Integer instructorId) {
        return mapper.mapStudentModelListToDto(repository.findAllByCourses_instructorId(instructorId));
    }

    @Override
    public StudentDto create(StudentDto studentDto) {
        var student = repository.save(mapper.mapStudentDtoToMapper(studentDto));
        return mapper.mapStudentModelToDto(student);    }

    @Override
    public StudentDto update(StudentDto studentDto) {
        findById(studentDto.getId());

        return create(studentDto);
    }

    @Override
    public StudentDto findById(Integer id) {
        var optionalStudent = repository.findById(id);
        if (optionalStudent.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Student with id not found");
        }
        return mapper.mapStudentModelToDto(optionalStudent.get());
    }

    @Override
    public List<StudentDto> findAll() {
        return mapper.mapStudentModelListToDto(repository.findAll());
    }

    @Override
    public void delete(Integer id) {
        findById(id);
        repository.deleteById(id);
    }
}
