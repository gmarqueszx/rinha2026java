package space.gmarqueszx.rinha_backend_2026.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Terminal(
      @JsonProperty("is_online")
      Boolean isOnline,
      @JsonProperty("card_present")
      Boolean cardPresent,
      @JsonProperty("km_from_current")
      double kmFromHome
) {
}
