package paulodev.investmentsaggregator.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import paulodev.investmentsaggregator.entity.User;
import paulodev.investmentsaggregator.service.UserService;

import java.net.URI;
import java.util.List;
import java.util.UUID;

// @RestController -> informa ao spring que essa classe possue endpoints da api
// @RequestMapping -> define um caminho base para todos os endpoints da api
// @PostMapping -> informa que o método sera um endpoint HTTP post
// @GetMapping -> informa que o método sera um endpoint HTTP get

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService =  userService;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody CreateUserDto createUserDto) {
        var userId = userService.createUser(createUserDto);
        return ResponseEntity.created(URI.create("/users/" + userId.toString())).build();
    }

    @GetMapping("/list")
    public ResponseEntity<List<User>> getAllUsers() {
        var usersList = userService.getUsersList();
        return ResponseEntity.ok(usersList);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable("userId") String userId) {
        try {
            var userById = userService.getUserById(userId);
            if (userById.isPresent()) {
                return ResponseEntity.ok(userById.get());
            }
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
