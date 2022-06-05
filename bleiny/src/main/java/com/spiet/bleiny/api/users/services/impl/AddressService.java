package com.spiet.bleiny.api.users.services.impl;

import com.spiet.bleiny.api.users.dto.AddressDTO;
import com.spiet.bleiny.api.users.repositories.AddressRepository;
import com.spiet.bleiny.api.users.services.IAddressService;
import com.spiet.bleiny.api.users.utils.AddressConverter;
import com.spiet.bleiny.shared.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService implements IAddressService {

    private AddressRepository addressRepository;

    private AddressConverter addressConverter;

    @Autowired
    public AddressService(AddressRepository addressRepository, AddressConverter addressConverter) {
        this.addressRepository = addressRepository;
        this.addressConverter = addressConverter;
    }

    @Override
    public void create(AddressDTO addressDTO, User user) {
        var converted = addressConverter.toAddress(addressDTO, user);
        addressRepository.save(converted);
    }

}
