package com.pe.bcp.challenge.api.moneda.handle;

import com.pe.bcp.challenge.api.moneda.dto.TipoCambioRequest;
import com.pe.bcp.challenge.api.moneda.entidad.TipoCambio;
import com.pe.bcp.challenge.api.moneda.servicio.TipoCambioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;

@Configuration
public class TipoCambioHandler {

    @Autowired
    private TipoCambioService service;

    public Mono<ServerResponse> findAll(ServerRequest request){
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(service.findAll(), TipoCambio.class);
    }

    public Mono<ServerResponse> findOne(ServerRequest request){
        int id = Integer.parseInt(request.pathVariable("id"));
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_PROBLEM_JSON_UTF8)
                .body(service.findById(id), TipoCambio.class);
    }

    public Mono<ServerResponse> save(ServerRequest request){
        Mono<TipoCambio> type = request.bodyToMono(TipoCambio.class);

        return type.flatMap(p-> service.save(p))
                .flatMap(flat->ServerResponse.created(URI.create("api/type/".concat(String.valueOf(flat.getId()))))
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(flat)));
    }

    public Mono<ServerResponse> crearList(ServerRequest request) {
        Flux<TipoCambio> prostitutaFlux = request.bodyToFlux(TipoCambio.class);

        return prostitutaFlux.collectList()
                .map(data->data)
                .flatMap(p -> {
                    if(p.size() > 0){
                        p.parallelStream().forEach(response -> {
                            service.save(response).subscribe();
                        });
                    }

                    return Mono.just(p);

                })
                .flatMap(flat -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromValue("Se registró correctamente la lista")));
    }

    public Mono<ServerResponse> findAllTypeAmount(ServerRequest req){
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(service.findAllTypeAmount(), TipoCambio.class);
    }

    public Mono<ServerResponse> calculateResult(ServerRequest req){
        Mono<TipoCambioRequest> currency = req.bodyToMono(TipoCambioRequest.class);
        return currency.flatMap(p->service.calculateResult(p))
                .flatMap(m->ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromValue(m)))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> update(ServerRequest request){
        int id = Integer.parseInt(request.pathVariable("id"));
        Mono<TipoCambio> prostitutaRequest = request.bodyToMono(TipoCambio.class);
        Mono<TipoCambio> prostitutaDb = service.findById(id);

        return prostitutaDb.zipWith(prostitutaRequest, (prostitutaDb_,prostitutaRequest_) ->{
                    prostitutaDb_.setMonedaDestino(prostitutaRequest_.getMonedaDestino());
                    prostitutaDb_.setMonedaOrigen(prostitutaRequest_.getMonedaOrigen());
                    prostitutaDb_.setTipoCambio(prostitutaRequest_.getTipoCambio());

                    return prostitutaDb_;
                }).flatMap(p-> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_PROBLEM_JSON_UTF8)
                        .body(service.save(p), TipoCambio.class))
                .switchIfEmpty(ServerResponse.notFound().build());

    }

    public Mono<ServerResponse> delete(ServerRequest request){
        int id = Integer.parseInt(request.pathVariable("id"));
        Mono<TipoCambio> prostitutaDb = service.findById(id);
        return prostitutaDb.flatMap(p->service.delete(p).then(ServerResponse.noContent().build()));
    };

    public Mono<ServerResponse> deleteList(ServerRequest request){
        service.deleteList().subscribe();
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_PROBLEM_JSON_UTF8)
                .body(BodyInserters.fromValue("Se eliminó toda la lista"));
    };
}
