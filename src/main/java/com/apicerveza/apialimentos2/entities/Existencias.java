package com.apicerveza.apialimentos2.entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "existencias")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Existencias {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "alimento_id", nullable = false)
    private Alimentos alimento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ubicacion_id", nullable = false)
    private Ubicaciones ubicacion;

    @Column(nullable = false)
    private Integer cantidad;

    @Column(name = "fecha_entrada", nullable = false)
    private LocalDate fechaEntrada;
}
