package com.apicerveza.apialimentos2.repositories;

import com.apicerveza.apialimentos2.entities.Ubicaciones;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UbicacionRepository extends JpaRepository<Ubicaciones, Long> {
}
