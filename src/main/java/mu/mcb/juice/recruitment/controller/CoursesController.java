package mu.mcb.juice.recruitment.controller;

import lombok.RequiredArgsConstructor;
import mu.mcb.juice.recruitment.dao.CourseDao;
import mu.mcb.juice.recruitment.service.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Brume
 **/
@RequiredArgsConstructor
@RestController
@Validated
@RequestMapping("/courses")
public class CoursesController {
    private final CourseService service;

    @PostMapping()
    public ResponseEntity<CourseDao> create(@Valid @RequestBody CourseDao course) {
        return ResponseEntity.ok().body(service.create(course));
    }

    @PutMapping()
    public ResponseEntity<CourseDao> update(@Valid @RequestBody CourseDao course) {
        return ResponseEntity.ok().body(service.update(course));
    }

    @GetMapping()
    public ResponseEntity<List<CourseDao>> getAll() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseDao> getById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @GetMapping("/count/{studentId}")
    public ResponseEntity<Integer> countCourseDurationByStudentId(@PathVariable Integer studentId) {
        return ResponseEntity.ok().body(service.countCourseDurationByStudentId(studentId));
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<List<CourseDao>> getCoursesByStudentId(@PathVariable Integer studentId) {
        return ResponseEntity.ok().body(service.getCoursesByStudentId(studentId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}