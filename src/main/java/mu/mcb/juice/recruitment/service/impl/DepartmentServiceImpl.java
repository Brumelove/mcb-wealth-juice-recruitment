package mu.mcb.juice.recruitment.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mu.mcb.juice.recruitment.dao.DepartmentDao;
import mu.mcb.juice.recruitment.exception.BadRequestException;
import mu.mcb.juice.recruitment.exception.ElementNotFoundException;
import mu.mcb.juice.recruitment.mapper.JuiceMapper;
import mu.mcb.juice.recruitment.repository.DepartmentRepository;
import mu.mcb.juice.recruitment.service.DepartmentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @author Brume
 **/
@Service
@RequiredArgsConstructor
@Slf4j
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository repository;
    private final JuiceMapper mapper;


    @Override
    public boolean findByName(String name) {
        return repository.existsByName(name);
    }

    @Override
    public DepartmentDao create(DepartmentDao departmentDao) {
        if (departmentDao.getId() != null) {
            throw new BadRequestException("The ID must not be provided when creating a new Department");
        }
        return createNew(departmentDao);
    }

    private DepartmentDao createNew(DepartmentDao departmentDao) {
        var department = repository.save(mapper.mapDepartmentDtoToModelMapper(departmentDao));
        log.info("DATA ::: ::: " +department);
        return mapper.mapDepartmentModelToDto(department);
    }

    @Override
    public DepartmentDao update(DepartmentDao departmentDao) {
        var oldDept = findById(departmentDao.getId());
        if (Objects.isNull(oldDept)) {
            throw new ElementNotFoundException( "Department does not exist");
        } else {
            return createNew(departmentDao);
        }
    }

    @Override
    public DepartmentDao findById(Integer id) {
        var optionalDepartment = repository.findById(id);
        if (optionalDepartment.isEmpty()) {
            throw new ElementNotFoundException("Department with id not found");
        }
        return mapper.mapDepartmentModelToDto(optionalDepartment.get());
    }

    @Override
    public List<DepartmentDao> findAll() {
        return mapper.mapDepartmentModelListToDto(repository.findAll());
    }

    @Override
    public void delete(Integer id) {
        findById(id);
        repository.deleteById(id);
    }
}
