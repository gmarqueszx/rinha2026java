package space.gmarqueszx.rinha_backend_2026.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Merchant(
        String id,
        String mcc,

        @JsonProperty("avg_amount")
        double avgAmount
) {
}
