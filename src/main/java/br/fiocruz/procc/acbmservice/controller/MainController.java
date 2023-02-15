package br.fiocruz.procc.acbmservice.controller;

import br.fiocruz.procc.acbmservice.domain.Simulation;
import br.fiocruz.procc.acbmservice.domain.SimulationResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Tag(name = "Main Controller", description = "ROUTES for API operations.")
@RestController
@RequestMapping("/results")
public class MainController {

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "JSON Array of Simulations registered in the database."),
            @ApiResponse(responseCode = "403", description = "No permission to access this resource."),
            @ApiResponse(responseCode = "404", description = "Resource not found in the database."),
            @ApiResponse(responseCode = "500", description = "An internal exception was generated on the Server."),
    })
    @Operation(description = "Searches all Simulation Results registered in the database.")
    @GetMapping("/")
    public ResponseEntity<List<Simulation>> getAll() {

        return ResponseEntity.ok(new ArrayList<Simulation>());
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "JSON of Simulation registered in the database."),
            @ApiResponse(responseCode = "403", description = "No permission to access this resource."),
            @ApiResponse(responseCode = "404", description = "Resource not found in the database."),
            @ApiResponse(responseCode = "500", description = "An internal exception was generated on the Server."),
    })
    @Operation(description = "Search by ID Simulation Result registered in the database.")
    @GetMapping("/{idResults}")
    public ResponseEntity<Simulation> getById(@PathVariable Long idResults) {

        return ResponseEntity.ok(new Simulation());
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "JSON Array of Simulation Results registered in the database."),
            @ApiResponse(responseCode = "403", description = "No permission to access this resource."),
            @ApiResponse(responseCode = "404", description = "Resource not found in the database."),
            @ApiResponse(responseCode = "500", description = "An internal exception was generated on the Server."),
    })
    @Operation(description = "Searches all Simulation Results registered in the database.")
    @GetMapping("/testes")
    public ResponseEntity<SimulationResult> getTestes() {

        return ResponseEntity.ok(new SimulationResult());
    }
}
