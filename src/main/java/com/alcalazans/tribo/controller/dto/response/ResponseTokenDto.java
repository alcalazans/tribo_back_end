package com.alcalazans.tribo.controller.dto.response;

public class ResponseTokenDto {

	private String token;
	private String tipo;

	public ResponseTokenDto(String token, String tipo) {
		this.token = token;
		this.tipo = tipo;
	}

	public String getToken() {
		return token;
	}

	public String getTipo() {
		return tipo;
	}

}
