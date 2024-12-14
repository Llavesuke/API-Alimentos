package com.apicerveza.apialimentos2.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ubicaciones")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ubicaciones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String descripcion; // Ej. "Balda superior"

    @Column(name = "tipo_ubicacion", nullable = false)
    private String tipoUbicacion; // Ej. Alacena, Nevera, Congelador

    @Column(nullable = false)
    private Integer capacidad; // Capacidad máxima
}
