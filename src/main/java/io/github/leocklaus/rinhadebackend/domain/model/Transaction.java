package io.github.leocklaus.rinhadebackend.domain.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer value;
    @Column(length = 1)
    private String transactionType;
    @Column(length = 10)
    private String description;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    public Transaction(){

    }

    public Transaction(Long id, Integer value, String transactionType, String description) {
        this.id = id;
        this.value = value;
        this.transactionType = transactionType;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
