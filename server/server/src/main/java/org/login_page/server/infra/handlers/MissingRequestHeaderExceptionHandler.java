package org.login_page.server.infra.handlers;

import org.login_page.server.infra.message.OperationStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MissingRequestHeaderExceptionHandler extends InternalServerExceptionHandler {

    /**
     * Manipula exceções de cabeçalhos de solicitação ausentes.
     *
     * @param exception A exceção {@link MissingRequestHeaderException} lançada.
     * @return Um {@link ResponseEntity} contendo um {@link OperationStatus} com a mensagem de erro e o status HTTP apropriado.
     */
    @ExceptionHandler(MissingRequestHeaderException.class)
    public ResponseEntity<OperationStatus> handleMissingHeaderException(MissingRequestHeaderException exception) {
        OperationStatus operationStatus = new OperationStatus(HttpStatus.BAD_REQUEST.value(), "Faltam informações no seu header: " + exception.getHeaderName());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(operationStatus);
    }
}
