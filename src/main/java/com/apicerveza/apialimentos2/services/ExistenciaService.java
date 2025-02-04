package com.apicerveza.apialimentos2.services;

import com.apicerveza.apialimentos2.entities.Alimento;
import com.apicerveza.apialimentos2.entities.Existencia;
import com.apicerveza.apialimentos2.repositories.ExistenciaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExistenciaService {

    private final ExistenciaRepository existenciaRepository;

    // Listar todas las existencias
    public List<Existencia> listarTodos() {
        return existenciaRepository.findAll();
    }

    // Guardar una nueva existencia o actualizar una existente
    public Existencia guardar(Existencia existencia) {
        return existenciaRepository.save(existencia);
    }

    // Buscar una existencia por ID
    public Existencia buscarPorId(Long id) {
        return existenciaRepository.findById(id).orElse(null);
    }

    // Eliminar una existencia por ID
    public void eliminar(Long id) {
        existenciaRepository.deleteById(id);
    }

    // Aplicar rotación FIFO
    @Transactional
    public List<Existencia> aplicarRotacionFIFO() {
        return existenciaRepository.findAll().stream()
                .sorted((e1, e2) -> e1.getFechaEntrada().compareTo(e2.getFechaEntrada()))
                .collect(Collectors.toList());
    }

    // NO SE ESTA USANDO, USALO
    // Sugerir una ubicación para un alimento específico
    public String sugerirUbicacion(Alimento alimento) {
        // Lógica para sugerir ubicación según las características del alimento (ejemplo)
        if ("Perecedero".equalsIgnoreCase(alimento.getTipo())) {
            return "Nevera o Congelador";
        } else {
            return "Alacena";
        }
    }
}
