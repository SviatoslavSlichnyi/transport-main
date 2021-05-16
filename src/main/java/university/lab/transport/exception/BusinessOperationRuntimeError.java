package university.lab.transport.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BusinessOperationRuntimeError extends RuntimeException {
    public BusinessOperationRuntimeError() {
    }

    public BusinessOperationRuntimeError(String message) {
        super(message);
    }

    public BusinessOperationRuntimeError(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessOperationRuntimeError(Throwable cause) {
        super(cause);
    }

    public BusinessOperationRuntimeError(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
