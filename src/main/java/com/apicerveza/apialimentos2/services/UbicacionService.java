package com.apicerveza.apialimentos2.services;

import com.apicerveza.apialimentos2.entities.Ubicacion;
import com.apicerveza.apialimentos2.repositories.UbicacionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UbicacionService {

    private final UbicacionRepository ubicacionRepository;

    public List<Ubicacion> listarTodos() {
        return ubicacionRepository.findAll();
    }

    public Ubicacion guardar(Ubicacion ubicacion) {
        return ubicacionRepository.save(ubicacion);
    }

    public void eliminar(Long id) {
        ubicacionRepository.deleteById(id);
    }

    public Ubicacion buscarPorId(Long id) {
        return ubicacionRepository.findById(id).orElse(null);
    }
}
