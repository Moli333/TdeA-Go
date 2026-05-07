package com.tdea.go.dto;

import lombok.Data;

@Data
public class SolicitarViajeRequest {
    private String origen;
    private String destino;
    private Double latitudOrigen;
    private Double longitudOrigen;
    private Double latitudDestino;
    private Double longitudDestino;
}