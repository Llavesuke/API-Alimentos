package com.apicerveza.apialimentos2.repositories;

import com.apicerveza.apialimentos2.entities.Alimentos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AlimentoRepository extends JpaRepository<Alimentos, Long> {

    List<Alimentos> findByTipo(String tipo);

    List<Alimentos> findByFechaCaducidadBefore(LocalDate fecha);

    List<Alimentos> findByNombreContaining(String palabraClave);

    @Query("SELECT a FROM Alimentos a WHERE a.fechaCaducidad BETWEEN :inicio AND :fin")
    List<Alimentos> findAlimentosPorRangoDeFechas(@Param("inicio") LocalDate inicio, @Param("fin") LocalDate fin);

    @Query(value = "SELECT * FROM alimentos WHERE estado = 'Abierto'", nativeQuery = true)
    List<Alimentos> findAlimentosAbiertos();
}
