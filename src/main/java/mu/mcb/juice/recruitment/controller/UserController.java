package mu.mcb.juice.recruitment.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mu.mcb.juice.recruitment.dao.LoginRequest;
import mu.mcb.juice.recruitment.dao.UserDao;
import mu.mcb.juice.recruitment.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

/**
 * @author Brume
 **/
@RequiredArgsConstructor
@RestController
@Slf4j
@Validated
@RequestMapping("/users")
public class UserController {
    private final UserService service;

    @PostMapping("/create")
    public ResponseEntity<UserDao> create(@Valid @RequestBody UserDao user) {
        return ResponseEntity.ok().body(service.create(user));
    }
    @PostMapping("/login")
    public ResponseEntity<UserDetails> login(HttpServletRequest request, @Valid @RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok().body(service.login(request, loginRequest));
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> login(HttpServletRequest request, HttpServletResponse response) {
        service.logout(request, response);
        return ResponseEntity.ok().build();
    }

    @PutMapping()
    public ResponseEntity<UserDao> update(@Valid @RequestBody UserDao user) {
        return ResponseEntity.ok().body(service.update(user));
    }

    @GetMapping()
    public ResponseEntity<List<UserDao>> getAll() {
        return ResponseEntity.ok().body(service.findAll());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}
