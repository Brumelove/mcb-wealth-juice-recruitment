package mu.mcb.juice.recruitment.controller;

import lombok.RequiredArgsConstructor;
import mu.mcb.juice.recruitment.dao.InstructorDao;
import mu.mcb.juice.recruitment.service.InstructorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Brume
 **/
@RequiredArgsConstructor
@RestController
@RequestMapping("/rest/instructors")
public class InstructorController {
    public final InstructorService service;

    @PostMapping()
    public ResponseEntity<InstructorDao> create(@Valid @RequestBody InstructorDao instructor) {
        return ResponseEntity.ok().body(service.create(instructor));
    }

    @PutMapping()
    public ResponseEntity<InstructorDao> update(@Valid @RequestBody InstructorDao instructor) {
        return ResponseEntity.ok().body(service.update(instructor));
    }

    @GetMapping()
    public ResponseEntity<List<InstructorDao>> getAll() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<InstructorDao> getById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}
