package br.fiocruz.procc.acbmservice.service;

import br.fiocruz.procc.acbmservice.commands.EnvironmentCommand;
import br.fiocruz.procc.acbmservice.commands.SimulationInfoCommand;
import br.fiocruz.procc.acbmservice.domain.Environment;
import br.fiocruz.procc.acbmservice.domain.RunWindow;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class RunSimulationService {

    public String runSimulatoin(EnvironmentCommand environmentCommand, SimulationInfoCommand simulationInfoCommand) {

        try {
//            for (int i = 0; i < table.getRowCount(); i++) {
//                ArrayList<String> al = new ArrayList<String>();
//                for (int j = 1; j < table.getColumnCount(); j+=2) {
//                    al.add((String) table.getModel().getValueAt(i, j));
//                }
//                ex_rxns_name.add(al);
//                ArrayList<Integer> al2 = new ArrayList<Integer>();
//                for (int j = 2; j < table.getColumnCount(); j+=2) {
//                    al2.add(Integer.parseInt((String) table.getModel().getValueAt(i, j)));
//                }
//                ex_rxns_direction.add(al2);
//            }
            Double n_real = simulationInfoCommand.getMetaboliteScale()
                    * Math.pow(10, simulationInfoCommand.getMetaboliteScaleMult());

            Environment.setParameters(environmentCommand, n_real);
            RunWindow w = new RunWindow();
            w.execute();

        } catch (Exception ex) {
            ex.printStackTrace();

            return "Invalid Input. Try Again";
        }

        return "Simulation is running";
    }
}
