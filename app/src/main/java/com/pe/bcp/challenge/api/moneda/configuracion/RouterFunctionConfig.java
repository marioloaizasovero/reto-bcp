package com.pe.bcp.challenge.api.moneda.configuracion;

import com.pe.bcp.challenge.api.moneda.handle.TipoCambioHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class RouterFunctionConfig {

    @Bean
    public RouterFunction <ServerResponse> routes(TipoCambioHandler handler){
        return RouterFunctions.
                route(RequestPredicates.GET("/api/moneda"),handler::findAll)
                .andRoute(RequestPredicates.GET("/api/moneda/{id}"),handler::findOne)
                .andRoute(RequestPredicates.POST("/api/moneda"),handler::crearList)
                .andRoute(RequestPredicates.PUT("/api/moneda/{id}"),handler::update)
                .andRoute(RequestPredicates.DELETE("/api/moneda/{id}"),handler::delete)
                .andRoute(RequestPredicates.DELETE("/api/moneda"),handler::deleteList)

                .andRoute(RequestPredicates.POST("/api/calcularResultado"),handler::calculateResult)
                .andRoute(RequestPredicates.GET("/api/tipoCambio"),handler::findAllTypeAmount)
                ;

    }
}
