package com.spiet.bleiny.api.users.dto;

import com.spiet.bleiny.api.users.dto.AddressDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
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
