package com.apicerveza.apialimentos2.controllers;

import com.apicerveza.apialimentos2.entities.Ubicacion;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.apicerveza.apialimentos2.services.UbicacionService;

import java.util.List;

@RestController
@RequestMapping("/api/ubicaciones")
@RequiredArgsConstructor
public class UbicacionController {

    private final UbicacionService ubicacionService;

    @GetMapping
    public List<Ubicacion> listarTodos() {
        return ubicacionService.listarTodos();
    }

    @PostMapping
    public Ubicacion guardar(@RequestBody Ubicacion ubicacion) {
        return ubicacionService.guardar(ubicacion);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ubicacion> buscarPorId(@PathVariable Long id) {
        Ubicacion ubicacion = ubicacionService.buscarPorId(id);
        return (ubicacion != null) ? ResponseEntity.ok(ubicacion) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        ubicacionService.eliminar(id);
    }
}
