package com.tribo.config.security;

import com.tribo.model.Usuario;
import lombok.AccessLevel;
import lombok.Setter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope
@Setter(value = AccessLevel.PRIVATE)
public class SegurancaSupport {

    SegurancaSupport() {
    }

    public Usuario getUsuarioAutenticado(){
        return (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    //TODO "USUARIO_ADMIN" em variável.
    public boolean isAdministrador(){
        return SecurityContextHolder.getContext().getAuthentication()
                .getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("USUARIO_ADMIN"));
    }
}
