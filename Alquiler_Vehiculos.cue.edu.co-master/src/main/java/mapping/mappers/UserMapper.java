package mapping.mappers;

import mapping.dtos.userDTO;
import model.user;

public class UserMapper {
    public static userDTO mapFromModel(user user){
        return new userDTO( user.getName(), user.getLastName(), user.getPhoneNumber(), user.getPassword());

    }
    public static user mapFromDTO(userDTO userDTO){
        return user.builder()
                .name(userDTO.name())
                .lastName(userDTO.lastName())
                .phoneNumber(userDTO.phoneNumber())
                .password(userDTO.password())
                .build();
    }
}
