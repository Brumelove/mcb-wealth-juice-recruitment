package mu.mcb.juice.recruitment.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import mu.mcb.juice.recruitment.dao.CourseDao;
import mu.mcb.juice.recruitment.service.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Brume
 **/
@RequiredArgsConstructor
@RestController
@SecurityRequirement(name = "Bearer Authentication")
@RequestMapping("/rest/courses")
public class CoursesController {
    private final CourseService service;

    @PostMapping("/{studentId}")
    public ResponseEntity<CourseDao> create(@Valid @RequestBody CourseDao course, @PathVariable final Integer studentId) {
        return ResponseEntity.ok().body(service.create(studentId, course));
    }

    @PutMapping("/{studentId}")
    public ResponseEntity<CourseDao> update(@Valid @RequestBody CourseDao course, @PathVariable final Integer studentId) {
        return ResponseEntity.ok().body(service.update(studentId, course));
    }

    @GetMapping()
    public ResponseEntity<List<CourseDao>> getAll() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseDao> getById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @GetMapping("/sum/{studentId}")
    public ResponseEntity<Integer> countCourseDurationByStudentId(@PathVariable Integer studentId) {
        return ResponseEntity.ok().body(service.sumCourseDurationByStudentId(studentId));
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<CourseDao>> getCoursesByStudentId(@PathVariable Integer studentId) {
        return ResponseEntity.ok().body(service.getCoursesByStudentId(studentId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}
