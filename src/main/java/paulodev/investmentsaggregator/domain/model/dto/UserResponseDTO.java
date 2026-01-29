package paulodev.investmentsaggregator.domain.model.dto;

import paulodev.investmentsaggregator.domain.model.entity.User;

import java.util.UUID;

public record UserResponseDTO(UUID id, String name, String email) {

    public UserResponseDTO(User user) {
        this(user.getUserID(), user.getUsername(), user.getEmail());
    }
}
