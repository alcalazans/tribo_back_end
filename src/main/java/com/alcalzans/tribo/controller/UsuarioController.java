package com.alcalzans.tribo.controller;

import com.alcalzans.tribo.controller.dto.UsuarioDTO;
import com.alcalzans.tribo.service.UsuarioService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @ApiOperation(value = "Localizar usuario por id")
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> detalhar(@PathVariable Long id){
        UsuarioDTO retornoDto = new UsuarioDTO(usuarioService.findById(id));

        if(retornoDto!=null)
            return ResponseEntity.ok(retornoDto);
        else
            return ResponseEntity.notFound().build();
    }


}
