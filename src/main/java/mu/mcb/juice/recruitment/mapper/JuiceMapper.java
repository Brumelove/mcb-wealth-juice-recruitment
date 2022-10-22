package mu.mcb.juice.recruitment.mapper;

import mu.mcb.juice.recruitment.dto.*;
import mu.mcb.juice.recruitment.model.*;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @author Brume
 **/
@Mapper(componentModel = "spring")
public interface JuiceMapper {
    UserDto mapUserModelToDto(User source);
    User mapUserDtoToModelMapper(UserDto target);

    InstructorDto mapInstructorModelToDto(Instructor source);
    List<InstructorDto> mapInstructorModelListToDto(List<Instructor> source);
    Instructor mapInstructorDtoToMapper(InstructorDto target);

    StudentDto mapStudentModelToDto(Student source);
    List<StudentDto> mapStudentModelListToDto(List<Student> source);
    Student mapStudentDtoToMapper(StudentDto target);
    List<Student> mapStudentDtoListToMapper(List<StudentDto> target);


    DepartmentDto mapDepartmentModelToDto(Department source);
   List<DepartmentDto>  mapDepartmentModelListToDto(List<Department> source);
    Department mapDepartmentDtoToModelMapper(DepartmentDto target);

    CourseDto mapCourseModelToDto(Course source);
    List<CourseDto> mapCourseModelListToDto(List<Course> source);
    Course mapCourseDtoToModelMapper(CourseDto target);
}
