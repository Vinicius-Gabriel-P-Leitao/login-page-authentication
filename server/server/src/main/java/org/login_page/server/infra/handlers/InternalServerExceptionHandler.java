package org.login_page.server.infra.handlers;

import org.login_page.server.infra.message.OperationStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class InternalServerExceptionHandler {

    /**
     * Retorna uma resposta HTTP para erros internos do servidor.
     *
     * @return Um {@link ResponseEntity} contendo um {@link OperationStatus} com a mensagem de erro e o message HTTP apropriado.
     */
    protected ResponseEntity<OperationStatus> internalServerError() {
        OperationStatus operationStatus = new OperationStatus(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Ocorreu um erro interno no servidor.");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(operationStatus);
    }
}
