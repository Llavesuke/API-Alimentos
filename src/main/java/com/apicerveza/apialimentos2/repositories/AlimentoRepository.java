package com.apicerveza.apialimentos2.repositories;

import com.apicerveza.apialimentos2.entities.Alimentos;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlimentoRepository extends JpaRepository<Alimentos, Long> {
    Page<Alimentos> findByNombreContaining(String nombre, Pageable pageable);
}