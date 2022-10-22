package mu.mcb.juice.recruitment.repository;

import mu.mcb.juice.recruitment.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * @author Brume
 **/

public interface CourseRepository extends JpaRepository<Course, Integer> {
    List<Course> findAllByStudent_Id(Integer studentId);
    Optional<Course> findByDepartmentNameAndName(String departmentName, String name);

    @Query("select count(c.duration) from Course c where c.student.id =:studentId")
    Integer countCourseDurationByStudentId(Integer studentId);
}
