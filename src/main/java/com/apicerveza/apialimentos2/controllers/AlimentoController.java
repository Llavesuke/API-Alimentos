package com.apicerveza.apialimentos2.controllers;

import com.apicerveza.apialimentos2.entities.Alimentos;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.apicerveza.apialimentos2.services.AlimentoService;

import java.util.List;
import java.time.LocalDate;

@RestController
@RequestMapping("/api/alimentos")
@RequiredArgsConstructor
public class AlimentoController {

    @Autowired
    private AlimentoService alimentoService;

    @GetMapping
    public List<Alimentos> listarTodos() {
        return alimentoService.listarTodos();
    }

    @PostMapping
    public ResponseEntity<Alimentos> guardar(@RequestBody Alimentos alimento) {
        Alimentos savedAlimento = alimentoService.guardar(alimento);
        return new ResponseEntity<>(savedAlimento, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Alimentos> buscarPorId(@PathVariable Long id) {
        Alimentos alimento = alimentoService.buscarPorId(id);
        return (alimento != null) ? ResponseEntity.ok(alimento) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Alimentos> actualizar(@PathVariable Long id, @RequestBody Alimentos alimento) {
        Alimentos updatedAlimento = alimentoService.actualizar(id, alimento);
        return (updatedAlimento != null) ? ResponseEntity.ok(updatedAlimento) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        alimentoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/mover")
    public ResponseEntity<String> moverAlimento(@PathVariable Long id, @RequestParam String nuevaUbicacion) {
        String mensaje = alimentoService.moverAlimento(id, nuevaUbicacion);
        return ResponseEntity.ok(mensaje);
    }

    @GetMapping("/proximos-caducar")
    public List<Alimentos> proximosACaducar() {
        return alimentoService.alimentosProximosACaducar();
    }

    @GetMapping("/tipo/{tipo}")
    public List<Alimentos> buscarPorTipo(@PathVariable String tipo) {
        return alimentoService.buscarPorTipo(tipo);
    }

    @GetMapping("/rango-caducidad")
    public List<Alimentos> buscarPorRangoDeFechas(@RequestParam LocalDate inicio, @RequestParam LocalDate fin) {
        return alimentoService.buscarPorRangoDeFechas(inicio, fin);
    }

    @GetMapping("/abiertos")
    public List<Alimentos> buscarAbiertos() {
        return alimentoService.buscarAbiertos();
    }
}