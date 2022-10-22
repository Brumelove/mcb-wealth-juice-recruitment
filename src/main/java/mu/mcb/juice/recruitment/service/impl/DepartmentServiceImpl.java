package mu.mcb.juice.recruitment.service.impl;

import lombok.RequiredArgsConstructor;
import mu.mcb.juice.recruitment.dao.DepartmentDao;
import mu.mcb.juice.recruitment.mapper.JuiceMapper;
import mu.mcb.juice.recruitment.repository.DepartmentRepository;
import mu.mcb.juice.recruitment.service.DepartmentService;
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
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository repository;
    private final JuiceMapper mapper;


    @Override
    public DepartmentDao create(DepartmentDao departmentDao) {
        var department = repository.save(mapper.mapDepartmentDtoToModelMapper(departmentDao));
        return mapper.mapDepartmentModelToDto(department);
    }

    @Override
    public DepartmentDao update(DepartmentDao departmentDao) {
        var oldDept = findById(departmentDao.getId());
        if (Objects.isNull(oldDept)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Department does not exist");
        } else {
            return create(departmentDao);
        }
    }

    @Override
    public DepartmentDao findById(Integer id) {
        var optionalDepartment = repository.findById(id);
        if (optionalDepartment.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Department with id not found");
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
