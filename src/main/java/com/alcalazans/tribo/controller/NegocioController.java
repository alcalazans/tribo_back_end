package com.alcalazans.tribo.controller;

import com.alcalazans.tribo.model.negociacoes.Negocio;
import com.alcalazans.tribo.service.NegociacaoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/negocios")
public class NegocioController {

    @Autowired
    private NegociacaoService negociacaoService;

    @ApiOperation(value = "Listar Negocios")
    @GetMapping("")
    public List<Negocio> listar() {
        return negociacaoService.getNegocios();
    }
}
