package com.pe.bcp.challenge.api.moneda.servicio.implementacion;


import com.pe.bcp.challenge.api.moneda.dto.TipoCambioRequest;
import com.pe.bcp.challenge.api.moneda.dto.TipoCambioResponse;
import com.pe.bcp.challenge.api.moneda.entidad.TipoCambio;
import com.pe.bcp.challenge.api.moneda.repositorio.TipoCambioRepository;
import com.pe.bcp.challenge.api.moneda.servicio.TipoCambioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
//@AllArgsConstructor //=> no te obliga que sea de final pero tipo final para inyectar a los clientes sin el autowire
//@RequiredArgsConstructor //=> obliga a que lo que instancie siempre se final
public class TipoCambioServiceImpl implements TipoCambioService {


    @Autowired
    private TipoCambioRepository repository;

    @Override
    public Flux<TipoCambio> findAll() {
        return repository.findAll();
    }

    @Override
    public Mono<TipoCambio> save(TipoCambio obj) {
        return repository.save(obj);
    }

    @Override
    public Flux<TipoCambio> findAllTypeAmount() {
        return repository.findAllTypeAmount();
    }

    @Override
    public Mono<TipoCambio> findById(Integer request) {
        return repository.findById(request);
    }

    @Override
    public Mono<TipoCambioResponse> calculateResult(TipoCambioRequest request) {
        return repository.calculateResult(request.getTipoCambio().getMonedaOrigen(),request.getTipoCambio().getMonedaDestino())
                .flatMap(p->{
                    TipoCambioResponse resp = new TipoCambioResponse();
                    resp.setTipoCambio(p);
                    resp.setMonto(request.getMonto());
                    resp.setMontoCambiado(request.getMonto()*p.getTipoCambio());
                    System.out.println(request.getMonto());
                    System.out.println(p.getTipoCambio());
                    System.out.println(request.getMonto()*p.getTipoCambio());
                    return Mono.just(resp); });
    }

    @Override
    public Mono<Void> delete(TipoCambio prostituta) {
        return repository.delete(prostituta);
    }

    @Override
    public Mono<Void> deleteList() {
        return repository.deleteAll();
    }


}
