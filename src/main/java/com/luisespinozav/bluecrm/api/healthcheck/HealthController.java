package com.luisespinozav.bluecrm.api.healthcheck;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health")
public class HealthController {

    @GetMapping
    public ResponseEntity<HealthStatus> health () {

        HealthStatus healthStatus = new HealthStatus("ok");
        return ResponseEntity.ok(healthStatus);
    }
}
