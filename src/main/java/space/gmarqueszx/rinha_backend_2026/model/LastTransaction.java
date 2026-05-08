package space.gmarqueszx.rinha_backend_2026.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

public record LastTransaction(
        Instant timestamp,
        @JsonProperty("km_from_current")
        double kmFromCurrent
) {
}
