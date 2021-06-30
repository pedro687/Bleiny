package com.spiet.bleiny.shared.infra.utils.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FormErrorDto {
    private String field;
    private String message;
}
