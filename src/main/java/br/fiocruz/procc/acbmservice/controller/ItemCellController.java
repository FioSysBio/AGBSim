package br.fiocruz.procc.acbmservice.controller;

import br.fiocruz.procc.acbmservice.commands.ItemCellCreateCommand;
import br.fiocruz.procc.acbmservice.commands.ItemCellGetAllCommand;
import br.fiocruz.procc.acbmservice.commands.ItemCellGetByIdCommand;
import br.fiocruz.procc.acbmservice.commands.ItemCellInfoCommand;
import br.fiocruz.procc.acbmservice.commands.ItemCellUpdateCommand;
import br.fiocruz.procc.acbmservice.domain.ItemCell;
import br.fiocruz.procc.acbmservice.service.ItemCellService;
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

@Tag(name = "ItemCell Controller", description = "ROUTES for API of ItemCell operations.")
@RestController
@RequestMapping("/item-cell")
public class ItemCellController {

    @Autowired
    private ItemCellService itemCellService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "JSON Array of ItemCells registered in the database."),
            @ApiResponse(responseCode = "403", description = "No permission to access this resource."),
            @ApiResponse(responseCode = "404", description = "Resource not found in the database."),
            @ApiResponse(responseCode = "500", description = "An internal exception was generated on the Server."),
    })
    @Operation(description = "Searches all Simulation registered in the database.")
    @GetMapping()
    public ResponseEntity<List<ItemCellInfoCommand>> getAll() {

        List<ItemCell> itemCells = itemCellService.getAll();

        return ResponseEntity.ok(ItemCellGetAllCommand.convert(itemCells));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "JSON fields of ItemCell registered in the database by ID."),
            @ApiResponse(responseCode = "403", description = "No permission to access this resource."),
            @ApiResponse(responseCode = "404", description = "Resource not found in the database."),
            @ApiResponse(responseCode = "500", description = "An internal exception was generated on the Server."),
    })
    @Operation(description = "Search by ID (PK) ItemCell Result registered in the database.")
    @GetMapping("/{idCell}/{idSimulation}")
    public ResponseEntity<ItemCellGetByIdCommand> getById(@PathVariable Long idCell, @PathVariable Long idSimulation) {

        ItemCell itemCell = itemCellService.getById(idCell, idSimulation);

        return ResponseEntity.ok(ItemCellGetByIdCommand.convert(itemCell));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "JSON fields of ItemCell registered in the database by ID."),
            @ApiResponse(responseCode = "403", description = "No permission to access this resource."),
            @ApiResponse(responseCode = "404", description = "Resource not found in the database."),
            @ApiResponse(responseCode = "500", description = "An internal exception was generated on the Server."),
    })
    @Operation(description = "Create New ItemCell Entity in the database.")
    @PostMapping()
    public ResponseEntity<ItemCellCreateCommand> create(@RequestBody ItemCellCreateCommand itemCellCreateCommand) {

        ItemCell itemCell = itemCellService.save(itemCellCreateCommand);

        return ResponseEntity.ok(ItemCellCreateCommand.convert(itemCell));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "JSON with fields and message of ItemCell deleted in the database by ID."),
            @ApiResponse(responseCode = "403", description = "No permission to access this resource."),
            @ApiResponse(responseCode = "404", description = "Resource not found in the database."),
            @ApiResponse(responseCode = "500", description = "An internal exception was generated on the Server."),
    })
    @Operation(description = "Delete ItemCell Entity by ID (PK) in the database.")
    @DeleteMapping("/{idCell}/{idSimulation}")
    public ResponseEntity<String> delete(@PathVariable Long idCell, @PathVariable Long idSimulation) {

        Boolean result = itemCellService.delete(idCell, idSimulation);
        if (result) {
            return ResponseEntity.ok("ItemCell deleted with success!");
        }

        return ResponseEntity.ok("ERROR of deleted ItemCell!");
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "JSON with fields updated of ItemCell in the database by ID (PK)."),
            @ApiResponse(responseCode = "403", description = "No permission to access this resource."),
            @ApiResponse(responseCode = "404", description = "Resource not found in the database."),
            @ApiResponse(responseCode = "500", description = "An internal exception was generated on the Server."),
    })
    @Operation(description = "Update ItemCell Entity by ID (PK) e new values in the database.")
    @PutMapping()
    public ResponseEntity<ItemCellUpdateCommand> update(@RequestBody ItemCellUpdateCommand itemCellUpdateCommand) {

        ItemCell itemCell = itemCellService.update(itemCellUpdateCommand);

        return ResponseEntity.ok(ItemCellUpdateCommand.convert(itemCell));
    }
}
