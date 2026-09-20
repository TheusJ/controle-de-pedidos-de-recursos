package com.matheus.controlepedidosrecursos.response;

import com.matheus.controlepedidosrecursos.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GeralHandleException {

    @ExceptionHandler(FuncionarioIdInvalidoException.class)
    public ResponseEntity<?> funcionarioIdInvalidoHandle(FuncionarioIdInvalidoException exception){
        Map<String, Object> response = new HashMap<>();
        response.put("status", HttpStatus.NOT_FOUND);
        response.put("timestamp", LocalDateTime.now());
        response.put("error", exception.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(SoliciacaoIdInvalido.class)
    public ResponseEntity<?> solicitacaoIdInvalidoHandle(SoliciacaoIdInvalido exception){
        Map<String, Object> response = new HashMap<>();
        response.put("status", HttpStatus.NOT_FOUND);
        response.put("timestamp", LocalDateTime.now());
        response.put("error", exception.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(SetorComSolicitacaoPendenteException.class)
    public ResponseEntity<?> setorComSolicitacaoPendenteHandle(SetorComSolicitacaoPendenteException exception){
        Map<String, Object> response = new HashMap<>();
        response.put("status", HttpStatus.BAD_REQUEST);
        response.put("timestamp", LocalDateTime.now());
        response.put("error", exception.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(FuncionarioCpfJaExistenteException.class)
    public ResponseEntity<?> funcionarioCpfJaExistenteHandle(FuncionarioCpfJaExistenteException exception){
        Map<String, Object> response = new HashMap<>();
        response.put("status", HttpStatus.BAD_REQUEST);
        response.put("timestamp", LocalDateTime.now());
        response.put("error", exception.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(FuncionarioIdJaExistenteException.class)
    public ResponseEntity<?> funcionarioIdJaExistenteHandle(FuncionarioIdJaExistenteException exception){
        Map<String, Object> response = new HashMap<>();
        response.put("status", HttpStatus.BAD_REQUEST);
        response.put("timestamp", LocalDateTime.now());
        response.put("error", exception.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(EmailJaExistenteException.class)
    public ResponseEntity<?> emailJaExistenteHandle(EmailJaExistenteException exception){
        Map<String, Object> response = new HashMap<>();
        response.put("status", HttpStatus.BAD_REQUEST);
        response.put("timestamp", LocalDateTime.now());
        response.put("error", exception.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
