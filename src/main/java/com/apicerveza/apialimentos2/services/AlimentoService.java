package com.apicerveza.apialimentos2.services;

import com.apicerveza.apialimentos2.entities.Alimentos;
import com.apicerveza.apialimentos2.repositories.AlimentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AlimentoService {

    @Autowired
    private AlimentoRepository alimentoRepository;

    public List<Alimentos> listarTodos() {
        return alimentoRepository.findAll();
    }

    public Alimentos guardar(Alimentos alimento) {
        return alimentoRepository.save(alimento);
    }

    public void eliminar(Long id) {
        alimentoRepository.deleteById(id);
    }

    public Alimentos buscarPorId(Long id) {
        return alimentoRepository.findById(id).orElse(null);
    }

    @Transactional
    public String moverAlimento(Long alimentoId, String nuevaUbicacion) {
        Alimentos alimento = buscarPorId(alimentoId);
        if (alimento == null) {
            return "El alimento no existe";
        }
        // Lógica para mover el alimento (ejemplo simplificado)
        alimento.setEstado("Movido a " + nuevaUbicacion);
        alimentoRepository.save(alimento);
        return "Alimento movido exitosamente";
    }

    public Alimentos actualizar(Long id, Alimentos alimento) {
        Alimentos existingAlimento = buscarPorId(id);
        if (existingAlimento == null) {
            throw new IllegalArgumentException("Alimento no encontrado");
        }
        existingAlimento.setNombre(alimento.getNombre());
        existingAlimento.setTipo(alimento.getTipo());
        existingAlimento.setEstado(alimento.getEstado());
        existingAlimento.setFechaCaducidad(alimento.getFechaCaducidad());
        return alimentoRepository.save(existingAlimento);
    }

    public List<Alimentos> alimentosProximosACaducar() {
        LocalDate ahora = LocalDate.now();
        return alimentoRepository.findAll().stream()
                .filter(a -> a.getFechaCaducidad() != null && a.getFechaCaducidad().isBefore(ahora.plusDays(7)))
                .collect(Collectors.toList());
    }

    public List<Alimentos> buscarPorTipo(String tipo) {
        return alimentoRepository.findByTipo(tipo);
    }

    public List<Alimentos> buscarPorRangoDeFechas(LocalDate inicio, LocalDate fin) {
        return alimentoRepository.findAlimentosPorRangoDeFechas(inicio, fin);
    }

    public List<Alimentos> buscarAbiertos() {
        return alimentoRepository.findAlimentosAbiertos();
    }

}