package br.fiocruz.procc.acbmservice.controller;

import br.fiocruz.procc.acbmservice.commands.MetaboliteCreateCommand;
import br.fiocruz.procc.acbmservice.commands.MetaboliteUpdateCommand;
import br.fiocruz.procc.acbmservice.domain.Metabolite;
import br.fiocruz.procc.acbmservice.repository.MetaboliteRepository;
import br.fiocruz.procc.acbmservice.service.MetaboliteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/metabolites")
public class MetaboliteController {

    @Autowired
    private MetaboliteService metaboliteService;
    @Autowired
    private MetaboliteRepository metaboliteRepository;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "JSON Array of Metabolites registered in the database."),
            @ApiResponse(responseCode = "403", description = "No permission to access this resource."),
            @ApiResponse(responseCode = "404", description = "Resource not found in the database."),
            @ApiResponse(responseCode = "500", description = "An internal exception was generated on the Server."),
    })
    @Operation(description = "Searches all Simulation Results registered in the database.")
    @GetMapping()
    public ResponseEntity<List<Metabolite>> getAll() {

        List<Metabolite> metabolites = metaboliteService.getAll();

        return ResponseEntity.ok(metabolites);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "JSON fields of Metabolite registered in the database by ID."),
            @ApiResponse(responseCode = "403", description = "No permission to access this resource."),
            @ApiResponse(responseCode = "404", description = "Resource not found in the database."),
            @ApiResponse(responseCode = "500", description = "An internal exception was generated on the Server."),
    })
    @Operation(description = "Search by ID Metabolite registered in the database.")
    @GetMapping("/{idMetabolite}")
    public ResponseEntity<Metabolite> getMetaboliteById(@PathVariable Long idMetabolite) {

        Metabolite metabolite = metaboliteService.getById(idMetabolite);

        return ResponseEntity.ok(metabolite);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "JSON fields of Metabolite registered in the database by ID."),
            @ApiResponse(responseCode = "403", description = "No permission to access this resource."),
            @ApiResponse(responseCode = "404", description = "Resource not found in the database."),
            @ApiResponse(responseCode = "500", description = "An internal exception was generated on the Server."),
    })
    @Operation(description = "Create New Metabolite Entity in the database.")
    @PostMapping()
    public ResponseEntity<MetaboliteCreateCommand> create(@RequestBody MetaboliteCreateCommand metaboliteCreateCommand) {

        Metabolite metabolite = metaboliteService.save(metaboliteCreateCommand);

        return ResponseEntity.ok(MetaboliteCreateCommand.convert(metabolite));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "JSON with fields and message of Metabolite deleted in the database by ID."),
            @ApiResponse(responseCode = "403", description = "No permission to access this resource."),
            @ApiResponse(responseCode = "404", description = "Resource not found in the database."),
            @ApiResponse(responseCode = "500", description = "An internal exception was generated on the Server."),
    })
    @Operation(description = "Delete Metabolite Entity by ID in the database.")
    @DeleteMapping("/{idMetabolite}")
    public ResponseEntity<String> delete(@PathVariable Long idMetabolite) {

        Boolean result = metaboliteService.delete(idMetabolite);

        if (result) {

            return ResponseEntity.ok("Metabolite deleted with success!");
        }

        return ResponseEntity.ok("ERROR of deleted Metabolite!");
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "JSON with fields updated of Metabolite in the database by ID."),
            @ApiResponse(responseCode = "403", description = "No permission to access this resource."),
            @ApiResponse(responseCode = "404", description = "Resource not found in the database."),
            @ApiResponse(responseCode = "500", description = "An internal exception was generated on the Server."),
    })
    @Operation(description = "Update Metabolite Entity by ID e new values in the database.")
    @PutMapping()
    public ResponseEntity<MetaboliteUpdateCommand> update(@RequestBody MetaboliteUpdateCommand metaboliteUpdateCommand) {

        Metabolite metabolite = metaboliteService.update(metaboliteUpdateCommand);

        return ResponseEntity.ok(MetaboliteUpdateCommand.convert(metabolite));
    }
}
