package com.spiet.bleiny.api.users.services;

import com.spiet.bleiny.api.users.http.dto.AddressDTO;
import com.spiet.bleiny.shared.domain.User;

public interface IAddressService {
    void create(AddressDTO addressDTO, User user);
}
