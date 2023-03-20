package br.fiocruz.procc.acbmservice.service;

import br.fiocruz.procc.acbmservice.commands.EnvironmentCommand;
import br.fiocruz.procc.acbmservice.commands.SimulationRunCommand;
import br.fiocruz.procc.acbmservice.domain.Environment;
import br.fiocruz.procc.acbmservice.domain.RunWindow;
import br.fiocruz.procc.acbmservice.domain.enuns.AmountType;
import br.fiocruz.procc.acbmservice.domain.enuns.ShapeType;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;


@Service
public class RunSimulationService {

    public String runSimulatoin(SimulationRunCommand simulationRunCommand) {

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

            Environment.setParameters(transform(simulationRunCommand));
            RunWindow w = new RunWindow();
            w.execute();

        } catch (Exception ex) {
            ex.printStackTrace();

            return "Invalid Input. Try Again";
        }

        return "Simulation is running";
    }

    private EnvironmentCommand transform(SimulationRunCommand simulationRunCommand) {

        EnvironmentCommand command = new EnvironmentCommand();

        try {
            simulationRunCommand.getItensSimulation().forEach(
                    item -> {
                        //linha 315
                        command.getBacteria_name().add(item.getCell().getName());
                        //linha 316
                        if (item.getCell().getAmountType() == AmountType.GL) {
                            command.getBacteria_conc().add(Double.parseDouble(item.getCell().getAmount()));
                            command.getBacteria_count().add(-1);
                        } else {
                            command.getBacteria_count().add(Integer.parseInt(item.getCell().getAmount()));
                            command.getBacteria_conc().add((-1.0));
                        }
                        //linha 323
                        command.getBacteria_scale().add(item.getCell().getScale());
                        //linha 325
                        if (item.getCell().getRadius() != (0.0) || item.getCell().getRadius() != null) {
                            command.getR_bac().add(
                                    item.getCell().getRadius() *
                                            (Math.cbrt(command.getBacteria_scale().get(command.getBacteria_scale().size() - 1)))
                            );
                        } else {
                            command.getR_bac().add((0.0));
                        }
                        //linha 330
                        if (item.getCell().getShapeType() == ShapeType.COCCI) {
                            command.getL_bac().add((0.0));
                        } else {
                            command.getL_bac().add(
                                    item.getCell().getLength() *
                                            (Math.cbrt(command.getBacteria_scale().get(command.getBacteria_scale().size() - 1)))
                            );
                        }
                        //linha 335
                        if (command.getL_bac().get(command.getL_bac().size() - 1) == (0.0)) {
                            command.getV_bac().add(
                                    (4 / 3) *
                                            Math.PI *
                                            Math.pow( command.getR_bac().get( command.getR_bac().size() - 1 ), 3 ) *
                                            Math.pow(10, -18)
                            );
                        } else {
                            command.getV_bac().add(
                                    Math.PI *
                                            Math.pow(command.getR_bac().get( command.getR_bac().size() - 1 ), 2) *
                                            command.getL_bac().get(command.getL_bac().size() - 1) *
                                            Math.pow(10, -18)

                            );
                        }
                        //linha 341
                        if (item.getCell().getMass() != 0.0 || item.getCell().getMass() != null) {
                            command.getM_bac().add(
                                    (1.1) *
                                            command.getV_bac().get( command.getV_bac().size() - 1) *
                                            Math.pow(10 , 6)
                            );
                        } else {
                            command.getM_bac().add(
                                    item.getCell().getMass() *
                                            Math.pow(10, -12) *
                                            command.getBacteria_scale().get(command.getBacteria_scale().size() - 1)
                            );
                        }
                        //linha 353
                        command.getEat_radius().add(item.getCell().getEatRadius());

                        command.getMFile().add(item.getCell().getMathlabFile());

                        command.getBacteria_speed().add(item.getCell().getSpeed());

                        command.getR_search().add(item.getCell().getSearchRadius());

                        command.getT_survive().add(item.getCell().getSurviveTime());

                        command.getBacteria_color().add(new Color(
                                Integer.parseInt(item.getCell().getCellColor().split(",")[0]),//R
                                Integer.parseInt(item.getCell().getCellColor().split(",")[1]),//G
                                Integer.parseInt(item.getCell().getCellColor().split(",")[2]) //B
                        ));

                        //linha 1225
                        item.getMetabolites().forEach(met -> {

                            ArrayList<String> al = new ArrayList<String>();
                            al.add(met.getReactionName());
                            command.getEx_rxns_name().add(al);

                            ArrayList<Integer> al2 = new ArrayList<Integer>();
                            al2.add(met.getReactionDirection());
                            command.getEx_rxns_direction().add(al2);
                        });

                        //linha 1125
                        command.setTickslimit(simulationRunCommand.getTimeLimit());

                        command.setTickTime(simulationRunCommand.getTimeLimit());

                        command.setL(simulationRunCommand.getEnvironmentLength());

                        command.setD(simulationRunCommand.getEnvironmentDepth());

                        command.setW(simulationRunCommand.getEnvironmentWidth());

                        command.setN_real(simulationRunCommand.getMetaboliteScale() * Math.pow(10, simulationRunCommand.getMetaboliteScaleMult()));
                    }
            );
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return command;
    }
}
