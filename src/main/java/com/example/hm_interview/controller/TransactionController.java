package com.example.hm_interview.controller;

import com.example.hm_interview.api.TransactionsApi;
import com.example.hm_interview.converter.TransactionDTOConverter;
import com.example.hm_interview.dto.TransactionCreateDTO;
import com.example.hm_interview.dto.TransactionDTO;
import com.example.hm_interview.model.Transaction;
import com.example.hm_interview.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/transaction")
public class TransactionController implements TransactionsApi {

    @Autowired
    private TransactionService transactionService;

    @Override
    @GetMapping("/all")
    public List<TransactionDTO> getAllTransactions() {
        List<Transaction> transactions = transactionService.getAllTransactions();
        List<TransactionDTO> transactionDTOs = new ArrayList<>();

        for (Transaction transaction : transactions) {
            transactionDTOs.add(TransactionDTOConverter.convertToDTO(transaction));
        }

        return transactionDTOs;
    }

    @Override
    @GetMapping("/getTransactionById/{id}")
    public Optional<TransactionDTO> getTransactionById(@PathVariable Integer id) {
        Optional<Transaction> optionalTransaction = transactionService.getTransactionById(id);
        if (optionalTransaction.isPresent()) {
            Transaction transaction = optionalTransaction.get();
            TransactionDTO dto = TransactionDTOConverter.convertToDTO(transaction);
            return Optional.of(dto);
        } else {
            return Optional.empty();
        }
    }

    @Override
    @PostMapping("/createTransaction")
    public TransactionDTO createTransaction(TransactionCreateDTO transactionDTO) {
        Transaction transaction = TransactionDTOConverter.convertCreateToEntity(transactionDTO);
        Transaction savedTransaction = transactionService.saveTransaction(transaction);
        return TransactionDTOConverter.convertToDTO(savedTransaction);
    }

    @Override
    @PutMapping("/updateTransaction/{id}")
    public TransactionDTO updateTransaction(Integer id, TransactionCreateDTO transactionDTO) {
        Transaction transaction = TransactionDTOConverter.convertCreateToEntity(transactionDTO);
        Transaction updatedTransaction = transactionService.updateTransaction(id, transaction);
        return TransactionDTOConverter.convertToDTO(updatedTransaction);
    }

    @Override
    @DeleteMapping("/deleteTransaction/{id}")
    public void deleteTransaction(@PathVariable Integer id) {
        transactionService.deleteTransaction(id);
    }

    @Override
    @GetMapping("/filter")
    public List<TransactionDTO> filterTransactions(@RequestParam(required = false) String type,
                                                @RequestParam(required = false) String actor,
                                                @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime from,
                                                @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime to,
                                                @RequestParam(required = false) String key,
                                                @RequestParam(required = false) String value) {
        List<Transaction> transactions = transactionService.getTransactionsByFilter(type, actor, from, to, key, value);
        List<TransactionDTO> transactionDTOs = new ArrayList<>();

        for (Transaction transaction : transactions) {
            transactionDTOs.add(TransactionDTOConverter.convertToDTO(transaction));
        }

        return transactionDTOs;
    }

}
