package io.github.leocklaus.rinhadebackend.domain.repository;

import io.github.leocklaus.rinhadebackend.domain.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
