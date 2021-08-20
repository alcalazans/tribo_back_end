package com.alcalazans.tribo.controller;

import com.alcalazans.tribo.config.security.token.TokenService;
import com.alcalazans.tribo.controller.dto.request.LoginRequestDto;
import com.alcalazans.tribo.controller.dto.response.TokenResponseDto;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AutenticacaoController {

	@Autowired
	private AuthenticationManager authManager;

	@Autowired
	private TokenService tokenService;

	@ApiOperation(value = "Efetuar Login")
	@PostMapping
	public ResponseEntity<TokenResponseDto> autenticar(@RequestBody @Validated LoginRequestDto request) {
		UsernamePasswordAuthenticationToken dadosLogin = request.converter();
		
		try {
			var authentication = authManager.authenticate(dadosLogin);
			String token = tokenService.gerarToken(authentication);
			return ResponseEntity.ok(new TokenResponseDto(token, "Bearer"));
		} catch (AuthenticationException e) {
			return ResponseEntity.unprocessableEntity().build();
		}
	}
	
}
