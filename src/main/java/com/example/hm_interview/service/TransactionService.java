package com.example.hm_interview.service;

import com.example.hm_interview.model.Transaction;
import com.example.hm_interview.repository.TransactionRepository;
import com.example.hm_interview.specification.TransactionSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public Optional<Transaction> getTransactionById(Integer id) {
        return transactionRepository.findById(id);
    }

    public Transaction saveTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public Transaction updateTransaction(Integer id, Transaction transaction) {
        Optional<Transaction> currentTransaction = getTransactionById(id);
        if (currentTransaction.isPresent()) {
            Transaction findedTransaction = currentTransaction.get();
            findedTransaction.setType(transaction.getType());
            findedTransaction.setActor(transaction.getActor());
            findedTransaction.setTransactionData(transaction.getTransactionData());

            return transactionRepository.save(findedTransaction);
        } else {
            throw new RuntimeException("Transaction not found");
        }
    }

    public void deleteTransaction(Integer id) {
        transactionRepository.deleteById(id);
    }

    public List<Transaction> getTransactionsByFilter(String type, String actor, LocalDateTime from, LocalDateTime to, String key, String value) {
        Specification<Transaction> spec = TransactionSpecification.filterTransactions(type, actor, from, to, key, value);
        return transactionRepository.findAll(spec);
    }
}
