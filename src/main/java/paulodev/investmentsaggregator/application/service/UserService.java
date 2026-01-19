package paulodev.investmentsaggregator.application.service;

import org.springframework.stereotype.Service;
import paulodev.investmentsaggregator.application.dto.CreateUserDto;
import paulodev.investmentsaggregator.application.dto.UpdateUserDto;
import paulodev.investmentsaggregator.domain.model.entity.User;
import paulodev.investmentsaggregator.domain.repository.UserRepository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private UserRepository userRepository;

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

    public void updateUserById(String uuid, UpdateUserDto updateUserDto) {
        var id = UUID.fromString(uuid);
        var userEntity = userRepository.findById(id);
        if(userEntity.isPresent()) {
            var user = userEntity.get();
            if(updateUserDto.username() != null) {
                user.setUsername(updateUserDto.username());
            }
            if(updateUserDto.password() != null) {
                user.setPassword(updateUserDto.password());
            }
            if(updateUserDto.email() != null) {
                user.setEmail(updateUserDto.email());
            }
            userRepository.save(user);
        }
    }

    public void deleteById(String uuid) {
        var id = UUID.fromString(uuid);
        var userId = userRepository.existsById(id);
        if (userId) {
           userRepository.deleteById(id);
        }
    }




}
