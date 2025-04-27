package SkillSync_PAF_Y3S2_WE_193_BE.controller;

import SkillSync_PAF_Y3S2_WE_193_BE.model.User;
import SkillSync_PAF_Y3S2_WE_193_BE.model.Project;
import SkillSync_PAF_Y3S2_WE_193_BE.model.Skill;
import SkillSync_PAF_Y3S2_WE_193_BE.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        return ResponseEntity.ok(userService.getUserByUsername(username));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
        return ResponseEntity.ok(userService.getUserByEmail(email));
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
        return ResponseEntity.ok(userService.updateUser(id, userDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{userId}/skills/{skillId}")
    public ResponseEntity<User> addSkillToUser(
            @PathVariable Long userId,
            @PathVariable Long skillId) {
        return ResponseEntity.ok(userService.addSkillToUser(userId, skillId));
    }

    @DeleteMapping("/{userId}/skills/{skillId}")
    public ResponseEntity<User> removeSkillFromUser(
            @PathVariable Long userId,
            @PathVariable Long skillId) {
        return ResponseEntity.ok(userService.removeSkillFromUser(userId, skillId));
    }

    @GetMapping("/{userId}/projects")
    public ResponseEntity<List<Project>> getUserProjects(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getUserProjects(userId));
    }

    @GetMapping("/{userId}/skills")
    public ResponseEntity<List<Skill>> getUserSkills(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getUserSkills(userId));
    }
}