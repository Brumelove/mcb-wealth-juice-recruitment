package mu.mcb.juice.recruitment.repository;

import mu.mcb.juice.recruitment.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author Brume
 **/

public interface CourseRepository extends JpaRepository<Course, Integer> {
    List<Course> findAllByStudent_Id(Integer studentId);
    @Query("select sum (c.duration) from Course c where c.student.id =:studentId")
    Integer sumCourseDurationByStudentId(Integer studentId);
}
