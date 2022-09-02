package com.stackroute.authenticationService.Model;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class User {

    String userId;
        String userName;
    String password;
    String email;
    String address;
    String phone;
    String type;

}
