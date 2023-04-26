package br.fiocruz.procc.acbmservice.controller;

import br.fiocruz.procc.acbmservice.domain.ItemToPrintSimulation;
import br.fiocruz.procc.acbmservice.service.ItemToPrintSimulationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "ItemToPrintSimulation Controller", description = "ROUTES for API of ItemToPrintSimulation operations.")
@RestController
@RequestMapping("/itenstoprint")
public class ItemToPrintSimulationController {

    @Autowired
    private ItemToPrintSimulationService itemToPrintSimulationService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "JSON Array of Itens registered in the database that must be printed in UI."),
            @ApiResponse(responseCode = "403", description = "No permission to access this resource."),
            @ApiResponse(responseCode = "404", description = "Resource not found in the database."),
            @ApiResponse(responseCode = "500", description = "An internal exception was generated on the Server."),
    })
    @Operation(description = "Fetch all items to be drawn in the UI.")
    @GetMapping("/{tick}")
    public ResponseEntity<List<ItemToPrintSimulation>> getAllByTick(@PathVariable Integer tick) {

        List<ItemToPrintSimulation> itemToPrintSimulations = itemToPrintSimulationService.getByTick(tick);

        return ResponseEntity.ok(itemToPrintSimulations);
    }
}
