package com.example.market_api.exception;




import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

public class BadRequestApiException extends RuntimeException {

    private final ErrorCode errorCode;
    private final HttpStatus statusCode;
    private List<String> parameters;





    public BadRequestApiException(ErrorCode errorCode, HttpStatus httpStatusCode) {
        super(errorCode.getMessageTitleKey());
        this.errorCode = errorCode;
        this.statusCode = httpStatusCode;
    }

    public BadRequestApiException(ErrorCode errorCode, HttpStatus httpStatusCode, List<String> parameters) {
        super(errorCode.getMessageTitleKey());
        this.errorCode = errorCode;
        this.statusCode = httpStatusCode;
        this.parameters = parameters;
    }

    public ErrorCode getErrorCode() {
        return this.errorCode;
    }

    public HttpStatus getStatusCode() {
        return this.statusCode;
    }
    /**
     * Return parameters as Object[] as MessageSource requires
     */
    public Object[] getParameters() {
        return this.parameters == null ? null : this.parameters.toArray();
    }

    /**
     * Add parameter to be used from i18n during localization
     * @return ApiException to be able to chain parameters
     */
    public BadRequestApiException addParameter(String parameter) {
        if (this.parameters == null) {
            this.parameters = new ArrayList<>();
        }
        this.parameters.add(parameter);
        return this;
    }

    /**
     * Add multiple parameters to be used from i18n during localization
     */
    public void addParameters(List<String> parameters) {
        this.parameters = parameters;
    }
}

