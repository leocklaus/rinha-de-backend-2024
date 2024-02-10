package io.github.leocklaus.rinhadebackend.domain.service;

import io.github.leocklaus.rinhadebackend.domain.exception.ClientNotFound;
import io.github.leocklaus.rinhadebackend.domain.model.Client;
import io.github.leocklaus.rinhadebackend.domain.model.Transaction;
import io.github.leocklaus.rinhadebackend.domain.repository.ClientRepository;
import io.github.leocklaus.rinhadebackend.domain.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    private ClientRepository clientRepository;
    private TransactionRepository transactionRepository;

    public TransactionService(ClientRepository clientRepository, TransactionRepository transactionRepository){
        this.clientRepository = clientRepository;
        this.transactionRepository = transactionRepository;
    }

    public Transaction crateTransaction(Long clientId, Transaction transaction){
        Client client = returnsAClientOrThrowsExceptionIfNonExists(clientId);

    }

    private Client returnsAClientOrThrowsExceptionIfNonExists(Long clientId){
        Client client = clientRepository.findById(clientId)
                .orElseThrow(()-> new ClientNotFound());
    }

}
