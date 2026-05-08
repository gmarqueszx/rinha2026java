package space.gmarqueszx.rinha_backend_2026.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record FraudResponse(
        boolean approved,

        @JsonProperty("fraud_score")
        double fraudScore
) {
}
