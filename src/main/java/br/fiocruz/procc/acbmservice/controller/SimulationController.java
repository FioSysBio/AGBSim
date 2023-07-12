package br.fiocruz.procc.acbmservice.controller;

import br.fiocruz.procc.acbmservice.commands.EnvironmentCommand;
import br.fiocruz.procc.acbmservice.commands.SimulationCreateCommand;
import br.fiocruz.procc.acbmservice.commands.SimulationRunCommand;
import br.fiocruz.procc.acbmservice.domain.Simulation;
import br.fiocruz.procc.acbmservice.domain.SimulationResult;
import br.fiocruz.procc.acbmservice.service.EmailService;
import br.fiocruz.procc.acbmservice.service.RunSimulationService;
import br.fiocruz.procc.acbmservice.service.SimulationResultService;
import br.fiocruz.procc.acbmservice.service.SimulationService;
import br.fiocruz.procc.acbmservice.service.ValidateParametersService;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Main Controller", description = "ROUTES for API operations.")
@RestController
@RequestMapping("/simulations")
public class SimulationController {

    @Autowired
    private SimulationService simulationService;

    @Autowired
    private RunSimulationService runSimulationService;

    @Autowired
    private SimulationResultService simulationResultService;

    @Autowired
    private ValidateParametersService validateService;

    @Autowired
    private EmailService emailService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "JSON fields of Environment Simulation for Run a New Simulation."),
            @ApiResponse(responseCode = "403", description = "No permission to access this resource."),
            @ApiResponse(responseCode = "404", description = "Resource not found in the database."),
            @ApiResponse(responseCode = "500", description = "An internal exception was generated on the Server."),
    })
    @Operation(description = "Run a New Simulation.")
    @PostMapping("/run")
    public ResponseEntity<String> run(@RequestBody SimulationRunCommand simulationRunCommand) {

        try {
            EnvironmentCommand env = new EnvironmentCommand();

            if(!validateService.validateSubstrate(simulationRunCommand))
                return ResponseEntity.ok("Invalid parameters, reaction name not found in SBML!");

            String result = runSimulationService.runSimulation(simulationRunCommand);

            String textEmail = "New Simulation started from User: " + simulationRunCommand.getEmailOnwer() + "\n\n";
            emailService.sendEmail("acbm.service@gmail.com", "New Simulation started!", textEmail);

            return ResponseEntity.ok(result);

        } catch (Exception ex) {
            String textEmail = "Something unexpected happened!: " + ex.getMessage() + "\n\n" + simulationRunCommand.getEmailOnwer() + "\n\n";
            emailService.sendEmail("acbm.service@gmail.com", "New Simulation started!", textEmail);

            return ResponseEntity.ok("Something unexpected happened!");
        }
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "JSON Array of Simulations registered in the database."),
            @ApiResponse(responseCode = "403", description = "No permission to access this resource."),
            @ApiResponse(responseCode = "404", description = "Resource not found in the database."),
            @ApiResponse(responseCode = "500", description = "An internal exception was generated on the Server."),
    })
    @Operation(description = "Searches all Simulation Results registered in the database.")
    @GetMapping()
    public ResponseEntity<List<Simulation>> getAll() {

        List<Simulation> simulations = simulationService.getAll();

        return ResponseEntity.ok(simulations);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "JSON of Simulation registered in the database."),
            @ApiResponse(responseCode = "403", description = "No permission to access this resource."),
            @ApiResponse(responseCode = "404", description = "Resource not found in the database."),
            @ApiResponse(responseCode = "500", description = "An internal exception was generated on the Server."),
    })
    @Operation(description = "Search by ID Simulation registered in the database.")
    @GetMapping("/{idSimulation}")
    public ResponseEntity<Simulation> getById(@PathVariable Long idSimulation) {

        Simulation simulation = simulationService.getById(idSimulation);

        return ResponseEntity.ok(simulation);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "JSON fields of Simulation registered in the database by ID."),
            @ApiResponse(responseCode = "403", description = "No permission to access this resource."),
            @ApiResponse(responseCode = "404", description = "Resource not found in the database."),
            @ApiResponse(responseCode = "500", description = "An internal exception was generated on the Server."),
    })
    @Operation(description = "Create New Simulation Entity in the database.")
    @PostMapping()
    public ResponseEntity<SimulationCreateCommand> create(@RequestBody SimulationCreateCommand simulationCreateCommand) {

        Simulation simulation = simulationService.save(simulationCreateCommand);

        return ResponseEntity.ok(SimulationCreateCommand.convert(simulation));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "JSON of Simulation Result registered in the database."),
            @ApiResponse(responseCode = "403", description = "No permission to access this resource."),
            @ApiResponse(responseCode = "404", description = "Resource not found in the database."),
            @ApiResponse(responseCode = "500", description = "An internal exception was generated on the Server."),
    })
    @Operation(description = "Search by ID Simulation Result registered in the database.")
    @DeleteMapping("/{idSimulation}")
    public ResponseEntity<String> delete(@PathVariable Long idSimulation) {

        Boolean result = simulationService.delete(idSimulation);

        if (result) {
            return ResponseEntity.ok("Simulation deleted with success!");
        }

        return ResponseEntity.ok("ERROR of deleted Simulation");
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "JSON of Results of the Simulation registered for Email in the database."),
            @ApiResponse(responseCode = "403", description = "No permission to access this resource."),
            @ApiResponse(responseCode = "404", description = "Resource not found in the database."),
            @ApiResponse(responseCode = "500", description = "An internal exception was generated on the Server."),
    })
    @Operation(description = "Search by ID Simulation registered in the database.")
    @GetMapping("/results/{emailOnwer}")
    public ResponseEntity<List<SimulationResult>> getResultsByEmail(@PathVariable String emailOnwer) {

        List<SimulationResult> simulationResults = simulationResultService.getResultsByEmail(emailOnwer);

        return ResponseEntity.ok(simulationResults);
    }
}
