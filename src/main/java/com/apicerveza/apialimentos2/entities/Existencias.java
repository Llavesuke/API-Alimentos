package com.apicerveza.apialimentos2.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "existencias")
public class Existencias {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "alimento_id", nullable = false)
    private Alimentos alimento;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ubicacion_id", nullable = false)
    private Ubicaciones ubicacion;

    @Column(nullable = false)
    private Integer cantidad;

    @Column(name = "fecha_entrada", nullable = false)
    private LocalDate fechaEntrada;

}