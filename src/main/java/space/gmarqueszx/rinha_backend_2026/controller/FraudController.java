package space.gmarqueszx.rinha_backend_2026.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/rinha-backend")
public class FraudController {
    @GetMapping("/ready")
    public ResponseEntity<Void> ready() {
        return ResponseEntity.ok().build();
    }

    @PostMapping("/fraud-score")
    public ResponseEntity<Void> fraudScore() {
        return ResponseEntity.ok().build();
    }
}
