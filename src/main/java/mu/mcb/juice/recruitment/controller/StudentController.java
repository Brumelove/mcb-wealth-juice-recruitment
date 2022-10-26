package mu.mcb.juice.recruitment.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import mu.mcb.juice.recruitment.dao.StudentDao;
import mu.mcb.juice.recruitment.service.StudentService;
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
@RequestMapping("/rest/student")
public class StudentController {
    public final StudentService service;

    @PostMapping()
    public ResponseEntity<StudentDao> create(@Valid @RequestBody StudentDao student) {
        return ResponseEntity.ok().body(service.create(student));
    }

    @PutMapping()
    public ResponseEntity<StudentDao> update(@Valid @RequestBody StudentDao student) {
        return ResponseEntity.ok().body(service.update(student));
    }

    @GetMapping()
    public ResponseEntity<List<StudentDao>> getAll() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDao> getById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @GetMapping("/instructor/{instructorId}")
    public ResponseEntity<List<StudentDao>> getStudentsByInstructorId(@PathVariable Integer instructorId) {
        return ResponseEntity.ok().body(service.findStudentsByInstructorId(instructorId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}
