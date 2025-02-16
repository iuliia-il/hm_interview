package com.example.hm_interview.api;

import com.example.hm_interview.dto.TransactionCreateDTO;
import com.example.hm_interview.dto.TransactionDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Tag(name = "Transactions", description = "API for managing transactions")
public interface TransactionsApi {

    @Operation(
            summary = "Retrieve all transactions",
            description = "Fetches a list of all transactions stored in the database."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the list of transactions."),
            @ApiResponse(responseCode = "500", description = "Internal server error while retrieving transactions.")
    })
    List<TransactionDTO> getAllTransactions();

    @Operation(
            summary = "Retrieve a transaction by ID",
            description = "Fetches a single transaction by its unique identifier."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the transaction."),
            @ApiResponse(responseCode = "404", description = "Transaction not found for the given ID."),
            @ApiResponse(responseCode = "500", description = "Internal server error while retrieving the transaction.")
    })
    Optional<TransactionDTO> getTransactionById(@PathVariable Integer id);

    @Operation(
            summary = "Create a new transaction",
            description = "Creates and stores a new transaction in the database."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Transaction successfully created."),
            @ApiResponse(responseCode = "400", description = "Invalid request data."),
            @ApiResponse(responseCode = "500", description = "Internal server error.")
    })
    TransactionDTO createTransaction(@RequestBody TransactionCreateDTO transaction);

    @Operation(
            summary = "Update an existing transaction",
            description = "Updates the details of a specific transaction in the database."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transaction successfully updated."),
            @ApiResponse(responseCode = "404", description = "Transaction not found."),
            @ApiResponse(responseCode = "400", description = "Invalid request data."),
            @ApiResponse(responseCode = "500", description = "Internal server error.")
    })
    TransactionDTO updateTransaction(@PathVariable Integer id, @RequestBody TransactionCreateDTO transaction);

    @Operation(
            summary = "Delete a transaction",
            description = "Removes the specified transaction from the database."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Transaction successfully deleted."),
            @ApiResponse(responseCode = "404", description = "Transaction not found."),
            @ApiResponse(responseCode = "500", description = "Internal server error.")
    })
    void deleteTransaction(@PathVariable Integer id);

    @Operation(
            summary = "Filter transactions by criteria",
            description = "Retrieves transactions that match the specified search criteria."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved transactions."),
            @ApiResponse(responseCode = "400", description = "Invalid filter parameters."),
            @ApiResponse(responseCode = "500", description = "Internal server error.")
    })
    List<TransactionDTO> filterTransactions(@RequestParam String type, @RequestParam String actor,
                                         @RequestParam LocalDateTime from, @RequestParam LocalDateTime to,
                                         @RequestParam String key, @RequestParam String value);
}
