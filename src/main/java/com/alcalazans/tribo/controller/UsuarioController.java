package com.alcalazans.tribo.controller;

import com.alcalazans.tribo.controller.dto.response.UsuarioDto;
import com.alcalazans.tribo.service.UsuarioService;
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
    public ResponseEntity<UsuarioDto> detalhar(@PathVariable Long id){
        
        UsuarioDto retornoDto = new UsuarioDto(usuarioService.findById(id));

        if(retornoDto!=null)
            return ResponseEntity.ok(retornoDto);
        else
            return ResponseEntity.notFound().build();
    }


}
