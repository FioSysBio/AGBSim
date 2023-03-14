package br.fiocruz.procc.acbmservice.commands;

import br.fiocruz.procc.acbmservice.domain.Entity;
import br.fiocruz.procc.acbmservice.domain.Environment;
import lombok.Data;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Data
public class EnvironmentCommand {

    //Constructor
    public EnvironmentCommand() {
        super();
    	mesh = new int[L][D][W];
    }

    //environment mesh
    private int[][][] mesh;

    //create lists of objects
    private List<Entity> bacterias = new ArrayList<Entity>();

    private List<Entity> PS = new ArrayList<Entity>();

    private List<Entity> ANT = new ArrayList<Entity>();

    private List<Number> ProdAmount = new ArrayList<Number>();

    //bacteria and metabolite properties:
    private ArrayList<String> bacteria_name = new ArrayList<String>();

    private ArrayList<Integer> bacteria_count = new ArrayList<Integer>();

    private ArrayList<Integer> bacteria_died = new ArrayList<Integer>();

    private ArrayList<Double> bacteria_conc = new ArrayList<Double>();

    private ArrayList<Integer> bacteria_scale = new ArrayList<Integer>();

    private ArrayList<Double> r_bac = new ArrayList<Double>();

    private ArrayList<Double> l_bac = new ArrayList<Double>();

    private ArrayList<Double> v_bac = new ArrayList<Double>();

    private ArrayList<Double> m_bac = new ArrayList<Double>();

    private ArrayList<String> mFile = new ArrayList<String>();

    private ArrayList<Integer> bacteria_speed = new ArrayList<Integer>();

    private ArrayList<Integer> t_survive = new ArrayList<Integer>();

    private ArrayList<Integer> r_search = new ArrayList<Integer>();

    private ArrayList<String> metabolite_name = new ArrayList<String>();

    private ArrayList<Integer> metabolite_count = new ArrayList<Integer>();

    private ArrayList<Double> metabolite_conc = new ArrayList<Double>();

    private ArrayList<Integer> metabolite_index = new ArrayList<Integer>();

    private ArrayList<Double> metabolite_mw = new ArrayList<Double>();

    private ArrayList<Integer> metabolite_speed = new ArrayList<Integer>();

    private ArrayList<Double> metabolite_uub = new ArrayList<Double>();

    private ArrayList<ArrayList<String>> ex_rxns_name = new ArrayList<ArrayList<String>>();

    private ArrayList<ArrayList<Integer>> ex_rxns_direction = new ArrayList<ArrayList<Integer>>();

    private ArrayList<ArrayList<Integer>> substrate = new ArrayList<ArrayList<Integer>>();

    private ArrayList<Double> eat_radius = new ArrayList<Double>();

    private ArrayList<Color> bacteria_color = new ArrayList<Color>();

    private ArrayList<Color> metabolite_color = new ArrayList<Color>();

    private ArrayList<Integer[]> feeding_points = new ArrayList<Integer[]>();

    private boolean stirredFeed;


    //Variable for counting time
    private int ticks;

    //Variable for stop program at this time
    private int tickslimit;

    private int death;

    private int notEat = 0;

    private ArrayList<Integer> notEat1 = new ArrayList<Integer>();


    //dimension informations
    //length of the artificial reactor [mkm]
    private int L ;

    //width of the artificial reactor [mkm]
    private int W;

    //depth of artificial reactor [mkm]
    private int D;

    //number of minuets in 1 program's tick [min]
    private int tickTime;
    //number of tick in 1 hour [ticks]

    public static EnvironmentCommand convert (Environment env) {

        EnvironmentCommand command = new EnvironmentCommand();

//        BeanUtils.copyProperties(environment, command);
        command.bacteria_name = env.getBacteria_name();
        command.bacteria_count = env.getBacteria_count();
        command.bacteria_conc = env.getBacteria_conc();
        command.bacteria_scale = env.getBacteria_scale();
        command.r_bac = env.getR_bac();
        command.l_bac = env.getL_bac();
        command.v_bac = env.getV_bac();
        command.m_bac = env.getM_bac();
        command.eat_radius = env.getEat_radius();
        command.mFile = env.getMFile();
        command.bacteria_speed = env.getBacteria_speed();
        command.t_survive = env.getT_survive();
        command.r_search = env.getR_search();
        command.metabolite_name = env.getMetabolite_name();
        command.metabolite_count = env.getMetabolite_count();
        command.metabolite_conc = env.getMetabolite_conc();
        command.metabolite_mw = env.getMetabolite_mw();
        command.metabolite_speed = env.getMetabolite_speed();
        command.metabolite_uub = env.getMetabolite_uub();
        command.ex_rxns_name = env.getEx_rxns_name();
        command.ex_rxns_direction = env.getEx_rxns_direction();
        command.tickslimit = env.getTickslimit();
        command.tickTime = env.getTickTime();
        command.L = env.getL();
        command.D = env.getD();
        command.W = env.getW();
        command.bacteria_color = env.getBacteria_color();
        command.metabolite_color = env.getMetabolite_color();
        command.feeding_points = env.getFeeding_points();
        command.stirredFeed = env.isStirredFeed();

        return command;
    }
}
