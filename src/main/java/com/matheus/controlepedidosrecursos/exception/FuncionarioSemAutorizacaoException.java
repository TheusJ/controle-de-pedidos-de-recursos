package com.matheus.controlepedidosrecursos.exception;

public class FuncionarioSemAutorizacaoException extends RuntimeException {
    public FuncionarioSemAutorizacaoException(String message) {
        super(message);
    }
}
