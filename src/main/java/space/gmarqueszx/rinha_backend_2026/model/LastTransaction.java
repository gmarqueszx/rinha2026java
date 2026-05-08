package space.gmarqueszx.rinha_backend_2026.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record LastTransaction(
        String timestamp,
        @JsonProperty("km_from_current")
        double kmFromCurrent
) {
}
