package com.apicerveza.apialimentos2.services;

import com.apicerveza.apialimentos2.entities.Ubicaciones;
import com.apicerveza.apialimentos2.repositories.UbicacionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UbicacionService {

    @Autowired
    private UbicacionRepository ubicacionRepository;

    public List<Ubicaciones> listarTodos() {
        return ubicacionRepository.findAll();
    }

    public Ubicaciones guardar(Ubicaciones ubicacion) {
        return ubicacionRepository.save(ubicacion);
    }

    public void eliminar(Long id) {
        ubicacionRepository.deleteById(id);
    }

    public Ubicaciones buscarPorId(Long id) {
        return ubicacionRepository.findById(id).orElse(null);
    }
}
