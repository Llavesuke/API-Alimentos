package com.apicerveza.apialimentos2.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "alimentos")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Alimentos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String tipo; // Perecedero o No Perecedero

    @Column(nullable = false)
    private String estado; // Abierto o Cerrado

    @Column(name = "fecha_caducidad")
    private LocalDate fechaCaducidad;

}
