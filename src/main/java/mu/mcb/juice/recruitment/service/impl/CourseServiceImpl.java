package mu.mcb.juice.recruitment.service.impl;

import lombok.RequiredArgsConstructor;
import mu.mcb.juice.recruitment.dto.CourseDto;
import mu.mcb.juice.recruitment.mapper.JuiceMapper;
import mu.mcb.juice.recruitment.repository.CourseRepository;
import mu.mcb.juice.recruitment.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;

/**
 * @author Brume
 **/
@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
    private final CourseRepository repository;
    private final JuiceMapper mapper;

    @Override
    public List<CourseDto> getCoursesByStudentId(Integer studentId) {
        return mapper.mapCourseModelListToDto(repository.findAllByStudent_Id(studentId));
    }

    @Override
    public Integer countCourseDurationByStudentId(Integer studentId) {
        return repository.countCourseDurationByStudentId(studentId);
    }

    @Override
    public CourseDto create(CourseDto courseDto) {
        findByUserNameOrEmail(courseDto.getDepartmentName(), courseDto.getName());

        return createNew(courseDto);
    }

    private CourseDto createNew(CourseDto courseDto) {
        var newCourse = repository.save(mapper.mapCourseDtoToModelMapper(courseDto));
        return mapper.mapCourseModelToDto(newCourse);
    }

    private void findByUserNameOrEmail(String departmentName, String name) {
        var optionalCourse = repository.findByDepartmentNameAndName(departmentName, name);
        if (optionalCourse.isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Course with " + name + "for  " + departmentName + " Department already exists");
    }

    @Override
    public CourseDto update(CourseDto courseDto) {

        var oldCourse = findById(courseDto.getId());
        if (Objects.isNull(oldCourse)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "course does not exist");
        } else {
            return createNew(courseDto);
        }
    }

    @Override
    public CourseDto findById(Integer id) {
        var optionalCourse = repository.findById(id);
        if (optionalCourse.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Course id not found");
        }
        return mapper.mapCourseModelToDto(optionalCourse.get());
    }

    @Override
    public List<CourseDto> findAll() {
        return mapper.mapCourseModelListToDto(repository.findAll());
    }

    @Override
    public void delete(Integer id) {
        findById(id);
        repository.deleteById(id);
    }


}
