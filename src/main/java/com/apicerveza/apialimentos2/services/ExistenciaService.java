package com.apicerveza.apialimentos2.services;

import com.apicerveza.apialimentos2.dto.ExistenciasDTO;
import com.apicerveza.apialimentos2.dto.CrearExistenciaDTO;
import com.apicerveza.apialimentos2.dto.ModificarExistenciaDTO;
import com.apicerveza.apialimentos2.entities.Existencias;
import com.apicerveza.apialimentos2.entities.Ubicaciones;
import com.apicerveza.apialimentos2.exceptions.ResourceNotFoundException;
import com.apicerveza.apialimentos2.repositories.ExistenciaRepository;
import com.apicerveza.apialimentos2.repositories.UbicacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExistenciaService {

    @Autowired
    private ExistenciaRepository existenciaRepository;

    @Autowired
    private UbicacionRepository ubicacionRepository;

    public List<ExistenciasDTO> listarTodos() {
        return existenciaRepository.findAll().stream()
                .map(this::convertirAExistenciasDTO)
                .collect(Collectors.toList());
    }

    public ExistenciasDTO guardar(CrearExistenciaDTO crearExistenciaDTO) {
        Ubicaciones ubicacion = ubicacionRepository.findById(crearExistenciaDTO.getUbicacion().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Ubicacion not found with id " + crearExistenciaDTO.getUbicacion().getId()));

        Existencias existencia = new Existencias();
        existencia.setAlimento(crearExistenciaDTO.getAlimento());
        existencia.setCantidad(crearExistenciaDTO.getCantidad());
        existencia.setFechaEntrada(crearExistenciaDTO.getFechaIngreso());
        existencia.setUbicacion(ubicacion);
        return convertirAExistenciasDTO(existenciaRepository.save(existencia));
    }

    public ExistenciasDTO buscarPorId(Long id) {
        Existencias existencia = existenciaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Existencia not found with id " + id));
        return convertirAExistenciasDTO(existencia);
    }

    public ExistenciasDTO actualizar(Long id, ModificarExistenciaDTO modificarExistenciaDTO) {
        Existencias existencia = existenciaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Existencia not found with id " + id));

        if (modificarExistenciaDTO.getAlimento() != null) {
            existencia.setAlimento(modificarExistenciaDTO.getAlimento());
        }
        if (modificarExistenciaDTO.getCantidad() != 0) {
            existencia.setCantidad(modificarExistenciaDTO.getCantidad());
        }
        if (modificarExistenciaDTO.getFechaIngreso() != null) {
            existencia.setFechaEntrada(modificarExistenciaDTO.getFechaIngreso());
        }
        if (modificarExistenciaDTO.getUbicacion() != null) {
            existencia.setUbicacion(modificarExistenciaDTO.getUbicacion());
        }

        return convertirAExistenciasDTO(existenciaRepository.save(existencia));
    }

    public void eliminar(Long id) {
        if (!existenciaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Existencia not found with id " + id);
        }
        existenciaRepository.deleteById(id);
    }

    private ExistenciasDTO convertirAExistenciasDTO(Existencias existencia) {
        ExistenciasDTO dto = new ExistenciasDTO();
        dto.setId(existencia.getId());
        dto.setAlimento(existencia.getAlimento());
        dto.setCantidad(existencia.getCantidad());
        dto.setFechaIngreso(existencia.getFechaEntrada());
        dto.setUbicacion(existencia.getUbicacion());
        return dto;
    }
}