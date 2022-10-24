package mu.mcb.juice.recruitment.repository;

import mu.mcb.juice.recruitment.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Brume
 **/
public interface DepartmentRepository extends JpaRepository<Department, Integer> {
    boolean existsByName(String name);
}
