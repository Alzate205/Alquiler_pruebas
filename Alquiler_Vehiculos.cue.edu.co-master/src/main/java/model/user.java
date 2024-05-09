package model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder

public class user {
    private int id;
    private String name;
    private String lastName;
    private int phoneNumber;
    private String password;

}
