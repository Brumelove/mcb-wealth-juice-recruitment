package mu.mcb.juice.recruitment.service.impl;

import lombok.RequiredArgsConstructor;
import mu.mcb.juice.recruitment.dto.InstructorDto;
import mu.mcb.juice.recruitment.mapper.JuiceMapper;
import mu.mcb.juice.recruitment.repository.InstructorRepository;
import mu.mcb.juice.recruitment.service.InstructorService;
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
public class InstructorServiceImpl implements InstructorService {
    private final InstructorRepository repository;
    private final JuiceMapper mapper;


    @Override
    public InstructorDto create(InstructorDto instructorDto) {
        var instructor = repository.save(mapper.mapInstructorDtoToMapper(instructorDto));
        return mapper.mapInstructorModelToDto(instructor);
    }

    @Override
    public InstructorDto update(InstructorDto instructorDto) {
        var oldInstructor = findById(instructorDto.getId());
        if (Objects.isNull(oldInstructor)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Department does not exist");
        } else {
            return create(instructorDto);
        }
    }

    @Override
    public InstructorDto findById(Integer id) {
        var optionalDepartment = repository.findById(id);
        if (optionalDepartment.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Instructor with id not found");
        }
        return mapper.mapInstructorModelToDto(optionalDepartment.get());
    }

    @Override
    public List<InstructorDto> findAll() {
        return mapper.mapInstructorModelListToDto(repository.findAll());
    }

    @Override
    public void delete(Integer id) {
        findById(id);
        repository.deleteById(id);
    }
}
