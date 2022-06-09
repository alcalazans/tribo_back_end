package com.alcalazans.tribo.controller;

import com.alcalazans.tribo.controller.dto.request.UsuarioRequestDTO;
import com.alcalazans.tribo.controller.dto.response.UsuarioResponseDTO;
import com.alcalazans.tribo.service.UsuarioService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @ApiOperation(value = "Localizar usuario por id")
    @GetMapping("/{id}")

    public UsuarioResponseDTO detalhar(UsuarioRequestDTO dto) {
        return new UsuarioResponseDTO(usuarioService.findById(dto.getId()));
    }


}
