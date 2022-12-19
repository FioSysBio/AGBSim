package br.fiocruz.procc.acbmservice.controller;

import br.fiocruz.procc.acbmservice.commands.CellCreateCommand;
import br.fiocruz.procc.acbmservice.commands.CellUpdateCommand;
import br.fiocruz.procc.acbmservice.domain.Cell;
import br.fiocruz.procc.acbmservice.domain.Simulation;
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

@Tag(name = "Cell Controller", description = "ROUTES for API of Cell operations.")
@RestController
@RequestMapping("/")
public class CellController {

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "JSON Array of Cells registered in the database."),
            @ApiResponse(responseCode = "403", description = "No permission to access this resource."),
            @ApiResponse(responseCode = "404", description = "Resource not found in the database."),
            @ApiResponse(responseCode = "500", description = "An internal exception was generated on the Server."),
    })
    @Operation(description = "Searches all Simulation registered in the database.")
    @GetMapping("/cells")
    public ResponseEntity<List<Cell>> getAll() {

        return ResponseEntity.ok(new ArrayList<Cell>());
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "JSON fields of Cell registered in the database by ID."),
            @ApiResponse(responseCode = "403", description = "No permission to access this resource."),
            @ApiResponse(responseCode = "404", description = "Resource not found in the database."),
            @ApiResponse(responseCode = "500", description = "An internal exception was generated on the Server."),
    })
    @Operation(description = "Search by ID Cell Result registered in the database.")
    @GetMapping("/cells/id")
    public ResponseEntity<Cell> getById() {

        return ResponseEntity.ok(new Cell());
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "JSON fields of Cell registered in the database by ID."),
            @ApiResponse(responseCode = "403", description = "No permission to access this resource."),
            @ApiResponse(responseCode = "404", description = "Resource not found in the database."),
            @ApiResponse(responseCode = "500", description = "An internal exception was generated on the Server."),
    })
    @Operation(description = "Create New Cell Entity in the database.")
    @PostMapping("/cells")
    public ResponseEntity<Cell> create(@RequestBody CellCreateCommand cellCreateCommand) {

        return ResponseEntity.ok(new Cell());
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "JSON with fields and message of Cell deleted in the database by ID."),
            @ApiResponse(responseCode = "403", description = "No permission to access this resource."),
            @ApiResponse(responseCode = "404", description = "Resource not found in the database."),
            @ApiResponse(responseCode = "500", description = "An internal exception was generated on the Server."),
    })
    @Operation(description = "Delete Cell Entity by ID in the database.")
    @DeleteMapping("/cells")
    public ResponseEntity<Cell> delete(@PathVariable Long idCell) {

        return ResponseEntity.ok(new Cell());
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "JSON with fields updated of Cell in the database by ID."),
            @ApiResponse(responseCode = "403", description = "No permission to access this resource."),
            @ApiResponse(responseCode = "404", description = "Resource not found in the database."),
            @ApiResponse(responseCode = "500", description = "An internal exception was generated on the Server."),
    })
    @Operation(description = "Update Cell Entity by ID e new values in the database.")
    @PutMapping("/cells")
    public ResponseEntity<Cell> update(@RequestBody CellUpdateCommand cellUpdateCommand) {

        return ResponseEntity.ok(new Cell());
    }
}