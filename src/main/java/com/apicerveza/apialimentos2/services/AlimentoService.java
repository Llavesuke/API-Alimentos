package com.apicerveza.apialimentos2.services;

import com.apicerveza.apialimentos2.entities.Alimento;
import com.apicerveza.apialimentos2.repositories.AlimentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AlimentoService {

    private final AlimentoRepository alimentoRepository;

    public List<Alimento> listarTodos() {
        return alimentoRepository.findAll();
    }

    public Alimento guardar(Alimento alimento) {
        return alimentoRepository.save(alimento);
    }

    public void eliminar(Long id) {
        alimentoRepository.deleteById(id);
    }

    public Alimento buscarPorId(Long id) {
        return alimentoRepository.findById(id).orElse(null);
    }

    @Transactional
    public String moverAlimento(Long alimentoId, String nuevaUbicacion) {
        Alimento alimento = buscarPorId(alimentoId);
        if (alimento == null) {
            return "El alimento no existe";
        }
        // Lógica para mover el alimento (ejemplo simplificado)
        alimento.setEstado("Movido a " + nuevaUbicacion);
        alimentoRepository.save(alimento);
        return "Alimento movido exitosamente";
    }

    public List<Alimento> alimentosProximosACaducar() {
        LocalDate ahora = LocalDate.now();
        return alimentoRepository.findAll().stream()
                .filter(a -> a.getFechaCaducidad() != null && a.getFechaCaducidad().isBefore(ahora.plusDays(7)))
                .collect(Collectors.toList());
    }

    public List<Alimento> buscarPorTipo(String tipo) {
        return alimentoRepository.findByTipo(tipo);
    }

    public List<Alimento> buscarPorRangoDeFechas(LocalDate inicio, LocalDate fin) {
        return alimentoRepository.findAlimentosPorRangoDeFechas(inicio, fin);
    }

    public List<Alimento> buscarAbiertos() {
        return alimentoRepository.findAlimentosAbiertos();
    }
}
