package paulodev.investmentsaggregator.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import paulodev.investmentsaggregator.dto.CreateUserDto;
import paulodev.investmentsaggregator.dto.UpdateUserDto;
import paulodev.investmentsaggregator.entity.User;
import paulodev.investmentsaggregator.service.UserService;

import java.net.URI;
import java.util.List;

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

    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUserById(@PathVariable("userId") String uuid, @RequestBody UpdateUserDto updateUserDto) {
        userService.updateUserById(uuid, updateUserDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteById(@PathVariable("userId") String userId) {
        userService.deleteById(userId);
        return ResponseEntity.noContent().build();
    }
}
