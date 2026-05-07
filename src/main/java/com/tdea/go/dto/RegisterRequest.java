package com.tdea.go.dto;

import com.tdea.go.model.RolUsuario;
import lombok.Data;

@Data
public class RegisterRequest {
    private String nombre;
    private String email;
    private String password;
    private String codigoEstudiante;
    private String telefono;
    private RolUsuario rol;
}