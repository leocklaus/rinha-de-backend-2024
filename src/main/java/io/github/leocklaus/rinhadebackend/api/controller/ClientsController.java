package io.github.leocklaus.rinhadebackend.api.controller;

import io.github.leocklaus.rinhadebackend.domain.model.Transaction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clients")
public class ClientsController {

    @PostMapping("/{id}/transacoes")
    public ResponseEntity<?> createTransaction(@PathVariable long userId, @RequestBody Transaction transaction){
        return ResponseEntity.ok().build();
    }

}
