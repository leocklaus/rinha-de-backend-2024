package io.github.leocklaus.rinhadebackend.domain.service;

import io.github.leocklaus.rinhadebackend.api.dto.BallanceResponseDTO;
import io.github.leocklaus.rinhadebackend.api.dto.StatementResponseDTO;
import io.github.leocklaus.rinhadebackend.api.dto.TransactionDTODetailed;
import io.github.leocklaus.rinhadebackend.api.dto.TransactionResponseDTO;
import io.github.leocklaus.rinhadebackend.domain.exception.ClientNotFound;
import io.github.leocklaus.rinhadebackend.domain.exception.InsufficientLimit;
import io.github.leocklaus.rinhadebackend.domain.model.Client;
import io.github.leocklaus.rinhadebackend.domain.model.Transaction;
import io.github.leocklaus.rinhadebackend.domain.repository.ClientRepository;
import io.github.leocklaus.rinhadebackend.domain.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionService {

    private ClientRepository clientRepository;
    private TransactionRepository transactionRepository;

    public TransactionService(ClientRepository clientRepository, TransactionRepository transactionRepository){
        this.clientRepository = clientRepository;
        this.transactionRepository = transactionRepository;
    }

    public TransactionResponseDTO crateTransaction(Long clientId, Transaction transaction){
        Client client = returnsAClientOrThrowsExceptionIfNonExists(clientId);
        if(client.hasNotEnoughLimit(transaction.getValue(), transaction.getTransactionType())){
            throw new InsufficientLimit();
        }
        transaction.setClient(client);
        transaction = transactionRepository.save(transaction);
        var responseDTO = getANewTransactionResponseDTO(client, transaction);
        return responseDTO;
    }

    public StatementResponseDTO getStatement(Long clientId){
        Client client = returnsAClientOrThrowsExceptionIfNonExists(clientId);
        List<Transaction> transactions = transactionRepository.findFirst10ByClientOrderByCreatedAt(client);
        return getStatementDTO(client, transactions);
    }

    private Client returnsAClientOrThrowsExceptionIfNonExists(Long clientId){
        return clientRepository.findById(clientId)
                .orElseThrow(()-> new ClientNotFound());
    }

    private TransactionResponseDTO getANewTransactionResponseDTO(Client client, Transaction transaction){
        int limite = client.getCreditLimit();
        int saldo = client.getInitialBalance() - transaction.getValue();

        return new TransactionResponseDTO(limite, saldo);
    }

    private StatementResponseDTO getStatementDTO(Client client, List<Transaction> transactions){
        var saldo = new BallanceResponseDTO(
                client.getInitialBalance(), LocalDateTime.now(), client.getCreditLimit());

        var ultimasTransacoes = transactions.stream()
                .map(transaction -> {
                    return new TransactionDTODetailed(transaction.getValue(), transaction.getTransactionType(),
                            transaction.getDescription(), transaction.getCreatedAt());
                })
                .toList();

        var statementResponse = new StatementResponseDTO(saldo, ultimasTransacoes);

        return statementResponse;
    }

}
