package mu.mcb.juice.recruitment.service;

import mu.mcb.juice.recruitment.dao.InstructorDao;

import java.util.List;

/**
 * @author Brume
 **/
public interface InstructorService {
    boolean existsById(Integer id);
    InstructorDao create(InstructorDao courseDto);
    InstructorDao update(InstructorDao courseDto);
    InstructorDao findById(Integer id);
    List<InstructorDao> findAll();
    void delete(Integer id);
}
