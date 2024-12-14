package com.apicerveza.apialimentos2.repositories;

import com.apicerveza.apialimentos2.entities.Alimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AlimentoRepository extends JpaRepository<Alimento, Long> {

    List<Alimento> findByTipo(String tipo);

    List<Alimento> findByFechaCaducidadBefore(LocalDate fecha);

    List<Alimento> findByNombreContaining(String palabraClave);

    @Query("SELECT a FROM Alimento a WHERE a.fechaCaducidad BETWEEN :inicio AND :fin")
    List<Alimento> findAlimentosPorRangoDeFechas(@Param("inicio") LocalDate inicio, @Param("fin") LocalDate fin);

    @Query(value = "SELECT * FROM alimentos WHERE estado = 'Abierto'", nativeQuery = true)
    List<Alimento> findAlimentosAbiertos();
}
