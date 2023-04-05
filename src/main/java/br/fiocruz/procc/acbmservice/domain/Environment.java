package br.fiocruz.procc.acbmservice.domain;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.*;
import java.util.List;

import br.fiocruz.procc.acbmservice.commands.EnvironmentCommand;
import jep.JepException;
import lombok.Getter;
import lombok.Setter;
import matlabcontrol.MatlabConnectionException;
import matlabcontrol.MatlabInvocationException;


@Getter
@Setter
public class Environment {

    //Constructor
    public Environment(String pathLogFile) {
        super();
    	mesh = new int[L][D][W];

        this.logFile =  new File(pathLogFile);
    }
    
    //environment mesh
    private int[][][] mesh;

    //create lists of objects
    private List<Entity> bacterias = new ArrayList<Entity>();

    private List<Entity> PS = new ArrayList<Entity>();

    private List<Entity> ANT = new ArrayList<Entity>();

    private List<Number> ProdAmount = new ArrayList<Number>();

    //bacteria and metabolite properties:    
    
    @Getter
    static ArrayList<String> bacteria_name = new ArrayList<String>();

    @Getter
    static ArrayList<Integer> bacteria_count = new ArrayList<Integer>();

    @Getter
    static ArrayList<Integer> bacteria_died = new ArrayList<Integer>();

    @Getter
    static ArrayList<Double> bacteria_conc = new ArrayList<Double>();

//    static ArrayList<Integer> doubling_time = new ArrayList<Integer>();

    @Getter
    static ArrayList<Integer> bacteria_scale = new ArrayList<Integer>();

    @Getter
    static ArrayList<Double> r_bac = new ArrayList<Double>();

    @Getter
    static ArrayList<Double> l_bac = new ArrayList<Double>();

    @Getter
    static ArrayList<Double> v_bac = new ArrayList<Double>();

    @Getter
    static ArrayList<Double> m_bac = new ArrayList<Double>();

    @Getter
    static ArrayList<String> mFile = new ArrayList<String>();

    @Getter
    static ArrayList<Integer> bacteria_speed = new ArrayList<Integer>();

    @Getter
    static ArrayList<Integer> t_survive = new ArrayList<Integer>();

    @Getter
    static ArrayList<Integer> r_search = new ArrayList<Integer>();

    @Getter
    static ArrayList<String> metabolite_name = new ArrayList<String>();

    @Getter
    static ArrayList<Integer> metabolite_count = new ArrayList<Integer>();
    
    @Getter
    static ArrayList<Double> metabolite_conc = new ArrayList<Double>();

    @Getter
    static ArrayList<Integer> metabolite_index = new ArrayList<Integer>();

    @Getter
    static ArrayList<Double> metabolite_mw = new ArrayList<Double>();

    @Getter
    static ArrayList<Integer> metabolite_speed = new ArrayList<Integer>();

    @Getter
    static ArrayList<Double> metabolite_uub = new ArrayList<Double>();

    @Getter
    static ArrayList<ArrayList<String>> ex_rxns_name = new ArrayList<ArrayList<String>>();

    @Getter
    static ArrayList<ArrayList<Integer>> ex_rxns_direction = new ArrayList<ArrayList<Integer>>();

    @Getter
    static ArrayList<ArrayList<Integer>> substrate = new ArrayList<ArrayList<Integer>>();

    @Getter
    static ArrayList<Double> eat_radius = new ArrayList<Double>();

    @Getter
    static ArrayList<Color> bacteria_color = new ArrayList<Color>();

    @Getter
    static ArrayList<Color> metabolite_color = new ArrayList<Color>();

    @Getter
    static ArrayList<Integer[]> feeding_points = new ArrayList<Integer[]>();

    @Getter
    static boolean stirredFeed;


    //Variable for counting time
    @Getter
    static int ticks;
    //Variable for stop program at this time

    @Getter
    static int tickslimit;

    static int death;
    static int notEat = 0;
    static ArrayList<Integer> notEat1 = new ArrayList<Integer>();

    
    
    //dimension
    //length of the artificial reactor [px]
    @Setter@Getter
    private static int dimX = 1000;

    //length of the artificial reactor [mkm]
    @Setter@Getter
    private static int L ;

    //width of the artificial reactor [px]
    @Setter@Getter
    private static int dimY = 400;

    //width of the artificial reactor [mkm]
    @Setter@Getter
    private static int W;

    //depth of artificial reactor [mkm]
    @Setter@Getter
    private static int D;

    //volume of the reactor [l]
    private static double V = L * D * W * Math.pow(10, -15);

    //number of mkm in 1 px [mkm/px]
    @Setter@Getter
    private static double tickX;

    @Setter@Getter
    private static double tickY;

    //number of minuets in 1 program's tick [min]
    @Setter@Getter
    private static int tickTime;

    //number of tick in 1 hour [ticks]
    @Setter@Getter
    private static double norm;

    //Variable for eating
    //period of eating [hours]
//    static int TickEat = (int) (1*norm);

    //time of last eating
    static int ticksEatTime = 1;

    //period of antibiotic getting
    static int antibioticPeriod;

    static int ticksAntTime;

    //amount of days in which get antibiotic
    static int antibioticsDay;

    //helpful counter: count antibiotics days
    static int t;

    //Variable for reading value of variable from external file
    static Properties props = new Properties() ;

    //Variable for writing output in a file
    @Setter@Getter
    File logFile;

    PrintWriter writeFile = null;
    
    public void initialize () {    
    	ticks = -1;
        for (int i = 0; i < metabolite_count.size(); i++) {
			metabolite_index.add(0);
		}

        //create new output file
//        logFile =  new File("/files_simulation/output.txt");
    }
    
    public static void setParameters (EnvironmentCommand command) {

    	Environment.bacteria_name = command.getBacteria_name();
    	Environment.bacteria_count = command.getBacteria_count();
    	Environment.bacteria_conc = command.getBacteria_conc();
    	Environment.bacteria_scale = command.getBacteria_scale();
//    	Environment.doubling_time = RunWindow.doubling_time;
    	Environment.r_bac = command.getR_bac();
    	Environment.l_bac = command.getL_bac();
    	Environment.v_bac = command.getV_bac();
    	Environment.m_bac = command.getM_bac();
    	Environment.eat_radius = command.getEat_radius();
    	Environment.mFile = command.getMFile();
    	Environment.bacteria_speed = command.getBacteria_speed();
    	Environment.t_survive = command.getT_survive();
    	Environment.r_search = command.getR_search();
    	Environment.metabolite_name = command.getMetabolite_name();
    	Environment.metabolite_count = command.getMetabolite_count();
    	Environment.metabolite_conc = command.getMetabolite_conc();
    	Environment.metabolite_mw = command.getMetabolite_mw();
    	Environment.metabolite_speed = command.getMetabolite_speed();
    	Environment.metabolite_uub = command.getMetabolite_uub();
    	Environment.ex_rxns_name = command.getEx_rxns_name();
    	Environment.ex_rxns_direction = command.getEx_rxns_direction();
    	Environment.tickslimit = command.getTickslimit();
    	Environment.tickTime = command.getTickTime();
    	Environment.L = command.getL();
    	Environment.D = command.getD();
    	Environment.W = command.getW();
    	Environment.bacteria_color = command.getBacteria_color();
    	Environment.metabolite_color = command.getMetabolite_color();
    	Environment.feeding_points = command.getFeeding_points();
    	Environment.stirredFeed = command.isStirredFeed();
        Bacteria.n_real = command.getN_real(); //n_real = Double.parseDouble(metScaleField1.getText()) * Math.pow(10, Integer.parseInt(metScaleField2.getText()));

    	for (int i = 0; i < bacteria_name.size(); i++) {
			bacteria_died.add(0);
		}

    	norm = 60.0 / tickTime;
    	tickX = (double) L / dimX;
    	tickY = (double) W / dimY;

    	V = W * D * L * Math.pow(10, -15);
    	
    	for (int i = 0; i < bacteria_name.size(); i++) {
			try {
				Environment.substrate.add(Bacteria.substrateFinder(ex_rxns_name.get(i), ex_rxns_direction.get(i), mFile.get(i)));
			} catch (JepException e) {
				e.printStackTrace();
			}
        }
    	
    }

    //add objects to the environment
    public void addEntity()  throws IOException {
    	
    	//add bacteria
    	for (int i = 0; i < bacteria_count.size(); i++) {
			for (int j = 0; j < bacteria_count.get(i); j++) {
				Bacteria b = new Bacteria(new Random().nextInt(L), new Random().nextInt(W), new Random().nextInt(D), i);
				b.setTimeEat(0);
				bacterias.add(b);
			}
		}


        //add metabolites
    	if (stirredFeed) {
        	for (int i = 0; i < metabolite_count.size(); i++) {
    			for (int j = 0; j < metabolite_count.get(i); j++) {
    				PolySaccharides p = new PolySaccharides(new Random().nextInt(L), new Random().nextInt(W), new Random().nextInt(D), i);
    				p.setSpeed(metabolite_speed.get(i));
    				PS.add(p);
    			}
    		}
		} else {
	    	for (int k = 0; k < feeding_points.size(); k++) {
	    		for (int i = 0; i < metabolite_count.size(); i++) {
	    			for (int j = 0; j < metabolite_count.get(i)/feeding_points.size(); j++) {
	    				PolySaccharides p = new PolySaccharides((int) (feeding_points.get(k)[0] + new Random().nextGaussian() * 10), (int) (feeding_points.get(k)[1] + new Random().nextGaussian()*10), (int) (feeding_points.get(k)[1] + new Random().nextGaussian() * 10), i);
	    				p.setSpeed(metabolite_speed.get(i));
	    				PS.add(p);
	    				
	    			}
	    		}
	    	}
		}


    }
    
    public void sumupMass() {
    	
	    ArrayList<Double> bacteria_mass = new ArrayList<Double>();
	    for (int i = 0; i < bacteria_name.size(); i++) {
			bacteria_mass.add(0.0);
		}
        for (Entity bacteria : bacterias) {
            Bacteria b = (Bacteria) bacteria;
            bacteria_mass.set(b.getType(), bacteria_mass.get(b.getType()) + b.mass);
        }
		for (int i = 0; i < bacteria_mass.size(); i++) {
			int count = (int) (bacteria_mass.get(i) / m_bac.get(i));
			if (count > bacteria_count.get(i)) {
		    	double m_dummy = bacteria_mass.get(i) / count;
                for (Entity bacteria : bacterias) {
                    Bacteria b = (Bacteria) bacteria;
                    if (b.getType() == i) {
                        bacteria.setMass(m_dummy);
                    }
                }
		    	for (int j = 0; j < count-bacteria_count.get(i); j++) {
		    		Bacteria b = new Bacteria(new Random().nextInt(L), new Random().nextInt(W), new Random().nextInt(D), i );
		    		b.setMass(m_dummy);
		    		b.setTimeEat(Environment.ticks + 1);
		    	    bacterias.add(b);
		    	    Environment.bacteria_count.set(i, Environment.bacteria_count.get(i) +1 );
		    	}
			}
		}
	}
    
	public void nToC() {

	    ArrayList<Double> bacteria_mass = new ArrayList<Double>();
	    for (int i = 0; i < bacteria_count.size(); i++) {
			bacteria_mass.add(0.0);
		}
        for (Entity bacteria : bacterias) {
            Bacteria b = (Bacteria) bacteria;
            bacteria_mass.set(b.getType(), bacteria_mass.get(b.getType()) + b.mass);
        }
		for (int i = 0; i < bacteria_mass.size(); i++) {
			double c = bacteria_mass.get(i)/V;

            DecimalFormatSymbols symbols = new DecimalFormatSymbols();
            symbols.setDecimalSeparator('.');
            String newNumber = new DecimalFormat("####.##", symbols).format(c);
            c = Double.parseDouble(newNumber);
			bacteria_conc.set(i, c);
		}
		
	    ArrayList<Double> metabolite_mass = new ArrayList<Double>();
	    for (int i = 0; i < metabolite_count.size(); i++) {
			metabolite_mass.add(0.0);
		}
        for (Entity entity : PS) {
            PolySaccharides p = (PolySaccharides) entity;
            metabolite_mass.set(p.getType(), metabolite_mass.get(p.getType()) + p.mass);
        }
		for (int i = 0; i < metabolite_mass.size(); i++) {
			double c = (metabolite_mass.get(i)/Bacteria.n_a) * metabolite_mw.get(i) / V;

            DecimalFormatSymbols symbols = new DecimalFormatSymbols();
            symbols.setDecimalSeparator('.');
            String newNumber = new DecimalFormat("####.##", symbols).format(c);
			c = Double.parseDouble(newNumber);
			metabolite_conc.set(i, c);
		}
	}

    //write output data in a file
    //gravar dados de saída em um arquivo
    public void createFile(List<Number> l, PrintWriter writeFile){
        StringBuilder st = new StringBuilder();
        for (Number n: l) { st.append(n).append(" "); }
        writeFile.println(st.toString().trim());
        writeFile.flush();
    }


    //draw objects and environment
    //desenhar objetos e ambiente
    public void draw(){

        //bacteria and metabolites
        //bactérias e metabólitos
        List<Entity> newList = new ArrayList<Entity>() {
            {
                addAll(bacterias);
                addAll(PS);
                addAll(ANT);
            }
        };

        for(Entity ent : newList) {
            ent.draw();
        }

    }
    
    public void fillMesh(ArrayList<Coordinates> element) {
        for (Coordinates c : element) {
            if (c.getX() >= 0 && c.getX() < L && c.getY() >= 0 && c.getY() < W && c.getZ() >= 0 && c.getZ() < D) {
                mesh[c.getX()][c.getY()][c.getZ()] = 1;
            }
        }
	}
    
    public void freeUpMesh(ArrayList<Coordinates> element) {
        for (Coordinates c : element) {
            if (c.getX() >= 0 && c.getX() < L && c.getY() >= 0 && c.getY() < W && c.getZ() >= 0 && c.getZ() < D) {
                mesh[c.getX()][c.getY()][c.getZ()] = 0;
            }
        }
	}


    //action for each component in a list
    //ação para cada componente em uma lista
    public void Action(List<Entity> Ent) {
       for (int i = Ent.size()-1; i >= 0; i--){
           Entity entity = Ent.get(i);
            try{
               entity.tick(PS, ANT,bacterias,this);
            }catch(IOException e){
                e.printStackTrace();
            }
            if (! entity.getLive()){
                Ent.remove(i);
            }
        }

    }

    public void actionCore(){
        //create LogFile and 1 string
        if (ticks == -1){
            try {
                writeFile = new PrintWriter(logFile);

                StringBuilder st = new StringBuilder("Time");

                for (String s : bacteria_name) {
                    st.append(" ").append(s).append("_Count").append(" ").append(s).append("_Conc");
                }

                for (String s : metabolite_name) {
                    st.append(" ").append(s).append("_Count").append(" ").append(s).append("_Conc");
                }

                for (String s : bacteria_name) {
                    st.append(" ").append(s).append("_Died");
                }

                st.append(" bacteria_couldn't_eat");
                writeFile.println(st);

            } catch (IOException i) {
                i.printStackTrace();
            }
        }

        //change current time
        ticks += 1;
        System.out.println("time_tick " + ticks);
        nToC();

        //create a list with output data

        ProdAmount = new ArrayList<Number> ();
        ProdAmount.add(ticks* tickTime);

        for (int i = 0; i < bacteria_count.size(); i++) {
            ProdAmount.add(bacteria_count.get(i));
            ProdAmount.add(bacteria_conc.get(i));

		}

        for (int i = 0; i < metabolite_count.size(); i++) {
            ProdAmount.add(metabolite_count.get(i));
            ProdAmount.add(metabolite_conc.get(i));

		}

        for (Integer integer : bacteria_died) {
            ProdAmount.add(integer);
        }
        ProdAmount.add(notEat);

        //write these output data in a file
        createFile(ProdAmount, writeFile);

      notEat = 0;

        //action for each objects list
        Action(bacterias);
        Action(PS);
        Action(ANT);

        if (ticks == tickslimit || bacterias.isEmpty()){
            System.exit(0);
            writeFile.close();
        }
    }
}
