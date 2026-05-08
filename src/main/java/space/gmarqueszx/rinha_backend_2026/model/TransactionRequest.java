package space.gmarqueszx.rinha_backend_2026.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TransactionRequest(
        String id,
        Transaction transaction,
        Customer customer,
        Merchant merchant,
        Terminal terminal,
        @JsonProperty("last_transaction")
        LastTransaction lastTransaction
) {
}
