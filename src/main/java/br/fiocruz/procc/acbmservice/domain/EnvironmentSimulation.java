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
import jep.Interpreter;
import jep.JepException;
import jep.NDArray;
import jep.SharedInterpreter;
import lombok.Data;
import org.springframework.core.io.ClassPathResource;


@Data
public class EnvironmentSimulation {

    //Constructor
    public EnvironmentSimulation(String pathLogFile, String pathSubstrateScript, String pathEatScript) {
        super();
    	mesh = new int[L][D][W];
        qtdTicks = 0;
        this.logFile =  new File(pathLogFile);
        this.pathSubstrateScript = pathSubstrateScript;
        this.pathEatScript = pathEatScript;
    }

    private String pathSubstrateScript;

    private String pathEatScript;

    private Integer qtdTicks;

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

//    private ArrayList<Integer> doubling_time = new ArrayList<Integer>();

    
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

    //dimension
    //length of the artificial reactor [px]
    private int dimX = 1000;

    //length of the artificial reactor [mkm]
    private int L ;

    //width of the artificial reactor [px]
    private int dimY = 400;

    //width of the artificial reactor [mkm]
    private int W;

    //depth of artificial reactor [mkm]
    private int D;

    //volume of the reactor [l]
    private double V = L * D * W * Math.pow(10, -15);

    //number of mkm in 1 px [mkm/px]
    private double tickX;

    private double tickY;

    //number of minuets in 1 program's tick [min]
    private int tickTime;

    //number of tick in 1 hour [ticks]
    private double norm;

    //Variable for eating
    //period of eating [hours]
    //private int TickEat = (int) (1*norm);

    //time of last eating
    private int ticksEatTime = 1;

    //period of antibiotic getting
    private int antibioticPeriod;

    private int ticksAntTime;

    //amount of days in which get antibiotic
    private int antibioticsDay;

    //helpful counter: count antibiotics days
    private int t;

    private Double bacteria_n_real;

    private Double bacteria_n_a;

    //Variable for reading value of variable from external file
    private Properties props = new Properties() ;

    //Variable for writing output in a file
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
    
    public void setParameters (EnvironmentCommand command) {

    	this.bacteria_name = command.getBacteria_name();
    	this.bacteria_count = command.getBacteria_count();
    	this.bacteria_conc = command.getBacteria_conc();
    	this.bacteria_scale = command.getBacteria_scale();
//    	Environment.doubling_time = RunWindow.doubling_time;
    	this.r_bac = command.getR_bac();
    	this.l_bac = command.getL_bac();
    	this.v_bac = command.getV_bac();
    	this.m_bac = command.getM_bac();
    	this.eat_radius = command.getEat_radius();
    	this.mFile = command.getMFile();
    	this.bacteria_speed = command.getBacteria_speed();
    	this.t_survive = command.getT_survive();
    	this.r_search = command.getR_search();
    	this.metabolite_name = command.getMetabolite_name();
    	this.metabolite_count = command.getMetabolite_count();
    	this.metabolite_conc = command.getMetabolite_conc();
    	this.metabolite_mw = command.getMetabolite_mw();
    	this.metabolite_speed = command.getMetabolite_speed();
    	this.metabolite_uub = command.getMetabolite_uub();
    	this.ex_rxns_name = command.getEx_rxns_name();
    	this.ex_rxns_direction = command.getEx_rxns_direction();
    	this.tickslimit = command.getTickslimit();
    	this.tickTime = command.getTickTime();
    	this.L = command.getL();
    	this.D = command.getD();
    	this.W = command.getW();
        this.mesh = new int[L][D][W];
    	this.bacteria_color = command.getBacteria_color();
    	this.metabolite_color = command.getMetabolite_color();
    	this.feeding_points = command.getFeeding_points();
    	this.stirredFeed = command.isStirredFeed();
        this.bacteria_n_real = command.getBacteria_n_real();
        this.bacteria_n_a = command.getBacteria_n_a();

    	for (int i = 0; i < bacteria_name.size(); i++) {
			bacteria_died.add(0);
		}

    	norm = 60.0 / tickTime;
    	tickX = (double) L / dimX;
    	tickY = (double) W / dimY;

    	V = W * D * L * Math.pow(10, -15);

        ClassPathResource resource = new ClassPathResource("substrateFinder_v2.py");

    	for (int i = 0; i < bacteria_name.size(); i++) {
			try {
				this.substrate.add(substrateFinder(ex_rxns_name.get(i), ex_rxns_direction.get(i), mFile.get(i), pathSubstrateScript, pathEatScript));
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
				Bacteria b = new Bacteria(new Random().nextInt(L), new Random().nextInt(W), new Random().nextInt(D), i, this);
				b.setTimeEat(0);
				bacterias.add(b);
			}
		}


        //add metabolites
    	if (stirredFeed) {
        	for (int i = 0; i < metabolite_count.size(); i++) {
    			for (int j = 0; j < metabolite_count.get(i); j++) {
    				PolySaccharides p = new PolySaccharides(new Random().nextInt(L), new Random().nextInt(W), new Random().nextInt(D), i, this);
    				p.setSpeed(metabolite_speed.get(i));
    				PS.add(p);
    			}
    		}
		} else {
	    	for (int k = 0; k < feeding_points.size(); k++) {
	    		for (int i = 0; i < metabolite_count.size(); i++) {
	    			for (int j = 0; j < metabolite_count.get(i)/feeding_points.size(); j++) {
	    				PolySaccharides p = new PolySaccharides(
                                (int) (feeding_points.get(k)[0] + new Random().nextGaussian() * 10),
                                (int) (feeding_points.get(k)[1] + new Random().nextGaussian()*10),
                                (int) (feeding_points.get(k)[1] + new Random().nextGaussian() * 10), i,
                                this);
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
		    		Bacteria b = new Bacteria(new Random().nextInt(L), new Random().nextInt(W), new Random().nextInt(D), i, this);
		    		b.setMass(m_dummy);
		    		b.setTimeEat(this.ticks + 1);
		    	    bacterias.add(b);
		    	    this.bacteria_count.set(i, this.bacteria_count.get(i) +1 );
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
			double c = (metabolite_mass.get(i)/ this.bacteria_n_real) * metabolite_mw.get(i) / V;

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
            if (! entity.isLive()){
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
//            System.exit(0);
            writeFile.close();
        }
    }

    public ArrayList<Integer> substrateFinder( ArrayList<String> exRxnsName, ArrayList<Integer> exRxnsDirection, String mFileName, String pathSubstrateScript, String pathEatsScript ) throws JepException {

        ArrayList<Integer> substrates = new ArrayList<>();

        Interpreter interp = null;
        try {

            interp = new SharedInterpreter();

            String[] exRxnsArray = new String[exRxnsName.size()];
            for (int i = 0; i < exRxnsName.size(); i++) {
                exRxnsArray[i] = exRxnsName.get(i);
            }

            int[] exDirsArray = new int[exRxnsDirection.size()];
            for (int i = 0; i < exRxnsDirection.size(); i++) {
                exDirsArray[i] = exRxnsDirection.get(i);
            }

            String scriptSubstrateFinder = "/files_simulation/substrateFinder_v2.py";
//			String scriptSubstrateFinder = "./src/main/resources/static/substrateFinder_v2.py";
//			String scriptSubstrateFinder = pathSubstrateScript;

            //metabolites, directions, metabolic_model - parametros dentro do script que precisam ser settados
            interp.set("reactions", exRxnsArray);
            interp.set("directions", exDirsArray);
            String ext = ".json";
            interp.set("metabolic_model", mFileName + ext);

            interp.runScript(scriptSubstrateFinder);
            double[] result = (double[]) ((NDArray) interp.getValue("result")).getData();

            Arrays.stream(result).forEach(System.out::println);

            double[] fluxArray = result;

            for (int i = 0; i < fluxArray.length; i++) {
                if (fluxArray[i] > 0) {
                    int index = 0;
                    for (int j = 0; j < substrates.size(); j++) {
                        if ( fluxArray[i] < fluxArray[substrates.get(j)] ) {
                            index = j+1;
                        }
                    }
                    substrates.add(index, i);
                }
            }

        } catch (JepException exc) {
            exc.printStackTrace();
        } finally {
            if(interp != null) {
                interp.close();
            }
        }

        return substrates;
    }
}
