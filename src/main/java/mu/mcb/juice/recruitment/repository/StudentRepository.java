package mu.mcb.juice.recruitment.repository;

import mu.mcb.juice.recruitment.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Brume
 **/
@Transactional
public interface StudentRepository extends JpaRepository<Student, Integer> {
    List<Student> findAllByCourses_instructorId(Integer coursesInstructorId);
}
