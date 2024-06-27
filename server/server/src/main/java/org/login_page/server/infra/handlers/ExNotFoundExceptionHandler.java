package org.login_page.server.infra.handlers;

import org.login_page.server.infra.exception.ExNotFound;
import org.login_page.server.infra.message.OperationStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExNotFoundExceptionHandler extends InternalServerExceptionHandler {

    /**
     * Manipula exceções de recursos não encontrados.
     *
     * @param exception A exceção {@link ExNotFound} lançada.
     * @return Um {@link ResponseEntity} contendo um {@link OperationStatus} com a mensagem de erro e o message HTTP apropriado.
     */
    @ExceptionHandler(ExNotFound.class)
    public ResponseEntity<OperationStatus> handleNotFound(ExNotFound exception) {
        try {
            OperationStatus operationStatus = new OperationStatus(HttpStatus.NOT_FOUND.value(), exception.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(operationStatus);
        } catch (Exception exceptionInternal) {
            return internalServerError();
        }
    }
}
