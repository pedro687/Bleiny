package com.bleiny.communities.application.ports;

import com.bleiny.communities.adapters.inbound.dtos.ResponseTagServerDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FilterServersByParametersPort {
     List<ResponseTagServerDTO> filterByParameters(Long id, Pageable pageable);

}
