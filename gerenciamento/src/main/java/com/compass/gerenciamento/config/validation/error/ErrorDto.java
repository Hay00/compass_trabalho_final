package com.compass.gerenciamento.config.validation.error;

import lombok.Getter;


public class ErrorDto {

    @Getter
    private String campo;

    @Getter
    private String erro;

	public ErrorDto(String campo, String erro) {
		this.campo = campo;
		this.erro = erro;
	}
    
    
}
