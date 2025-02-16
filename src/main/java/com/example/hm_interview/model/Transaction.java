package com.example.hm_interview.model;

import com.example.hm_interview.converter.JsonNodeConverter;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDateTime timestamp;
    private String type;
    private String actor;

    @Column(columnDefinition = "json")
    @Convert(converter = JsonNodeConverter.class)
    private JsonNode transactionData;

    public Transaction() {}

    public Transaction(Integer id, LocalDateTime timestamp, String type, String actor, JsonNode transactionData) {
        this.id = id;
        this.timestamp = timestamp;
        this.type = type;
        this.actor = actor;
        this.transactionData = transactionData;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public JsonNode getTransactionData() {
        return transactionData;
    }

    public void setTransactionData(JsonNode transactionData) {
        this.transactionData = transactionData;
    }
}
