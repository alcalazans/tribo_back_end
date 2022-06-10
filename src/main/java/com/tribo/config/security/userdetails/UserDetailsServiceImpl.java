package com.tribo.config.security.userdetails;

import com.tribo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UsuarioRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) {
		return new MyUserDetails(repository.findByEmail(username)
				.orElseThrow(()->new UsernameNotFoundException("Usuário não encontrado!")));
	}
}
