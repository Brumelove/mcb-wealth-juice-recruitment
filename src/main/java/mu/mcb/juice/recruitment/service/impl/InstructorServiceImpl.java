package mu.mcb.juice.recruitment.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mu.mcb.juice.recruitment.dao.InstructorDao;
import mu.mcb.juice.recruitment.entity.Instructor;
import mu.mcb.juice.recruitment.exception.BadRequestException;
import mu.mcb.juice.recruitment.exception.ElementNotFoundException;
import mu.mcb.juice.recruitment.mapper.JuiceMapper;
import mu.mcb.juice.recruitment.repository.InstructorRepository;
import mu.mcb.juice.recruitment.service.DepartmentService;
import mu.mcb.juice.recruitment.service.InstructorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @author Brume
 **/
@Service
@RequiredArgsConstructor
@Slf4j
public class InstructorServiceImpl implements InstructorService {
    private final InstructorRepository repository;
    private final JuiceMapper mapper;
    private final DepartmentService departmentService;


    @Override
    public boolean existsById(Integer id) {
        return repository.existsById(id);
    }

    @Override
    public InstructorDao create(InstructorDao instructorDao) {
        if (instructorDao.getId() > 0) {
            throw new BadRequestException("The ID must not be provided when creating a new Instructor");
        }
        if (!departmentService.findByName(instructorDao.getDepartmentName())) {
            throw new ElementNotFoundException("Department Name does not exist");
        }
        return createNew(instructorDao);
    }

    private InstructorDao createNew(InstructorDao instructorDao) {
        Instructor entity = mapper.mapInstructorDtoToMapper(instructorDao);

        Instructor instructor = repository.save(entity);

        return mapper.mapInstructorModelToDto(instructor);
    }

    @Override
    public InstructorDao update(InstructorDao instructorDao) {
        var oldInstructor = findById(instructorDao.getId());
        if (Objects.isNull(oldInstructor)) {
            throw new ElementNotFoundException("Department does not exist");
        } else {
            return createNew(instructorDao);
        }
    }

    @Override
    public InstructorDao findById(Integer id) {
        var optionalDepartment = repository.findById(id);
        if (optionalDepartment.isEmpty()) {
            throw new ElementNotFoundException("Instructor with id not found");
        }
        return mapper.mapInstructorModelToDto(optionalDepartment.get());
    }

    @Override
    public List<InstructorDao> findAll() {
        return mapper.mapInstructorModelListToDto(repository.findAll());
    }

    @Override
    public void delete(Integer id) {
        findById(id);
        repository.deleteById(id);
    }
}
