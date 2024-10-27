package de.dhbwkarlsruhe.modellbahn.HardwareAbstractionLayer;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Endpoints {
    @GetMapping("/hallo")
    public String hello() {
        return "Hello World!";
    }
}
