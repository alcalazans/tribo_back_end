package com.tribo.aspect.usuario;

import com.tribo.aspect.AbstractAcessoValidator;
import com.tribo.config.security.SegurancaSupport;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class VisualizaApenasSeusDadosAspect extends AbstractAcessoValidator {

    @Autowired
    private SegurancaSupport seguranca;

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
