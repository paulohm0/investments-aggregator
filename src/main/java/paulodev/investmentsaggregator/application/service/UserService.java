package paulodev.investmentsaggregator.application.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import paulodev.investmentsaggregator.domain.model.dto.AccountResponseDTO;
import paulodev.investmentsaggregator.domain.model.dto.CreateAccountDTO;
import paulodev.investmentsaggregator.domain.model.dto.CreateUserDTO;
import paulodev.investmentsaggregator.domain.model.dto.UpdateUserDTO;
import paulodev.investmentsaggregator.domain.model.entity.Account;
import paulodev.investmentsaggregator.domain.model.entity.BillingAddress;
import paulodev.investmentsaggregator.domain.model.entity.User;
import paulodev.investmentsaggregator.domain.repository.AccountRepository;
import paulodev.investmentsaggregator.domain.repository.BillingAddressRepository;
import paulodev.investmentsaggregator.domain.repository.UserRepository;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private UserRepository userRepository;
    private AccountRepository accountRepository;
    private BillingAddressRepository billingAddressRepository;

    public UserService(UserRepository userRepository, AccountRepository accountRepository, BillingAddressRepository billingAddressRepository) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
        this.billingAddressRepository = billingAddressRepository;
    }

    public UUID createUser(CreateUserDTO createUserDto) {

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

    public void updateUserById(String uuid, UpdateUserDTO updateUserDto) {
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

    @Transactional
    public void createAccount(String userId, CreateAccountDTO createAccountDTO) {
        var user = userRepository.findById(UUID.fromString(userId))
                .orElseThrow(() -> new RuntimeException("User not found"));

        var account = new Account(
                null,
                user,
                null,
                createAccountDTO.description(),
                new ArrayList<>());

        var accountCreated = accountRepository.save(account);
        var billingAddress = new BillingAddress(
                accountCreated.getAccountId(),
                createAccountDTO.street(),
                createAccountDTO.number(),
                accountCreated);

        billingAddressRepository.save(billingAddress);
    }

    public List<Account> getAccountsListByUser(String userId) {
        var user = userRepository.findById(UUID.fromString(userId))
                .orElseThrow(() -> new RuntimeException("User not found"));
        return user.getAccountList()
                .stream()
                .map(account -> new Account(account.getAccountId(),account.getUser(), account.getBillingAddress(), account.getDescription(), account.getAccountStockList())).toList();
    }
}
