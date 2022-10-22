package mu.mcb.juice.recruitment.repository;

import mu.mcb.juice.recruitment.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Brume
 **/
public interface StudentRepository extends JpaRepository<Student, Integer> {
    List<Student> findAllByCourses_instructorId(Integer coursesInstructorId);
}
