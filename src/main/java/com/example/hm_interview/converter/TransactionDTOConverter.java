package com.example.hm_interview.converter;

import com.example.hm_interview.dto.TransactionCreateDTO;
import com.example.hm_interview.dto.TransactionDTO;
import com.example.hm_interview.model.Transaction;

public class TransactionDTOConverter {

    public static TransactionDTO convertToDTO(Transaction transaction) {
        TransactionDTO dto = new TransactionDTO();
        dto.setId(transaction.getId());
        dto.setTimestamp(transaction.getTimestamp());
        dto.setType(transaction.getType());
        dto.setActor(transaction.getActor());
        dto.setTransactionData(transaction.getTransactionData());
        return dto;
    }

    public static Transaction convertToEntity(TransactionDTO dto) {
        Transaction transaction = new Transaction();
        transaction.setId(dto.getId());
        transaction.setTimestamp(dto.getTimestamp());
        transaction.setType(dto.getType());
        transaction.setActor(dto.getActor());
        transaction.setTransactionData(dto.getTransactionData());
        return transaction;
    }

    public static Transaction convertCreateToEntity(TransactionCreateDTO dto) {
        Transaction transaction = new Transaction();
        transaction.setTimestamp(dto.getTimestamp());
        transaction.setType(dto.getType());
        transaction.setActor(dto.getActor());
        transaction.setTransactionData(dto.getTransactionData());
        return transaction;
    }
}
