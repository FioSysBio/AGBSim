package br.fiocruz.procc.acbmservice.controller;

import br.fiocruz.procc.acbmservice.commands.MetaboliteCreateCommand;
import br.fiocruz.procc.acbmservice.commands.MetaboliteUpdateCommand;
import br.fiocruz.procc.acbmservice.domain.Metabolite;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Tag(name = "Metabolite Controller", description = "ROUTES for API of Metabolite operations.")
@RestController
@RequestMapping("/")
public class MetaboliteController {

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "JSON Array of Metabolites registered in the database."),
            @ApiResponse(responseCode = "403", description = "No permission to access this resource."),
            @ApiResponse(responseCode = "404", description = "Resource not found in the database."),
            @ApiResponse(responseCode = "500", description = "An internal exception was generated on the Server."),
    })
    @Operation(description = "Searches all Simulation Results registered in the database.")
    @GetMapping("/metabolites")
    public ResponseEntity<List<Metabolite>> getAll() {

        return ResponseEntity.ok(new ArrayList<Metabolite>());
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "JSON fields of Metabolite registered in the database by ID."),
            @ApiResponse(responseCode = "403", description = "No permission to access this resource."),
            @ApiResponse(responseCode = "404", description = "Resource not found in the database."),
            @ApiResponse(responseCode = "500", description = "An internal exception was generated on the Server."),
    })
    @Operation(description = "Search by ID Metabolite registered in the database.")
    @GetMapping("/metabolites/id")
    public ResponseEntity<Metabolite> getMetaboliteById() {

        return ResponseEntity.ok(new Metabolite());
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "JSON fields of Metabolite registered in the database by ID."),
            @ApiResponse(responseCode = "403", description = "No permission to access this resource."),
            @ApiResponse(responseCode = "404", description = "Resource not found in the database."),
            @ApiResponse(responseCode = "500", description = "An internal exception was generated on the Server."),
    })
    @Operation(description = "Create New Metabolite Entity in the database.")
    @PostMapping("/metabolites")
    public ResponseEntity<Metabolite> create(@RequestBody MetaboliteCreateCommand metaboliteCreateCommand) {

        return ResponseEntity.ok(new Metabolite());
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "JSON with fields and message of Metabolite deleted in the database by ID."),
            @ApiResponse(responseCode = "403", description = "No permission to access this resource."),
            @ApiResponse(responseCode = "404", description = "Resource not found in the database."),
            @ApiResponse(responseCode = "500", description = "An internal exception was generated on the Server."),
    })
    @Operation(description = "Delete Metabolite Entity by ID in the database.")
    @DeleteMapping("/metabolites")
    public ResponseEntity<Metabolite> delete(@PathVariable Long idMetabolite) {

        return ResponseEntity.ok(new Metabolite());
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "JSON with fields updated of Metabolite in the database by ID."),
            @ApiResponse(responseCode = "403", description = "No permission to access this resource."),
            @ApiResponse(responseCode = "404", description = "Resource not found in the database."),
            @ApiResponse(responseCode = "500", description = "An internal exception was generated on the Server."),
    })
    @Operation(description = "Update Metabolite Entity by ID e new values in the database.")
    @PutMapping("/metabolites")
    public ResponseEntity<Metabolite> update(@RequestBody MetaboliteUpdateCommand metaboliteUpdateCommand) {

        return ResponseEntity.ok(new Metabolite());
    }
}
