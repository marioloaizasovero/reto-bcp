package com.pe.bcp.challenge.api.moneda.entidad;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;


@Data
@Table("tipocambio")
public class TipoCambio {
    @Id
    private Integer id;

    @Column("moneda_origen")
    @JsonProperty("moneda_origen")
    private String monedaOrigen;

    @Column("moneda_destino")
    @JsonProperty("moneda_destino")
    private String monedaDestino;

    @Column("tipo_cambio")
    @JsonProperty("tipo_cambio")
    private Double tipoCambio;
}
