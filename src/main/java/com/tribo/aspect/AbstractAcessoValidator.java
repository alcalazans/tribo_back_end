package com.tribo.aspect;

import com.tribo.config.security.SegurancaSupport;
import io.jsonwebtoken.lang.Collections;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public abstract class AbstractAcessoValidator {

    @Autowired
    protected SegurancaSupport seguranca;

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public Object validaAcesso(ProceedingJoinPoint joinPoint) throws Throwable {

        Object[] args = joinPoint.getArgs();
        if (args.length == 1 && args[0] instanceof List) {
            List arg = (List) args[0];
            if (Collections.isEmpty(arg)) {
               joinPoint.proceed();
            }
            arg.stream().forEach(i -> validaAcesso(new Object[] { i }));
            return joinPoint.proceed();
        }

        validaAcesso(args);
        return joinPoint.proceed();
    }

    protected void validaAcesso(Object[] args) {
        if (!isAcessoPermitido(getId(args))) {
            throw new AccessDeniedException("Acesso negado!");
        }
    }

    protected Object getId(Object[] args) {
        try {
            if (args.length == 1 && (args[0].getClass().equals(Long.class) || args[0].getClass().equals(String.class))) {
                return args[0];
            }
            for (Object object : args) {
                Method[] methods = object.getClass().getMethods();
                for (Method method : methods) {
                    if (method.getName().equals(getNomeMetodoDoId())) {
                        return method.invoke(object);
                    }
                }
            }
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            //
        }
        if (getNomeMetodoDoId() == null) {
            return null;
        }
        throw new AccessDeniedException("Acesso negado!");
    }

    public abstract String getNomeMetodoDoId();

    public abstract boolean isAcessoPermitido(Object id);

}
