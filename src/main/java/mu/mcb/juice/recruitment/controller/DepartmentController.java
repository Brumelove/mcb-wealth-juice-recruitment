package mu.mcb.juice.recruitment.controller;

import lombok.RequiredArgsConstructor;
import mu.mcb.juice.recruitment.dao.DepartmentDao;
import mu.mcb.juice.recruitment.service.DepartmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Brume
 **/
@RequiredArgsConstructor
@RestController
@RequestMapping("/rest/departments")
public class DepartmentController {
    public final DepartmentService service;

    @PostMapping()
    public ResponseEntity<DepartmentDao> create(@Valid @RequestBody DepartmentDao department) {
        return ResponseEntity.ok().body(service.create(department));
    }

    @PutMapping()
    public ResponseEntity<DepartmentDao> update(@Valid @RequestBody DepartmentDao department) {
        return ResponseEntity.ok().body(service.update(department));
    }

    @GetMapping()
    public ResponseEntity<List<DepartmentDao>> getAll() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentDao> getById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}
