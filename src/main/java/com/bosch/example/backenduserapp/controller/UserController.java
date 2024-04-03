package com.bosch.example.backenduserapp.controller;

import com.bosch.example.backenduserapp.model.dtos.UserDto;
import com.bosch.example.backenduserapp.model.entities.User;
import com.bosch.example.backenduserapp.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserDto> findAll() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Optional<UserDto> userOptional = userService.findById(id);

        return userOptional.isPresent()
                ? ResponseEntity.ok(userOptional.orElseThrow())
                : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody User user, BindingResult result) {
        if (result.hasErrors()) {
            return validation(result);
        }
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userService.save(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody User user, BindingResult result, @PathVariable Long id) {
        if (result.hasErrors()) {
            return validation(result);
        }
        Optional<UserDto> userOptional = userService.update(user, id);
        if (userOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(userOptional.orElseThrow());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        Optional<UserDto> userOptional = userService.findById(id);
        if (userOptional.isPresent()) {
            userService.deleteById(id);
            return ResponseEntity.noContent().build(); // 204
        }
        return ResponseEntity.notFound().build(); // 404
    }

    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errorsMap = new HashMap<>();
        result.getFieldErrors().forEach(err ->
                errorsMap.put(err.getField(), "Field '" + err.getField() + "' " + err.getDefaultMessage())
        );
        return ResponseEntity.badRequest().body(errorsMap);
    }
}
