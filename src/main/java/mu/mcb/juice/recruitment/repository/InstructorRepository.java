package mu.mcb.juice.recruitment.repository;

import mu.mcb.juice.recruitment.model.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Brume
 **/
public interface InstructorRepository extends JpaRepository<Instructor, Integer> {
}
