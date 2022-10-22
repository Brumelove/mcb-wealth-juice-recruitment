package mu.mcb.juice.recruitment.service;

import mu.mcb.juice.recruitment.dao.DepartmentDao;

import java.util.List;

/**
 * @author Brume
 **/
public interface DepartmentService {

    DepartmentDao create(DepartmentDao courseDto);
    DepartmentDao update(DepartmentDao courseDto);
    DepartmentDao findById(Integer id);
    List<DepartmentDao> findAll();
    void delete(Integer id);
}
