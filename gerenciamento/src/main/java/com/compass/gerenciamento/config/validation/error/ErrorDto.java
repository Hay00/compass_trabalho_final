package com.compass.gerenciamento.config.validation.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class ErrorDto {

    @Getter
    private String campo;

    @Getter
    private String erro;
}
