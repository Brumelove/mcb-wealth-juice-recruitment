package mu.mcb.juice.recruitment.service;

import mu.mcb.juice.recruitment.dto.DepartmentDto;

import java.util.List;

/**
 * @author Brume
 **/
public interface DepartmentService {

    DepartmentDto create(DepartmentDto courseDto);
    DepartmentDto update(DepartmentDto courseDto);
    DepartmentDto findById(Integer id);
    List<DepartmentDto> findAll();
    void delete(Integer id);
}
