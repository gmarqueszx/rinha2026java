package space.gmarqueszx.rinha_backend_2026.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Transaction(
        double amount,
        int installments,
        @JsonProperty("requested_at")
        String requestAt
) {
}
