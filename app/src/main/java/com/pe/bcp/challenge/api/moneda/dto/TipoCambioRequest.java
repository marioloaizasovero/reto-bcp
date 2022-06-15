package com.pe.bcp.challenge.api.moneda.dto;

import com.pe.bcp.challenge.api.moneda.entidad.TipoCambio;
import lombok.Data;

@Data
public class TipoCambioRequest {
    private TipoCambio tipoCambio;
    private Double monto;

}
