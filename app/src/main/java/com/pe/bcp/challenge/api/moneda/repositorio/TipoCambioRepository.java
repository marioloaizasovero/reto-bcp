package com.pe.bcp.challenge.api.moneda.repositorio;

import com.pe.bcp.challenge.api.moneda.entidad.TipoCambio;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TipoCambioRepository extends R2dbcRepository<TipoCambio, Integer> {

    @Query("select distinct(moneda_destino) from tipocambio")
    Flux<TipoCambio> findAllTypeAmount();

    @Query("select * from tipocambio where moneda_origen = :monedaOrigen and moneda_destino = :monedaDestino")
    Mono<TipoCambio> calculateResult(String monedaOrigen, String monedaDestino);


}
