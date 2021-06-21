package com.spiet.bleiny.api.users.repositories;

import com.spiet.bleiny.shared.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
}
