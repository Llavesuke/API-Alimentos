package com.apicerveza.apialimentos2.controllers;

import com.apicerveza.apialimentos2.entities.Ubicaciones;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.apicerveza.apialimentos2.services.UbicacionService;

import java.util.List;

@RestController
@RequestMapping("/api/ubicaciones")
@RequiredArgsConstructor
public class UbicacionController {

    @Autowired
    private UbicacionService ubicacionService;

    @GetMapping
    public List<Ubicaciones> listarTodos() {
        return ubicacionService.listarTodos();
    }

    @PostMapping
    public Ubicaciones guardar(@RequestBody Ubicaciones ubicacion) {
        return ubicacionService.guardar(ubicacion);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ubicaciones> buscarPorId(@PathVariable Long id) {
        Ubicaciones ubicacion = ubicacionService.buscarPorId(id);
        return (ubicacion != null) ? ResponseEntity.ok(ubicacion) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        ubicacionService.eliminar(id);
    }
}
