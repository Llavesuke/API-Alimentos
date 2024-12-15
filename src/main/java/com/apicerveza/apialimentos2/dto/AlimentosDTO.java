package com.apicerveza.apialimentos2.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class AlimentosDTO {
    private Long id;
    private String nombre;
    private String tipo;
    private String estado;
    private LocalDate fechaCaducidad;
}