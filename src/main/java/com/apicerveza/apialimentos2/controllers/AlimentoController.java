package com.apicerveza.apialimentos2.controllers;

import com.apicerveza.apialimentos2.dto.AlimentosDTO;
import com.apicerveza.apialimentos2.dto.CrearAlimentoDTO;
import com.apicerveza.apialimentos2.dto.ModificarAlimentoDTO;
import com.apicerveza.apialimentos2.services.AlimentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/alimentos")
@RequiredArgsConstructor
public class AlimentoController {

    @Autowired
    private final AlimentoService alimentoService;

    @GetMapping
    public ResponseEntity<Page<AlimentosDTO>> listarAlimentos(@RequestParam(required = false) String nombre, Pageable pageable) {
        Page<AlimentosDTO> alimentos = alimentoService.listarAlimentos(nombre, pageable);
        return ResponseEntity.ok(alimentos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlimentosDTO> obtenerAlimento(@PathVariable Long id) {
        AlimentosDTO alimento = alimentoService.obtenerAlimento(id);
        return ResponseEntity.ok(alimento);
    }

    @PostMapping
    public ResponseEntity<AlimentosDTO> crearAlimento(@RequestBody CrearAlimentoDTO crearAlimentoDTO) {
        AlimentosDTO nuevoAlimento = alimentoService.crearAlimento(crearAlimentoDTO);
        return new ResponseEntity<>(nuevoAlimento, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlimentosDTO> actualizarAlimento(@PathVariable Long id, @RequestBody ModificarAlimentoDTO modificarAlimentoDTO) {
        AlimentosDTO alimentoActualizado = alimentoService.actualizarAlimento(id, modificarAlimentoDTO);
        return ResponseEntity.ok(alimentoActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarAlimento(@PathVariable Long id) {
        alimentoService.eliminarAlimento(id);
        return ResponseEntity.noContent().build();
    }
}