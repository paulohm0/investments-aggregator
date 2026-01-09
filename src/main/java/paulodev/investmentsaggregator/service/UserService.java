package paulodev.investmentsaggregator.service;

import org.springframework.stereotype.Service;
import paulodev.investmentsaggregator.controller.CreateUserDto;
import paulodev.investmentsaggregator.entity.User;
import paulodev.investmentsaggregator.repository.UserRepository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UUID createUser(CreateUserDto createUserDto) {

        // DTO -> ENTITY
        var entity = new User(
                null,
                createUserDto.username(),
                createUserDto.email(),
                createUserDto.password(),
                Instant.now(),
                null
        );
        var userSaved = userRepository.save(entity);
        return userSaved.getUserID();
    }

    public List<User> getUsersList() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(String uuid) {
        return userRepository.findById(UUID.fromString(uuid));
    }


}
