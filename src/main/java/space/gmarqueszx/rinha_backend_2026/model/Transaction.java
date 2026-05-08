package space.gmarqueszx.rinha_backend_2026.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

public record Transaction(
        double amount,
        int installments,
        @JsonProperty("requested_at")
        Instant requestAt
) {
}
