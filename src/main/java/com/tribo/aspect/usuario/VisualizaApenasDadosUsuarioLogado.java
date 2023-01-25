package com.tribo.aspect.usuario;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * Restringe o acesso aos dados para o id do usuário logado
 *
 * @see VisualizaApenasSeusDadosAspect
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface VisualizaApenasDadosUsuarioLogado {

}
