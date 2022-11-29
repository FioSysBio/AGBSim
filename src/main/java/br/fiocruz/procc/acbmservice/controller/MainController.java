package br.fiocruz.procc.acbmservice.controller;

import br.fiocruz.procc.acbmservice.domain.SimulationResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class MainController {

    @GetMapping
    public ResponseEntity<SimulationResult> get() {

        return ResponseEntity.ok(new SimulationResult());
    }
}
