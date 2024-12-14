package com.apicerveza.apialimentos2.controllers;

import com.apicerveza.apialimentos2.entities.Existencia;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.apicerveza.apialimentos2.services.ExistenciaService;


import java.util.List;

@RestController
@RequestMapping("/api/existencias")
@RequiredArgsConstructor
public class ExistenciaController {

    private final ExistenciaService existenciaService;

    @GetMapping
    public List<Existencia> listarTodos() {
        return existenciaService.listarTodos();
    }

    @PostMapping
    public Existencia guardar(@RequestBody Existencia existencia) {
        return existenciaService.guardar(existencia);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Existencia> buscarPorId(@PathVariable Long id) {
        Existencia existencia = existenciaService.buscarPorId(id);
        return (existencia != null) ? ResponseEntity.ok(existencia) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        existenciaService.eliminar(id);
    }
}

