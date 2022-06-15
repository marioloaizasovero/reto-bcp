package com.pe.bcp.challenge.api.moneda.util;

import lombok.Data;

@Data
public class Response<T> {
    private String responseCode;
    private String responseMessage;
    private T responseData;

}
