package com.apicerveza.apialimentos2.controllers;

import com.apicerveza.apialimentos2.dto.ExistenciasDTO;
import com.apicerveza.apialimentos2.dto.CrearExistenciaDTO;
import com.apicerveza.apialimentos2.dto.ModificarExistenciaDTO;
import com.apicerveza.apialimentos2.services.ExistenciaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/existencias")
@RequiredArgsConstructor
public class ExistenciaController {

    @Autowired
    private ExistenciaService existenciaService;

    @GetMapping
    public List<ExistenciasDTO> listarTodos() {
        return existenciaService.listarTodos();
    }

    @PostMapping
    public ResponseEntity<ExistenciasDTO> guardar(@RequestBody CrearExistenciaDTO crearExistenciaDTO) {
        ExistenciasDTO savedExistencia = existenciaService.guardar(crearExistenciaDTO);
        return new ResponseEntity<>(savedExistencia, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExistenciasDTO> buscarPorId(@PathVariable Long id) {
        ExistenciasDTO existencia = existenciaService.buscarPorId(id);
        return (existencia != null) ? ResponseEntity.ok(existencia) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExistenciasDTO> actualizar(@PathVariable Long id, @RequestBody ModificarExistenciaDTO modificarExistenciaDTO) {
        ExistenciasDTO updatedExistencia = existenciaService.actualizar(id, modificarExistenciaDTO);
        return (updatedExistencia != null) ? ResponseEntity.ok(updatedExistencia) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        existenciaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}