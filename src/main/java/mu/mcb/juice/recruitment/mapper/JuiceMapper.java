package mu.mcb.juice.recruitment.mapper;

import mu.mcb.juice.recruitment.dao.CourseDao;
import mu.mcb.juice.recruitment.dao.DepartmentDao;
import mu.mcb.juice.recruitment.dao.InstructorDao;
import mu.mcb.juice.recruitment.dao.StudentDao;
import mu.mcb.juice.recruitment.entity.Course;
import mu.mcb.juice.recruitment.entity.Department;
import mu.mcb.juice.recruitment.entity.Instructor;
import mu.mcb.juice.recruitment.entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author Brume
 **/
@Mapper(componentModel = "spring")
public interface JuiceMapper {
    static JuiceMapper getJuiceMapper() {
        return Mappers.getMapper(JuiceMapper.class);
    }


    InstructorDao mapInstructorModelToDto(Instructor source);
    List<InstructorDao> mapInstructorModelListToDto(List<Instructor> source);
    Instructor mapInstructorDtoToMapper(InstructorDao target);

    StudentDao mapStudentModelToDto(Student source);
    List<StudentDao> mapStudentModelListToDto(List<Student> source);
    Student mapStudentDtoToMapper(StudentDao source);


    DepartmentDao mapDepartmentModelToDto(Department source);
   List<DepartmentDao>  mapDepartmentModelListToDto(List<Department> source);
    Department mapDepartmentDtoToModelMapper(DepartmentDao target);

    CourseDao mapCourseModelToDto(Course source);
    List<CourseDao> mapCourseModelListToDto(List<Course> source);
    Course mapCourseDtoToModelMapper(CourseDao target);

}
