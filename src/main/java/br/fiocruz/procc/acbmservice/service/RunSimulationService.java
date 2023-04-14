package br.fiocruz.procc.acbmservice.service;

import br.fiocruz.procc.acbmservice.commands.EnvironmentCommand;
import br.fiocruz.procc.acbmservice.commands.SimulationItensInfoCommand;
import br.fiocruz.procc.acbmservice.commands.SimulationRunCommand;
import br.fiocruz.procc.acbmservice.domain.Bacteria;
import br.fiocruz.procc.acbmservice.domain.Environment;
import br.fiocruz.procc.acbmservice.domain.LocalFeed;
import br.fiocruz.procc.acbmservice.domain.enuns.AmountType;
import br.fiocruz.procc.acbmservice.domain.enuns.ShapeType;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;


@Service
public class RunSimulationService {

    private static int cont = 0;

    Thread t;

    public String runSimulation(SimulationRunCommand simulationRunCommand) {

        try {

            if (simulationRunCommand.getItensSimulation() == null || simulationRunCommand.getItensSimulation().size() == 0) {
                return "Items for simulation are missing!!!";
            }

            Boolean temItens = true;
            for (SimulationItensInfoCommand item : simulationRunCommand.getItensSimulation()) {
                if (item.getCell() == null) {
                            return "Missing cells for this simulation!!!";
                }

                if (item.getMetabolites() == null || item.getMetabolites().size() == 0) {
                    return "Missing metabolites for this simulation!!!";
                }
            }

            if (simulationRunCommand.getIsLocalFeedSimulation()) {
                for (LocalFeed ilocal : simulationRunCommand.getLocalFeeds()) {
                    if (ilocal.getXFeedField() > simulationRunCommand.getEnvironmentLength() &&
                            ilocal.getYFeedField() > simulationRunCommand.getEnvironmentWidth() &&
                            ilocal.getZFeedField() > simulationRunCommand.getEnvironmentWidth() )
                    {
                        return "Coordinates for invalid launch location points, input must be within environment size. Try Again";
                    }
                }
            }

            Environment.setParameters(transform(simulationRunCommand));
//            RunWindow w = new RunWindow();
//            w.execute("/files_simulation/output_simulation_" + simulationRunCommand.getId() +  ".txt");


            this.executeEnvironmentsSimulation("/files_simulation/output_simulation_" + simulationRunCommand.getId() +  ".txt");


            System.out.println("Rodou a simulação até o final!!!");

        } catch (Exception ex) {
            ex.printStackTrace();

            return "Invalid Input. Try Again";
        }

        return "Simulation run start with sucess!";
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
                        if (item.getCell().getMass() == 0.0 || item.getCell().getMass() == null) {
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

                        //Metabolitos
                        ArrayList<String> auxList1 = new ArrayList<String>();//Lista auxiliar para vetores dos nomes das reações
                        ArrayList<Integer> auxList2 = new ArrayList<Integer>();//Lista auxiliar para vetores das direções das reações de cada metabólito
                        item.getMetabolites().forEach(met -> {
                            //linha 785
                            {
                                command.getMetabolite_name().add(met.getName());
//                                metabolite_name.add((String) metNamecomboBox.getSelectedItem());
                                if (met.getAmountType() == AmountType.GL) {
                                    command.getMetabolite_conc().add(Double.parseDouble(met.getAmount()));
                                    command.getMetabolite_count().add(-1);
                                } else {
                                    command.getMetabolite_count().add(Integer.parseInt(met.getAmount()));
                                    command.getMetabolite_conc().add(-1.0);
                                }

                                command.getMetabolite_mw().add(met.getMolarMass());

                                command.getMetabolite_color().add(
                                        new Color(
                                                Integer.parseInt(item.getCell().getCellColor().split(",")[0]),//R
                                                Integer.parseInt(item.getCell().getCellColor().split(",")[1]),//G
                                                Integer.parseInt(item.getCell().getCellColor().split(",")[2]) //B
                                        )
                                );


                                command.getMetabolite_index().add(0);

                                command.getMetabolite_speed().add(met.getSpeed());

                                command.getMetabolite_uub().add(met.getUptakeUpperBound());
                            }

                            //linha 1225
                            {
                                auxList1.add(met.getReactionName());
                                auxList2.add(met.getReactionDirection());
                            }
                        });
                        command.getEx_rxns_name().add(auxList1);

                        command.getEx_rxns_direction().add(auxList2);

                        //linha 1170
                        command.setStirredFeed(simulationRunCommand.getIsLocalFeedSimulation() ? false : true);//Caso esteja desmarcado na UI, StirredFeed será Ativado (ou seja, TRUE)

                        if (simulationRunCommand.getIsLocalFeedSimulation()) {
                            simulationRunCommand.getLocalFeeds().forEach(
                                    ilocal -> {
                                        Integer[] coord = new Integer[3];
                                        coord[0] = (ilocal.getXFeedField());
                                        coord[1] = (ilocal.getYFeedField());
                                        coord[2] = (ilocal.getZFeedField());

                                        command.getFeeding_points().add(coord);
                                    }
                            );
                        }

                        //linha 1125
                        command.setTickslimit(simulationRunCommand.getTimeLimit());

                        command.setTickTime(simulationRunCommand.getTimeStep());//na UI se chama Passos da simulação no tempo (Time Step)

                        command.setL(simulationRunCommand.getEnvironmentLength());

                        command.setD(simulationRunCommand.getEnvironmentDepth());

                        command.setW(simulationRunCommand.getEnvironmentWidth());

                        command.setN_real(simulationRunCommand.getMetaboliteScale() * Math.pow(10, simulationRunCommand.getMetaboliteScaleMult()));



                        //nToN
                        {
                            double V = command.getL() * command.getW() * command.getD() * Math.pow(10, -18);
                            for (int i = 0; i < command.getBacteria_conc().size(); i++) {
                                if (command.getBacteria_conc().get(i) != -1 ) {
//                                    System.out.println(bacteria_conc.get(i)+ ","+m_bac.get(i)+","+bacteria_scale.get(i));
                                    int n = (int) ( (command.getBacteria_conc().get(i)* 1000 * V) /( command.getM_bac().get(i) ) );
                                    command.getBacteria_count().set(i, n);
                                }
                            }

                            for (int i = 0; i < command.getMetabolite_conc().size(); i++) {
                                if (command.getMetabolite_conc().get(i) != -1 ) {
                                    int n = (int) ( ((command.getMetabolite_conc().get(i)*1000 * V) / (command.getMetabolite_mw().get(i))) * ( Bacteria.n_a / command.getN_real())  );
                                    command.getMetabolite_count().set(i, n);
                                }
                            }
                        }

                    }
            );
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return command;
    }


    public void executeEnvironmentsSimulation(String pathLogFile) {
        // create simple Environment object
        Environment e1 = new Environment(pathLogFile);
        e1.initialize();
        try {
            e1.addEntity();
        } catch (IOException e) {
            e.printStackTrace();
        }
        graphicDataGeneration(e1);
    }

    private void graphicDataGeneration(Environment environment){

        t = new Thread(new Runnable() {
            @Override
            public void run() {
                cont++;
                int num = cont;
                while( true ) {

                    try {

                        Color c1 = new Color(235, 245, 255);
                        Color st = new Color(0, 78, 152);

                        //TAMANHO DO QUADRO: 0, 0, environment.getDimX() , environment.getDimY()
                        // move and draw object
                        environment.draw();
                        environment.actionCore();

                        if (environment.getTicks() == environment.getTickslimit() || environment.getBacterias().isEmpty()){

                            System.out.println("Simulação " + num + " terminou com sucesso!");

                            return;
                        }

                        // TEXTO PARA MOSTRAR NO RESULTADO("time:  " + environment.ticks*environment.getTickTime() + " min", environment.getDimX() + 25, 25);
                        System.out.println("time: " + environment.getTicks() * environment.getTickTime() + " min");

                        for (int i = 0; i < environment.getBacteria_name().size(); i++) {
                            Color c = new Color(environment.getBacteria_color().get(i).getRed(), environment.getBacteria_color().get(i).getGreen(), environment.getBacteria_color().get(i).getBlue());
                            if (environment.getL_bac().get(i) == 0) {
                                System.out.println("Desenho Oval: "
                                        + "Coord x1: " + environment.getDimX() + 25
                                        + " / Coord y1" + 25 * ( i + 2 )
                                        + " / Largura: " + 10
                                        + " / Altura: " + 10
                                        + " / Cor RGB: " + c.getRGB()
                                        + " / Cor:  R=" + c.getRed() + ", G=" + c.getGreen() + ", B=" + c.getBlue()
                                );
                            } else {
                                System.out.println("Desenho Retângulo Arredondado: "
                                        + "Coord x1: " + environment.getDimX() + 25
                                        + " / Coord y1" + 25 * ( i + 2 )
                                        + " / Largura: " + 12
                                        + " / Altura: " + 6
                                        + "/ Largura ARC: " + 80
                                        + " / Altura ARC: " + 100
                                        + " / Cor RGB: " + c.getRGB()
                                        + " / Cor:  R=" + c.getRed() + ", G=" + c.getGreen() + ", B=" + c.getBlue()
                                );
                            }
                            System.out.println(environment.getBacteria_name().get(i));
                            System.out.println(environment.getBacteria_conc().get(i).toString() + " (g/L)");
                        }

                        int offset = (environment.getBacteria_name().size()+2)*25+8;

                        for (int i = 0; i < environment.getMetabolite_name().size(); i++) {
                            Color c = new Color(environment.getMetabolite_color().get(i).getRed(), environment.getMetabolite_color().get(i).getGreen(), environment.getMetabolite_color().get(i).getBlue());
                            System.out.println("Desenho Retângulo: "
                                    + "Coord x1: " + environment.getDimX() + 29
                                    + " / Coord y1" + offset + 25 * ( i + 1 )
                                    + " / Largura: " + 2
                                    + " / Altura: " + 2
                            );
                            System.out.println(environment.getMetabolite_name().get(i));
                            System.out.println(environment.getMetabolite_conc().get(i).toString() + " (g/L)");
                        }

                        Thread.yield();

                    }
                    finally {

                    }
                }
            }
        });

        t.start();
    }
}
