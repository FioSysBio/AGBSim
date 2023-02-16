package br.fiocruz.procc.acbmservice.controller;

import br.fiocruz.procc.acbmservice.commands.CellCreateCommand;
import br.fiocruz.procc.acbmservice.commands.CellGetByIdCommand;
import br.fiocruz.procc.acbmservice.commands.CellUpdateCommand;
import br.fiocruz.procc.acbmservice.domain.Cell;
import br.fiocruz.procc.acbmservice.service.CellService;
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

import java.util.List;

@Tag(name = "Cell Controller", description = "ROUTES for API of Cell operations.")
@RestController
@RequestMapping("/cells")
public class CellController {

    @Autowired
    private CellService cellService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "JSON Array of Cells registered in the database."),
            @ApiResponse(responseCode = "403", description = "No permission to access this resource."),
            @ApiResponse(responseCode = "404", description = "Resource not found in the database."),
            @ApiResponse(responseCode = "500", description = "An internal exception was generated on the Server."),
    })
    @Operation(description = "Searches all Simulation registered in the database.")
    @GetMapping()
    public ResponseEntity<List<Cell>> getAll() {

        List<Cell> cells = cellService.getAll();

        return ResponseEntity.ok(cells);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "JSON fields of Cell registered in the database by ID."),
            @ApiResponse(responseCode = "403", description = "No permission to access this resource."),
            @ApiResponse(responseCode = "404", description = "Resource not found in the database."),
            @ApiResponse(responseCode = "500", description = "An internal exception was generated on the Server."),
    })
    @Operation(description = "Search by ID Cell Result registered in the database.")
    @GetMapping("/{idCell}")
    public ResponseEntity<CellGetByIdCommand> getById(@PathVariable Long idCell) {

        Cell cell = cellService.getById(idCell);

        return ResponseEntity.ok(CellGetByIdCommand.convert(cell));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "JSON fields of Cell registered in the database by ID."),
            @ApiResponse(responseCode = "403", description = "No permission to access this resource."),
            @ApiResponse(responseCode = "404", description = "Resource not found in the database."),
            @ApiResponse(responseCode = "500", description = "An internal exception was generated on the Server."),
    })
    @Operation(description = "Create New Cell Entity in the database.")
    @PostMapping()
    public ResponseEntity<CellCreateCommand> create(@RequestBody CellCreateCommand cellCreateCommand) {

        Cell cell = cellService.save(cellCreateCommand);

        return ResponseEntity.ok(CellCreateCommand.convert(cell));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "JSON with fields and message of Cell deleted in the database by ID."),
            @ApiResponse(responseCode = "403", description = "No permission to access this resource."),
            @ApiResponse(responseCode = "404", description = "Resource not found in the database."),
            @ApiResponse(responseCode = "500", description = "An internal exception was generated on the Server."),
    })
    @Operation(description = "Delete Cell Entity by ID in the database.")
    @DeleteMapping("/{idCell}")
    public ResponseEntity<String> delete(@PathVariable Long idCell) {

        Boolean result = cellService.delete(idCell);
        if (result) {
            return ResponseEntity.ok("Cell deleted with success!");
        }

        return ResponseEntity.ok("ERROR of deleted Cell!");
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "JSON with fields updated of Cell in the database by ID."),
            @ApiResponse(responseCode = "403", description = "No permission to access this resource."),
            @ApiResponse(responseCode = "404", description = "Resource not found in the database."),
            @ApiResponse(responseCode = "500", description = "An internal exception was generated on the Server."),
    })
    @Operation(description = "Update Cell Entity by ID e new values in the database.")
    @PutMapping()
    public ResponseEntity<CellUpdateCommand> update(@RequestBody CellUpdateCommand cellUpdateCommand) {

        Cell cell = cellService.update(cellUpdateCommand);

        return ResponseEntity.ok(CellUpdateCommand.convert(cell));
    }
}
