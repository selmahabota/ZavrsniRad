package model;

import lombok.*;
@Getter
@AllArgsConstructor
@Builder
public class LoginUser {
    private String email;
    private String password;
}
