package com.apicerveza.apialimentos2.controllers;

import com.apicerveza.apialimentos2.dto.CrearUbicacionDTO;
import com.apicerveza.apialimentos2.dto.ModificarUbicacionDTO;
import com.apicerveza.apialimentos2.dto.UbicacionesDTO;
import com.apicerveza.apialimentos2.services.UbicacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ubicaciones")
@RequiredArgsConstructor
public class UbicacionController {

    @Autowired
    private UbicacionService ubicacionService;

    @GetMapping
    public List<UbicacionesDTO> listarTodos() {
        return ubicacionService.listarTodos();
    }

    @PostMapping
    public ResponseEntity<UbicacionesDTO> guardar(@RequestBody CrearUbicacionDTO crearUbicacionDTO) {
        UbicacionesDTO savedUbicacion = ubicacionService.guardar(crearUbicacionDTO);
        return new ResponseEntity<>(savedUbicacion, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UbicacionesDTO> buscarPorId(@PathVariable Long id) {
        UbicacionesDTO ubicacion = ubicacionService.buscarPorId(id);
        return (ubicacion != null) ? ResponseEntity.ok(ubicacion) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<UbicacionesDTO> actualizar(@PathVariable Long id, @RequestBody ModificarUbicacionDTO modificarUbicacionDTO) {
        UbicacionesDTO updatedUbicacion = ubicacionService.actualizar(id, modificarUbicacionDTO);
        return (updatedUbicacion != null) ? ResponseEntity.ok(updatedUbicacion) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        ubicacionService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}