package com.apicerveza.apialimentos2.services;

import com.apicerveza.apialimentos2.dto.CrearUbicacionDTO;
import com.apicerveza.apialimentos2.dto.ModificarUbicacionDTO;
import com.apicerveza.apialimentos2.dto.UbicacionesDTO;
import com.apicerveza.apialimentos2.entities.Ubicaciones;
import com.apicerveza.apialimentos2.exceptions.ResourceNotFoundException;
import com.apicerveza.apialimentos2.repositories.UbicacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UbicacionService {

    @Autowired
    private UbicacionRepository ubicacionesRepository;

    public List<UbicacionesDTO> listarTodos() {
        return ubicacionesRepository.findAll().stream()
                .map(this::convertirAUbicacionesDTO)
                .collect(Collectors.toList());
    }

    public UbicacionesDTO guardar(CrearUbicacionDTO crearUbicacionDTO) {
        Ubicaciones ubicacion = new Ubicaciones();
        ubicacion.setDescripcion(crearUbicacionDTO.getDescripcion());
        ubicacion.setTipoUbicacion(crearUbicacionDTO.getTipoUbicacion());
        ubicacion.setCapacidad(crearUbicacionDTO.getCapacidad());
        return convertirAUbicacionesDTO(ubicacionesRepository.save(ubicacion));
    }

    public UbicacionesDTO buscarPorId(Long id) {
        Ubicaciones ubicacion = ubicacionesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ubicacion not found with id " + id));
        return convertirAUbicacionesDTO(ubicacion);
    }

    public UbicacionesDTO actualizar(Long id, ModificarUbicacionDTO modificarUbicacionDTO) {
        Ubicaciones ubicacion = ubicacionesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ubicacion not found with id " + id));

        if (modificarUbicacionDTO.getDescripcion() != null) {
            ubicacion.setDescripcion(modificarUbicacionDTO.getDescripcion());
        }
        if (modificarUbicacionDTO.getTipoUbicacion() != null) {
            ubicacion.setTipoUbicacion(modificarUbicacionDTO.getTipoUbicacion());
        }
        if (modificarUbicacionDTO.getCapacidad() != 0) {
            ubicacion.setCapacidad(modificarUbicacionDTO.getCapacidad());
        }

        return convertirAUbicacionesDTO(ubicacionesRepository.save(ubicacion));
    }

    public void eliminar(Long id) {
        if (!ubicacionesRepository.existsById(id)) {
            throw new ResourceNotFoundException("Ubicacion not found with id " + id);
        }
        ubicacionesRepository.deleteById(id);
    }

    private UbicacionesDTO convertirAUbicacionesDTO(Ubicaciones ubicacion) {
        UbicacionesDTO dto = new UbicacionesDTO();
        dto.setId(ubicacion.getId());
        dto.setDescripcion(ubicacion.getDescripcion());
        dto.setTipoUbicacion(ubicacion.getTipoUbicacion());
        dto.setCapacidad(ubicacion.getCapacidad());
        return dto;
    }
}