package com.tdea.go.dto;

import com.tdea.go.model.MetodoPago;
import lombok.Data;

@Data
public class CompletarViajeRequest {

    private Double monto;
    private MetodoPago metodoPago;

}