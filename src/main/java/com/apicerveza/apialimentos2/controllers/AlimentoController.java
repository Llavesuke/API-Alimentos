package com.apicerveza.apialimentos2.controllers;

import com.apicerveza.apialimentos2.entities.Alimento;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.apicerveza.apialimentos2.services.AlimentoService;

import java.util.List;
import java.time.LocalDate;


@RestController
@RequestMapping("/api/alimentos")
@RequiredArgsConstructor
public class AlimentoController {

    private final AlimentoService alimentoService;

    @GetMapping
    public List<Alimento> listarTodos() {
        return alimentoService.listarTodos();
    }

    @PostMapping
    public Alimento guardar(@RequestBody Alimento alimento) {
        return alimentoService.guardar(alimento);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Alimento> buscarPorId(@PathVariable Long id) {
        Alimento alimento = alimentoService.buscarPorId(id);
        return (alimento != null) ? ResponseEntity.ok(alimento) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        alimentoService.eliminar(id);
    }

    @PostMapping("/{id}/mover")
    public ResponseEntity<String> moverAlimento(@PathVariable Long id, @RequestParam String nuevaUbicacion) {
        String mensaje = alimentoService.moverAlimento(id, nuevaUbicacion);
        return ResponseEntity.ok(mensaje);
    }

    @GetMapping("/proximos-caducar")
    public List<Alimento> proximosACaducar() {
        return alimentoService.alimentosProximosACaducar();
    }

    @GetMapping("/tipo/{tipo}")
    public List<Alimento> buscarPorTipo(@PathVariable String tipo) {
        return alimentoService.buscarPorTipo(tipo);
    }

    @GetMapping("/rango-caducidad")
    public List<Alimento> buscarPorRangoDeFechas(@RequestParam LocalDate inicio, @RequestParam LocalDate fin) {
        return alimentoService.buscarPorRangoDeFechas(inicio, fin);
    }

    @GetMapping("/abiertos")
    public List<Alimento> buscarAbiertos() {
        return alimentoService.buscarAbiertos();
    }
}
