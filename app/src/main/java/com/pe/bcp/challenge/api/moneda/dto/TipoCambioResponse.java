package com.pe.bcp.challenge.api.moneda.dto;

import com.pe.bcp.challenge.api.moneda.entidad.TipoCambio;
import lombok.Data;

@Data
public class TipoCambioResponse {
    private TipoCambio tipoCambio;
    private Double monto;
    private double montoCambiado;


}
