package com.tanvoid0.tanquark.config;

import com.tanvoid0.tanquark.common.exception.ExceptionDetailsVO;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class GlobalExceptionHandler implements ExceptionMapper<Exception> {

    @Override
    public Response toResponse(final Exception exception) {
        return ExceptionDetailsVO.response(exception);
    }
}
