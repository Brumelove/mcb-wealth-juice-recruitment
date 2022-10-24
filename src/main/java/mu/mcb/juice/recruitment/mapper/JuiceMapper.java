package mu.mcb.juice.recruitment.mapper;

import mu.mcb.juice.recruitment.dao.*;
import mu.mcb.juice.recruitment.entity.*;
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

    UserDao mapUserModelToDto(User source);
    User mapUserDtoToModelMapper(UserDao target);

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

    List<UserDao> mapUserModelListToDto(List<User> all);
}
