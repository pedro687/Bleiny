package com.spiet.bleiny.api.users.utils;

import com.spiet.bleiny.api.users.dto.AddressDTO;
import com.spiet.bleiny.shared.domain.Address;
import com.spiet.bleiny.shared.domain.User;
import org.springframework.stereotype.Component;

@Component
public class AddressConverter {

    public AddressDTO toDto(Address address) {
        AddressDTO addressDTO = new AddressDTO();

        addressDTO.setCity(address.getCity());
        addressDTO.setCountry(address.getCountry());
        addressDTO.setUf(address.getUf());

        return addressDTO;
    }

    public Address toAddress(AddressDTO addressDTO, User user) {
        Address address = new Address();
        address.setCity(addressDTO.getCity());
        address.setUf(addressDTO.getUf());
        address.setUser(user);
        address.setCountry(addressDTO.getCountry());

        return address;
    }
}
