package com.spiet.bleiny.api.users.dto;

import com.spiet.bleiny.api.users.dto.AddressDTO;
import lombok.*;

import javax.validation.constraints.NotNull;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class UserDTO {

    @NotNull
    private String username;

    @NotNull
    private String email;

    @NotNull
    private String password;

    private String tellphone;

    @NotNull
    private AddressDTO address;
}
