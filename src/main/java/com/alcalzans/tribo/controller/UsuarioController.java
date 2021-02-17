package com.alcalzans.tribo.controller;

import com.alcalzans.tribo.controller.dto.ConsultaUsuarioDTO;
import com.alcalzans.tribo.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/{id}")
    public ResponseEntity<ConsultaUsuarioDTO> detalhar(@PathVariable Long id){
        ConsultaUsuarioDTO retornoDto = new ConsultaUsuarioDTO(usuarioService.findById(id));

        if(retornoDto!=null)
            return ResponseEntity.ok(retornoDto);
        else
            return ResponseEntity.notFound().build();
    }


}
