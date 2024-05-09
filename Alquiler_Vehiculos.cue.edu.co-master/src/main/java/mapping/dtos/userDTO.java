package mapping.dtos;

import lombok.Builder;

@Builder
public record userDTO(String name, String lastName, int phoneNumber, String password) {
}
