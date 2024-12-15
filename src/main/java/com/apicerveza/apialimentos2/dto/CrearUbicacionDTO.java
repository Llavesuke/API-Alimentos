package com.apicerveza.apialimentos2.dto;

import lombok.Data;

@Data
public class CrearUbicacionDTO {
    private String descripcion;
    private String tipoUbicacion;
    private int capacidad;
}