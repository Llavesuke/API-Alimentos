package com.apicerveza.apialimentos2.repositories;

import com.apicerveza.apialimentos2.entities.Existencias;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  ExistenciaRepository extends JpaRepository<Existencias, Long> {
}
