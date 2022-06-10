package com.tribo.config.security;

import com.tribo.model.Usuario;
import org.springframework.security.core.context.SecurityContextHolder;

public class SegurancaSuporte {

    private SegurancaSuporte() {
        throw new IllegalStateException();
    }

    public static Usuario getUsuarioAutenticado(){
        return (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    //TODO "USUARIO_ADMIN" em variável.
    public static boolean isAdministrador(){
        return SecurityContextHolder.getContext().getAuthentication()
                .getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("USUARIO_ADMIN"));
    }
}
