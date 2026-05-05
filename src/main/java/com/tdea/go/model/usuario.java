package com.tdea.go.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(name = "codigo_estudiante", length = 20)
    private String codigoEstudiante;

    @Column(length = 15)
    private String telefono;

    @Enumerated(EnumType.STRING)
    private RolUsuario rol = RolUsuario.PASAJERO;

    @Column(name = "fecha_registro")
    private LocalDateTime fechaRegistro;

    private Boolean activo = true;

    @Column(name = "calificacion_promedio")
    private Double calificacionPromedio = 0.0;

    @PrePersist
    protected void onCreate() {
        fechaRegistro = LocalDateTime.now();
    }
}