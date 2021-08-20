package com.alcalazans.tribo.service;

import com.alcalazans.tribo.model.Usuario;
import com.alcalazans.tribo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario findById(Long id){
        return usuarioRepository.findById(id).orElse(new Usuario());
    }


}
