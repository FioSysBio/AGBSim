package br.fiocruz.procc.acbmservice.service;

import br.fiocruz.procc.acbmservice.commands.MetaboliteGetByIdCommand;
import br.fiocruz.procc.acbmservice.commands.SimulationItensInfoCommand;
import br.fiocruz.procc.acbmservice.commands.SimulationRunCommand;
import jep.Interpreter;
import jep.JepException;
import jep.NDArray;
import jep.SharedInterpreter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;

@Service
public class ValidateParametersService {

    public Boolean validateSubstrate(SimulationRunCommand simulationRunCommand) {

        Boolean valid = false;

        for (SimulationItensInfoCommand item : simulationRunCommand.getItensSimulation()) {

            ArrayList<String> exRxnsName = new ArrayList<>();

            String mFileName = item.getCell().getMathlabFile();

            for (MetaboliteGetByIdCommand met : item.getMetabolites()) {
                String reactionName = met.getReactionName();
                exRxnsName.add(reactionName);
            }

            Interpreter interp = null;
            try {
                interp = new SharedInterpreter();

                String[] exRxnsArray = new String[exRxnsName.size()];
                for (int i = 0; i < exRxnsName.size(); i++) {
                    exRxnsArray[i] = exRxnsName.get(i);
                }

                String substrateValidate = "/files_simulation/substrateValidate.py";

                //metabolites, directions, metabolic_model - parametros dentro do script que precisam ser settados
                interp.set("reactions", exRxnsArray);
                String ext = ".json";
                interp.set("metabolic_model", mFileName + ext);

                interp.runScript(substrateValidate);
                Boolean result = (Boolean) ((NDArray) interp.getValue("result")).getData();

                System.out.println(result);
//                Arrays.stream(result).forEach(System.out::println);

                valid = result;
//                for (int i = 0; i < fluxArray.length; i++) {
//                    if (fluxArray[i] > 0) {
//                        int index = 0;
//                        for (int j = 0; j < substrates.size(); j++) {
//                            if ( fluxArray[i] < fluxArray[substrates.get(j)] ) {
//                                index = j+1;
//                            }
//                        }
//                        substrates.add(index, i);
//                    }
//                }

            } catch (JepException exc) {
                exc.printStackTrace();
            } finally {
                if(interp != null) {
                    interp.close();
                }
            }
        }

        return valid;
    }
}
