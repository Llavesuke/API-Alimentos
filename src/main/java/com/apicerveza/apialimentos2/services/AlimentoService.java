package com.apicerveza.apialimentos2.services;

import com.apicerveza.apialimentos2.dto.AlimentosDTO;
import com.apicerveza.apialimentos2.dto.CrearAlimentoDTO;
import com.apicerveza.apialimentos2.dto.ModificarAlimentoDTO;
import com.apicerveza.apialimentos2.entities.Alimentos;
import com.apicerveza.apialimentos2.exceptions.ResourceNotFoundException;
import com.apicerveza.apialimentos2.repositories.AlimentoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AlimentoService {
    private final AlimentoRepository alimentoRepository;

    public AlimentoService(AlimentoRepository alimentoRepository) {
        this.alimentoRepository = alimentoRepository;
    }

    // Listar todos los alimentos
    public Page<AlimentosDTO> listarAlimentos(String nombre, Pageable pageable) {
        Page<Alimentos> alimentos;
        if (nombre != null && !nombre.isEmpty()) {
            alimentos = alimentoRepository.findByNombreContaining(nombre, pageable);
        } else {
            alimentos = alimentoRepository.findAll(pageable);
        }
        return alimentos.map(this::convertirAAlimentoDTO);
    }

    // Obtener los detalles de un alimento específico, por id
    public AlimentosDTO obtenerAlimento(Long id) {
        Alimentos alimento = alimentoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Alimento no encontrado con id " + id));
        return convertirAAlimentoDTO(alimento);
    }

    // Crear un nuevo alimento
    public AlimentosDTO crearAlimento(CrearAlimentoDTO crearAlimentoDTO) {
        Alimentos alimento = new Alimentos();
        alimento.setNombre(crearAlimentoDTO.getNombre());
        alimento.setTipo(crearAlimentoDTO.getTipo());
        alimento.setEstado(crearAlimentoDTO.getEstado());
        alimento.setFechaCaducidad(crearAlimentoDTO.getFechaCaducidad());
        return convertirAAlimentoDTO(alimentoRepository.save(alimento));
    }

    // Actualizar un alimento existente
    public AlimentosDTO actualizarAlimento(Long id, ModificarAlimentoDTO modificarAlimentoDTO) {
        Alimentos alimento = alimentoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Alimento no encontrado con id " + id));

        if (modificarAlimentoDTO.getNombre() != null) {
            alimento.setNombre(modificarAlimentoDTO.getNombre());
        }
        if (modificarAlimentoDTO.getTipo() != null) {
            alimento.setTipo(modificarAlimentoDTO.getTipo());
        }
        if (modificarAlimentoDTO.getEstado() != null) {
            alimento.setEstado(modificarAlimentoDTO.getEstado());
        }
        if (modificarAlimentoDTO.getFechaCaducidad() != null) {
            alimento.setFechaCaducidad(modificarAlimentoDTO.getFechaCaducidad());
        }

        return convertirAAlimentoDTO(alimentoRepository.save(alimento));
    }

    // Eliminar un alimento
    public void eliminarAlimento(Long id) {
        Alimentos alimento = alimentoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Alimento no encontrado con id " + id));
        alimentoRepository.delete(alimento);
    }

    private AlimentosDTO convertirAAlimentoDTO(Alimentos alimento) {
        AlimentosDTO dto = new AlimentosDTO();
        dto.setId(alimento.getId());
        dto.setNombre(alimento.getNombre());
        dto.setTipo(alimento.getTipo());
        dto.setEstado(alimento.getEstado());
        dto.setFechaCaducidad(alimento.getFechaCaducidad());
        return dto;
    }
}