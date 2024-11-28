package com.nm.client.exception;

import feign.Response;
import feign.codec.ErrorDecoder;

public class CustomErrorDecoder implements ErrorDecoder {

    private final ErrorDecoder defaultErrordecoder = new Default();

    @Override
    public Exception decode(String invoqueur, Response response) {
        if (response.status() == 400) {
            return new PatientBadRequestException(
                    "Requête incorrecte"
            );
        }
        return defaultErrordecoder.decode(invoqueur, response);
    }
}
