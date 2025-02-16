package com.example.hm_interview.dto;

import com.fasterxml.jackson.databind.JsonNode;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "Data Transfer Object for Create or update Transaction")
public class TransactionCreateDTO {

    @Schema(description = "Timestamp of the transaction", example = "2024-02-15T10:15:30")
    private LocalDateTime timestamp;

    @Schema(description = "Transaction type", example = "payment")
    private String type;

    @Schema(description = "Actor performing the transaction", example = "user123")
    private String actor;

    @Schema(description = "Transaction data as key-value pairs",
            example = "{\"amount\": \"100\", \"currency\": \"USD\"}")
    private JsonNode transactionData;

    public TransactionCreateDTO() {
    }

    public TransactionCreateDTO(LocalDateTime timestamp, String type, String actor, JsonNode transactionData) {
        this.timestamp = timestamp;
        this.type = type;
        this.actor = actor;
        this.transactionData = transactionData;
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
