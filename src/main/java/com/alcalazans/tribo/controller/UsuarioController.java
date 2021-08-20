package com.alcalazans.tribo.controller;

import com.alcalazans.tribo.controller.dto.request.UsuarioRequestDto;
import com.alcalazans.tribo.controller.dto.response.UsuarioResponseDto;
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
    public UsuarioResponseDto detalhar(UsuarioRequestDto dto) throws Exception {
        return new UsuarioResponseDto(usuarioService.findById(dto.getId()));
    }


}
