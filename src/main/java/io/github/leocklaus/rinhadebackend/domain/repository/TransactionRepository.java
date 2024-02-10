package io.github.leocklaus.rinhadebackend.domain.repository;

import io.github.leocklaus.rinhadebackend.domain.model.Client;
import io.github.leocklaus.rinhadebackend.domain.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findFirst10ByClientOrderByCreatedAt(Client user
    );
}
