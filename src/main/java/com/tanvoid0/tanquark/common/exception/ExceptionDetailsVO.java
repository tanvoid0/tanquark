package com.tanvoid0.tanquark.common.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.netty.handler.codec.http.HttpResponseStatus;
import jakarta.ws.rs.core.Response;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class ExceptionDetailsVO implements Serializable {
    @Serial
    private static final long serialVersionUID = 9173985491713456471L;

    @JsonIgnore
    @Getter(AccessLevel.NONE)
    private HttpResponseStatus httpStatus = HttpResponseStatus.INTERNAL_SERVER_ERROR;

    private String status = HttpResponseStatus.INTERNAL_SERVER_ERROR.reasonPhrase();

    private int statusCode = HttpResponseStatus.INTERNAL_SERVER_ERROR.code();

    @Setter(AccessLevel.NONE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp = LocalDateTime.now();

    private String message;
    private String debugMessage;
    private String type;
    private List<ValidationProperty> validations = new ArrayList<>();

    public static Response response(final Exception ex) {
        log.debug("A Global Exception occurred.");
        log.error("Exception: {}", ex.getClass().getSimpleName());
        log.error("Message: {}", ex.getMessage());
        log.error("Cause: {}", ex.getCause().toString());
        log.error("Stacktrace: {}", (Object[]) ex.getStackTrace());
        final ExceptionDetailsVO detailsVO = new ExceptionDetailsVO();
        if (ex.getClass().getSimpleName().startsWith("Invalid")) {
            detailsVO.setHttpStatus(HttpResponseStatus.BAD_REQUEST);
        } else if (ex.getClass().getSimpleName().endsWith("NotFoundException")) {
            detailsVO.setHttpStatus(HttpResponseStatus.NOT_FOUND);
        }

        if (ex instanceof InvalidRequestException validationEx) {
            detailsVO.setHttpStatus(HttpResponseStatus.BAD_REQUEST);
            detailsVO.validations.addAll(validationEx.getValidations());
        }

        detailsVO.setMessage(ex.getMessage());
        detailsVO.setType(ex.getClass().getSimpleName());
        return Response
                .status(detailsVO.getStatusCode())
                .entity(detailsVO)
                .type(APPLICATION_JSON)
                .build();
    }


    private void setHttpStatus(final HttpResponseStatus httpStatus) {
        this.httpStatus = httpStatus;
        this.status = httpStatus.reasonPhrase();
        this.statusCode = httpStatus.code();
    }
}
