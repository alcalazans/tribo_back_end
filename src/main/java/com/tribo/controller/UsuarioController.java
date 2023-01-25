package com.tribo.controller;

import com.tribo.aspect.usuario.VisualizaApenasDadosUsuarioLogado;
import com.tribo.config.security.SegurancaSupport;
import com.tribo.controller.dto.request.UsuarioForm;
import com.tribo.controller.dto.response.UsuarioDTO;
import com.tribo.service.UsuarioService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    @Autowired
    protected SegurancaSupport segurancaSupport;

    @Autowired
    private UsuarioService usuarioService;

    @ApiOperation(value = "Localizar usuario por id")
    @GetMapping("/{id}")
    @VisualizaApenasDadosUsuarioLogado
    public UsuarioDTO getUsuario(UsuarioForm usuarioForm) {
        return new UsuarioDTO(usuarioService.findById(usuarioForm.getId()));
    }


}
