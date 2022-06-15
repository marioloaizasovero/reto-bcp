package com.pe.bcp.challenge.api.moneda.servicio;

import com.pe.bcp.challenge.api.moneda.dto.TipoCambioRequest;
import com.pe.bcp.challenge.api.moneda.dto.TipoCambioResponse;
import com.pe.bcp.challenge.api.moneda.entidad.TipoCambio;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TipoCambioService {
    Flux<TipoCambio> findAll();
    Mono<TipoCambio> save(TipoCambio obj);
    Flux<TipoCambio> findAllTypeAmount();

    Mono<TipoCambio> findById(Integer request);
    Mono<TipoCambioResponse> calculateResult(TipoCambioRequest request);

    public Mono<Void> delete(TipoCambio prostituta);
    public Mono<Void> deleteList();

}
