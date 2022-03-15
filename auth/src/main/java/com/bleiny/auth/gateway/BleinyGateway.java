package com.bleiny.auth.gateway;

import com.bleiny.auth.entities.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(name = "bleiny-core", path = "/bleiny-core/user")
public interface BleinyGateway {

    @GetMapping(value = "/findByEmail/{email}")
    public ResponseEntity<User> findByEmail(@PathVariable String email, @RequestHeader("Content-Type") MediaType mediaType);

}
