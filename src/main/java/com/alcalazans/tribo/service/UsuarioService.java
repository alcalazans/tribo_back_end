package com.alcalazans.tribo.service;

import com.alcalazans.tribo.model.Usuario;
import com.alcalazans.tribo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario findById(Long id){
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if (usuario.isPresent()) {
            return usuario.get();
        }
        return null;
    }


}
