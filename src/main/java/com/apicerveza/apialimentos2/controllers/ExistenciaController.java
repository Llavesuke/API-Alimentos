package com.apicerveza.apialimentos2.controllers;

import com.apicerveza.apialimentos2.entities.Existencias;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.apicerveza.apialimentos2.services.ExistenciaService;

import java.util.List;

@RestController
@RequestMapping("/api/existencias")
@RequiredArgsConstructor
public class ExistenciaController {

    @Autowired
    private ExistenciaService existenciaService;

    @GetMapping
    public List<Existencias> listarTodos() {
        return existenciaService.listarTodos();
    }

    @PostMapping
    public Existencias guardar(@RequestBody Existencias existencia) {
        return existenciaService.guardar(existencia);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Existencias> buscarPorId(@PathVariable Long id) {
        Existencias existencia = existenciaService.buscarPorId(id);
        return (existencia != null) ? ResponseEntity.ok(existencia) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        existenciaService.eliminar(id);
    }

    @GetMapping("/rotacion-fifo")
    public List<Existencias> aplicarRotacionFIFO() {
        return existenciaService.aplicarRotacionFIFO();
    }
}