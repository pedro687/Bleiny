package com.bleiny.auth.config.security;

import com.bleiny.auth.entities.User;
import com.bleiny.auth.gateway.BleinyGateway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AppUserDetailsService implements UserDetailsService {

    @Autowired
    private BleinyGateway bleinyGateway;

    public User findByEmail(String email) {
        var user = bleinyGateway.findByEmail(email, MediaType.APPLICATION_JSON).getBody();
        if (user == null) {
            throw new IllegalArgumentException("Email not found");
        }
        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = bleinyGateway.findByEmail(username, MediaType.APPLICATION_JSON).getBody();
        if (user == null) {
            throw new UsernameNotFoundException("Email not found");
        }
        return user;
    }
}
