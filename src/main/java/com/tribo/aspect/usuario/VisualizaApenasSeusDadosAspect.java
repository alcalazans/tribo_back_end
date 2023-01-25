package com.tribo.aspect.usuario;

import com.tribo.aspect.AbstractAcessoValidator;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class VisualizaApenasSeusDadosAspect extends AbstractAcessoValidator {

    @Around("@annotation(com.tribo.aspect.usuario.VisualizaApenasDadosUsuarioLogado)")
    public Object validaAcessoUsuario(ProceedingJoinPoint joinPoint) throws Throwable {
        return super.validaAcesso(joinPoint);
    }

    @Override
    public String getNomeMetodoDoId() {
        return "getId";
    }

    @Override
    public boolean isAcessoPermitido(Object id) {
        return seguranca.getUsuarioAutenticado().getId().equals(id) ||
                seguranca.isAdministrador();
    }
}
