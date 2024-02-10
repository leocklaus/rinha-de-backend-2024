package io.github.leocklaus.rinhadebackend.api.controller;

import io.github.leocklaus.rinhadebackend.domain.exception.ClientNotFound;
import io.github.leocklaus.rinhadebackend.domain.exception.InsufficientLimit;
import io.github.leocklaus.rinhadebackend.domain.model.Transaction;
import io.github.leocklaus.rinhadebackend.domain.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clients")
public class ClientsController {

    private final TransactionService transactionService;

    public ClientsController(TransactionService transactionService){
        this.transactionService = transactionService;
    }

    @PostMapping ("/{id}/extrato")
    public ResponseEntity<?> getStatement(@PathVariable long userId){
        try{
            var statement = transactionService.getStatement(userId);
            return ResponseEntity.ok(statement);
        }catch (ClientNotFound clientNotFound){
            ResponseEntity.notFound();
        }finally {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/{id}/transacoes")
    public ResponseEntity<?> createTransaction(@PathVariable long userId, @RequestBody Transaction transaction){
        try{
            var transactionDTO = transactionService.crateTransaction(userId, transaction);
            return ResponseEntity.ok(transactionDTO);
        }catch (ClientNotFound clientNotFound){
            ResponseEntity.notFound();
        }catch (InsufficientLimit insufficientLimit){
            ResponseEntity.unprocessableEntity();
        }finally {
            return ResponseEntity.internalServerError().build();
        }
    }

}
