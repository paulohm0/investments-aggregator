package paulodev.investmentsaggregator.infra.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import paulodev.investmentsaggregator.domain.model.dto.*;
import paulodev.investmentsaggregator.domain.model.entity.User;
import paulodev.investmentsaggregator.application.service.UserService;

import java.net.URI;
import java.util.List;

// @RestController -> informa ao spring que essa classe possue endpoints da api
// @RequestMapping -> define um caminho base para todos os endpoints da api
// @PostMapping -> informa que o métodoo sera um endpoint HTTP post
// @GetMapping -> informa que o métodoo sera um endpoint HTTP get

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;
    public UserController(UserService userService) {
        this.userService =  userService;
    }

    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody CreateUserDTO createUserDto) {
        var userId = userService.createUser(createUserDto);
        return ResponseEntity.created(URI.create("/users/" + userId.toString())).build();
    }

    @GetMapping("/list")
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        var usersList = userService.getUsersList();
        var usersDtoList = usersList.stream()
                .map(user -> new UserResponseDTO(user))
                .toList();
        return ResponseEntity.ok(usersDtoList);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable("userId") String userId) {
        var userById = userService.getUserById(userId);
        if (userById.isPresent()) {
            var userEntity = userById.get();
            var userDTO =  new UserResponseDTO(userEntity);
            return ResponseEntity.ok(userDTO);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Void> updateUserById(@PathVariable("userId") String uuid, @RequestBody UpdateUserDTO updateUserDto) {
        userService.updateUserById(uuid, updateUserDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteById(@PathVariable("userId") String userId) {
        userService.deleteById(userId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{userId}/accounts")
    public ResponseEntity<Void> createAccount(@PathVariable("userId") String userId, @RequestBody CreateAccountDTO createAccountDTO) {
        userService.createAccount(userId, createAccountDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{userId}/accounts")
    public ResponseEntity<List<AccountResponseDTO>> getAccountsListByUser(@PathVariable("userId") String userId) {
        var accounts = userService.getAccountsListByUser(userId);
        var accountsDTO = accounts.stream().map(account -> new AccountResponseDTO(account)).toList();
        return ResponseEntity.ok(accountsDTO);
    }

}
