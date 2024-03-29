package com.tribo.controller.autenticacao;

import com.tribo.config.security.token.TokenService;
import com.tribo.controller.dto.request.LoginForm;
import com.tribo.controller.dto.response.TokenDTO;
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
	public ResponseEntity<TokenDTO> autenticar(@RequestBody @Validated LoginForm request) {
		UsernamePasswordAuthenticationToken dadosLogin = request.converter();
		
		try {
			var authentication = authManager.authenticate(dadosLogin);
			String token = tokenService.generateToken(authentication);
			return ResponseEntity.ok(new TokenDTO(token, "Bearer"));
		} catch (AuthenticationException e) {
			return ResponseEntity.unprocessableEntity().build();
		}
	}
	
}
