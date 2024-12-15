package com.apicerveza.apialimentos2.dto;

import lombok.Data;


@Data
public class UbicacionesDTO {
    private Long id;
    private String descripcion;
    private String tipoUbicacion;
    private int capacidad;
}