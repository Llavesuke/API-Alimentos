package com.apicerveza.apialimentos2.dto;

import com.apicerveza.apialimentos2.entities.Alimentos;
import com.apicerveza.apialimentos2.entities.Ubicaciones;
import lombok.Data;
import java.time.LocalDate;

@Data
public class ExistenciasDTO {
    private Long id;
    private Alimentos alimento;
    private int cantidad;
    private LocalDate fechaIngreso;
    private Ubicaciones ubicacion;
}