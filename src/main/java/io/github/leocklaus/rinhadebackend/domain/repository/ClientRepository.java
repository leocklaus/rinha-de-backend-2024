package io.github.leocklaus.rinhadebackend.domain.repository;

import io.github.leocklaus.rinhadebackend.domain.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
