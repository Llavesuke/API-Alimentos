package com.apicerveza.apialimentos2.services;

import com.apicerveza.apialimentos2.entities.Alimentos;
import com.apicerveza.apialimentos2.entities.Existencias;
import com.apicerveza.apialimentos2.repositories.ExistenciaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExistenciaService {

    @Autowired
    private ExistenciaRepository existenciaRepository;

    // Listar todas las existencias
    public List<Existencias> listarTodos() {
        return existenciaRepository.findAll();
    }

    // Guardar una nueva existencia o actualizar una existente
    public Existencias guardar(Existencias existencia) {
        return existenciaRepository.save(existencia);
    }

    // Buscar una existencia por ID
    public Existencias buscarPorId(Long id) {
        return existenciaRepository.findById(id).orElse(null);
    }

    // Eliminar una existencia por ID
    public void eliminar(Long id) {
        existenciaRepository.deleteById(id);
    }

    // Aplicar rotación FIFO
    @Transactional
    public List<Existencias> aplicarRotacionFIFO() {
        return existenciaRepository.findAll().stream()
                .sorted((e1, e2) -> e1.getFechaEntrada().compareTo(e2.getFechaEntrada()))
                .collect(Collectors.toList());
    }

    // Sugerir una ubicación para un alimento específico
    public String sugerirUbicacion(Alimentos alimento) {
        if ("Perecedero".equalsIgnoreCase(alimento.getTipo())) {
            return "Nevera o Congelador";
        } else {
            return "Alacena";
        }
    }
}