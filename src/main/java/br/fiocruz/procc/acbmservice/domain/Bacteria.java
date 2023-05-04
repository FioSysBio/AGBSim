package br.fiocruz.procc.acbmservice.domain;

import java.awt.*;
import java.io.IOException;
import java.util.*;
import java.util.List;

import jep.Interpreter;
import jep.JepException;
import jep.NDArray;
import jep.SharedInterpreter;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Bacteria extends Entity {//VAI SER TRANSFORMADO EM SERVICE

    //Constructor
    public Bacteria (int x, int y,int z, int type){
        super(x, y, z);
        this.type = type;
        SetProperty();
        setMass(m_avg);
        for (int i = 0; i <= EnvironmentSimulation.metabolite_count.size(); i++) {
			produce_fluxes.add(0.0);
		}
    }

    //variable
    
    //bacteria name
    private String name;

	private int type;

	//doubling time
    private int t_d;

	//average radius of one bacterium [mkm]
    private double r_bac;

	//average length of one bacterium [mkm]
    private double l_bac;

	//average volume of one bacterium [m^3]
    private double v_bac;

	//average mass of one bacterium [g]
    private double m_avg;

	//metabolites can be used as substrate
    private ArrayList<Integer> growth_substrate;
    
    
    //time to degradable one metabolite and produce another [ticks]
    private double degradableEat;

	//time, when bacteria eats some metabolite [ticks]
    private int TimeEat;

	//time when bacteria dies
    private int TimeDeath;

	//bacteria can sensitive something at this distance [mkm]
    private double searchRadius;

	//bacteria can eat something at this distance [mkm]
    private double eatRadius;

	//period of time,during which bacteria can live without eat
    private double eat_rate;

	//random period of time,during which bacteria can live without eat;
    //this value has chosen depended on Gaussian distribution witch mean value equals eat_rate and sigma = 1/3*eat_rate
    private double eat_range;

    //variable of count antibiotics within bacteria
    private int Antibiotic;


    //Avogadro number
    public static final double n_a = 6.022 * Math.pow(10, 23);

	//one molecule in simulations represent this amount in reality
    public static double n_real = 1.23 * Math.pow(10, 11);
    
    private ArrayList<String> exRxnsName;

	private ArrayList<Integer> exRxnsDirection;

	private String mFileName;
    
    ArrayList<Double> produce_fluxes = new ArrayList<>();

	public void setEat_radius(double eat_radius) { this.eatRadius = eat_radius * Math.max(r_bac, l_bac); }

	public void setTimeDeath(){
        this.TimeDeath = EnvironmentSimulation.ticks + 10*t_d;
    }

    public void setAntibiotic () {this.Antibiotic += 1;}

    //set value of bacteria variable
    public void SetProperty() {
        setSizeX(5);
        setName(EnvironmentSimulation.bacteria_name.get(type));
        setT_d(1);
        setR_bac(EnvironmentSimulation.r_bac.get(type));
        setL_bac(EnvironmentSimulation.l_bac.get(type));
        setV_bac(EnvironmentSimulation.v_bac.get(type));
        setM_avg(EnvironmentSimulation.m_bac.get(type));
        setColor(EnvironmentSimulation.bacteria_color.get(type).getRed(), EnvironmentSimulation.bacteria_color.get(type).getGreen(), EnvironmentSimulation.bacteria_color.get(type).getBlue());
        setSpeed(EnvironmentSimulation.bacteria_speed.get(type));
        setEat_radius(EnvironmentSimulation.eat_radius.get(type));
        setMFileName(EnvironmentSimulation.mFile.get(type));
        setExRxnsName(EnvironmentSimulation.ex_rxns_name.get(type));
        setExRxnsDirection(EnvironmentSimulation.ex_rxns_direction.get(type));
        setEat_range(EnvironmentSimulation.t_survive.get(type));
        setSearchRadius(EnvironmentSimulation.r_search.get(type));
        setGrowth_substrate(EnvironmentSimulation.substrate.get(type));
        RandomDie();
    }


    //draw bacteria in the environment
	//desenhar objetos e ambiente
//    public void draw(Graphics g){
//        Graphics2D g2d = (Graphics2D) g;
//        g.setColor(new Color(getColor_r(), getColor_g(), getColor_b()));
//
//        if (l_bac == 0) {
//        	g2d.fillOval((int) (getX() / Environment.getTickX()), (int) (getY() / Environment.getTickY()), 2*getSizeX(), 2*getSizeX());
//		} else {
//	        g2d.fillRoundRect((int) (getX() / Environment.getTickX()), (int) (getY() / Environment.getTickY()), 2*getSizeX()+2, getSizeX()+1, 80, 100);
//		}
//
//    }
	public void draw(){
//        Graphics2D g2d = (Graphics2D) g;
        Color color = new Color(getColor_r(), getColor_g(), getColor_b());

        if (l_bac == 0) {
			System.out.println("Desenho Oval: "
					+ "Coord x1: " + ((int) (getX() / EnvironmentSimulation.getTickX())
					+ " / Coord y1" + (int) (getY() / EnvironmentSimulation.getTickY())
					+ " / Largura: " + 2 * getSizeX()
					+ " / Altura: " + 2 * getSizeX())
					+ " / Cor RGB: " + color.getRGB()
					+ " / Cor:  R=" + color.getRed() + ", G=" + color.getGreen() + ", B=" + color.getBlue()
			);
		} else {
			System.out.println("Desenho Retângulo Arredondado: "
					+ "Coord x1: " + ((int) (getX() / EnvironmentSimulation.getTickX())
					+ " / Coord y1" + (int) (getY() / EnvironmentSimulation.getTickY())
					+ " / Largura: " + 2 * getSizeX() + 2
					+ " / Altura: " + getSizeX() + 1
					+ "/ Largura ARC: " + 80
					+ " / Altura ARC: " + 100)
					+ " / Cor RGB: " + color.getRGB()
					+ " / Cor:  R=" + color.getRed() + ", G=" + color.getGreen() + ", B=" + color.getBlue()
			);
		}

    }

	public void saveToDraw(){
//        Graphics2D g2d = (Graphics2D) g;
        Color color = new Color(getColor_r(), getColor_g(), getColor_b());//REVER A COR QUE VAIR VIR DA INTERFACE

        if (l_bac == 0) {
			System.out.println("Desenho Oval: "
					+ "Coord x1: " + ((int) (getX() / EnvironmentSimulation.getTickX())
					+ " / Coord y1" + (int) (getY() / EnvironmentSimulation.getTickY())
					+ " / Largura: " + 2 * getSizeX()
					+ " / Altura: " + 2 * getSizeX())
					+ " / Cor RGB: " + color.getRGB()
					+ " / Cor:  R=" + color.getRed() + ", G=" + color.getGreen() + ", B=" + color.getBlue()
			);
		} else {
			System.out.println("Desenho Retângulo Arredondado: "
					+ "Coord x1: " + ((int) (getX() / EnvironmentSimulation.getTickX())
					+ " / Coord y1" + (int) (getY() / EnvironmentSimulation.getTickY())
					+ " / Largura: " + 2 * getSizeX() + 2
					+ " / Altura: " + getSizeX() + 1
					+ "/ Largura ARC: " + 80
					+ " / Altura ARC: " + 100)
					+ " / Cor RGB: " + color.getRGB()
					+ " / Cor:  R=" + color.getRed() + ", G=" + color.getGreen() + ", B=" + color.getBlue()
			);
		}

    }
    
    public int scan(List<Entity> PS, double R, int type ) {
        double distance_min = R*R;
        int index_min = -1;
        
        for (int j = 0; j < PS.size(); j++) {
            PolySaccharides ps = (PolySaccharides) PS.get(j);
            double distance  = Math.pow((ps.getX() - getX()),2) + Math.pow((ps.getY() - getY()), 2) + Math.pow((ps.getZ() - getZ()), 2);
            if ( distance <= distance_min && ps.getType() == type ){
                    distance_min = distance;
                    index_min = PS.indexOf(ps);
                }
        }

        return (index_min);
    }
    

    //calculate direct vector to eat with index i
    public void direct(List<Entity> l, int i){
        //direct vector
        double a_x ;
        double a_y ;
        double a_z;
            a_x = getX() - l.get(i).getX();
            a_y = getY() - l.get(i).getY();
            a_z = getZ() - l.get(i).getZ();

            double a_mod = Math.sqrt(a_x*a_x + a_y*a_y + a_z*a_z);
            if (a_mod!=0){
                setDx(- a_x / a_mod);
                setDy(- a_y / a_mod);
                setDz(- a_z / a_mod);
            }
    }

    //bacteria change position
    public void Move(){

        setStepX(getStepX() + getSpeed()/ EnvironmentSimulation.getNorm() * getDx());
        setStepY(getStepY() + getSpeed()/ EnvironmentSimulation.getNorm() * getDy());
        setStepZ(getStepZ() + getSpeed()/ EnvironmentSimulation.getNorm() * getDz());
        
    }

    //void to calculate  distance between two objects and compare it with set distance
    public boolean CheckDistance(double x1, double x2, double y1, double y2, double z1, double z2, double d){

          double distance  = Math.sqrt(Math.pow((x1 - x2),2) + Math.pow((y1 - y2), 2) + Math.pow((z1 - z2), 2));

          return (distance <= d) ;

    }

    //check transaction trajectories of two object (bacteria and eat);
    // return true if trajectories transaction
    boolean transaction (double ax1, double ay1, double ax2, double ay2,
                         double bx1, double by1, double bx2, double by2)
    {
        double v1 = (bx2-bx1) * (ay1-by1) - (by2-by1) * (ax1-bx1);
        double v2 = (bx2-bx1) * (ay2-by1) - (by2-by1) * (ax2-bx1);
        double v3 = (ax2-ax1) * (by1-ay1) - (ay2-ay1) * (bx1-ax1);
        double v4 = (ax2-ax1) * (by2-ay1) - (ay2-ay1) * (bx2-ax1);
        return ((v1 * v2 <= 0) && (v3 * v4 <= 0));
    }



    //set period when bacteria must eat
    public void RandomDie() {

        //setEat_rate(new Random().nextInt((int) (10 * getEat_range())));
        setEat_rate(new Random().nextGaussian()*getEat_range()/5+getEat_range());

    }


    //set live false if bacteria didn't eat during set period
    public void CheckTime(){
		if (EnvironmentSimulation.ticks > (int) (getTimeEat() + getEat_rate())) {
			setLive(false);
			EnvironmentSimulation.bacteria_count.set(this.type, EnvironmentSimulation.bacteria_count.get(this.type) - 1);
			EnvironmentSimulation.bacteria_died.set(this.type, EnvironmentSimulation.bacteria_died.get(this.type) + 1);
		}
    }
    
    public void localConcentration() {

	}

    //calculate flux from number of metabolites
    public Object[] nToV(List<Entity> PS, List<Integer> l) {
		double m_t = 0.0;
		double c = 0.0;
		double x = 0.0;
		double v = 0.0;
		double v2 = 0.0;
		int index = -1;
		PolySaccharides pol = (PolySaccharides) PS.get(l.get(0));
		while (v2 <= EnvironmentSimulation.metabolite_uub.get(pol.getType()) && index < l.size()) {
			index++;
			v = v2;
			if (index < l.size()) {
				m_t += PS.get(l.get(index)).getMass();
				c = (m_t/n_a) / ( Math.pow(eatRadius, 3)* getV_bac() ); // unit = [mol/m^3]
				x = 0.33*mass / ( Math.pow(eatRadius, 3)* getV_bac() );
				v2 = c/(x*(t_d/ EnvironmentSimulation.getNorm())) *Math.pow(10, 3); //unit = [mmol/gr.hr]
			}
		}

		if (v == 0.0) {
			v = EnvironmentSimulation.metabolite_uub.get(pol.getType());
			x = 0.33*mass/( Math.pow(eatRadius, 3)* getV_bac() );
			c = v*(x*(t_d/ EnvironmentSimulation.getNorm()))*Math.pow(10, -3);
			index = 1;
			PolySaccharides p = new PolySaccharides((int) pol.getX(), (int) pol.getY(), (int) pol.getZ(), pol.getType());
			p.setMass(pol.getMass() - vToMass(v));
			PS.add(p);
			pol.setMass(vToMass(v));
			EnvironmentSimulation.metabolite_count.set(p.getType(), EnvironmentSimulation.metabolite_count.get(p.getType())+1);
		}


//    	System.out.println("m "+mass);
//    	System.out.println("vol "+getV_bac());
//    	System.out.println("n_t "+m_t);
//    	System.out.println("n_a "+n_a);
//		System.out.println("c "+c);
//    	System.out.println("x "+x);
//    	System.out.println("v "+v);


		return new Object[] {v,index-1, c};

	}
    
    public double vToMass(double v) {
		double x = 0.33*mass / ( Math.pow(eatRadius, 3) * getV_bac() );
		double c = v * (x* (t_d/ EnvironmentSimulation.getNorm()) ) * Math.pow(10, -3);
		double m = c * ( Math.pow(eatRadius, 3) * getV_bac() ) * n_a; //amount of metabolite produced
		return m;
	}

    public void calculateBiomass(double miu) {
		double new_mass = mass * Math.pow(Math.E, miu * t_d / EnvironmentSimulation.getNorm());
		setMass(new_mass);
		return;
	}

	public void divide(List<Entity> le1) {

		int count = (int) (getMass() / m_avg);

		double m_dummy = getMass()/count;
		this.setMass(m_dummy);
		for (int i = 0; i < count-1; i++) {
			Bacteria b = new Bacteria((int) (getX() + getSizeX()/2 * getDx()), (int) (getY() + getSizeX()/2*getDy()), (int) (getZ() + getSizeX()/2*getDz()), this.type);
			b.setMass(m_dummy);
			b.setTimeEat(EnvironmentSimulation.ticks + 1);
			le1.add(b);
			EnvironmentSimulation.bacteria_count.set(this.type, EnvironmentSimulation.bacteria_count.get(this.type) +1 );
		}
	}

    public void updateIndexes() {

		for (int i = 1; i < EnvironmentSimulation.metabolite_count.size(); i++) {
			EnvironmentSimulation.metabolite_index.set(i, EnvironmentSimulation.metabolite_index.get(i-1) + EnvironmentSimulation.metabolite_count.get(i-1));
		}
	}

    public void produce(List<Entity> PS, ArrayList<Double> f) {

		double m;
		int n;

		for (int i = f.size()-1; i >= 0; i--) {
			m = vToMass(f.get(i));
			n = (int) Math.round(m/n_real);
			for (int j = 0; j < n; j++) {
				PolySaccharides p = new PolySaccharides((int) this.getStepX(), (int) this.getStepY(), (int) this.getStepZ(), i);
				p.setMass(m/n);
				PS.add(EnvironmentSimulation.metabolite_index.get(i), p);
				EnvironmentSimulation.metabolite_count.set(i, EnvironmentSimulation.metabolite_count.get(i)+1);
			}
		}
		updateIndexes();

    }

	public ArrayList<Double> eat(List<Entity> PS, int i) {
		Entity p = PS.get(i);
		setStepX(p.getX());
		setStepY(p.getY());
		setStepZ(p.getZ());

		//creating an array for metabolites within eat radius
		ArrayList<Integer> [] nearMetabolites = new ArrayList[EnvironmentSimulation.metabolite_count.size()];
		for (int j = 0; j < nearMetabolites.length; j++) {
			nearMetabolites[j] = new ArrayList<Integer>();
		}

		//finds metabolites within eat radius
		for (int j = 0; j < PS.size(); j++) {
			PolySaccharides pol = (PolySaccharides) PS.get(j);
			if (CheckDistance(pol.getX(), this.getStepX(), pol.getY(), this.getStepY(), pol.getZ(), this.getStepZ(), eatRadius)) {
				nearMetabolites[pol.getType()].add(j);
			}
		}

		//converts metabolites found to metabolic fluxes
		ArrayList<Double> v_in = new ArrayList<Double>();
		ArrayList<Double> c_in = new ArrayList<Double>();

		for (int j = 0; j < EnvironmentSimulation.metabolite_count.size(); j++) {
			v_in.add(0.0);
			c_in.add(0.0);
		}
		int ps_size_1 = PS.size();
		for (int j = 0; j < nearMetabolites.length; j++) {
			if (nearMetabolites[j].size() == 0) {
				v_in.set(j, 0.0);
				c_in.set(j, 0.0);
			} else {
				Object[] funcOut = nToV(PS, nearMetabolites[j]);
				v_in.set(j, (Double) funcOut[0]);
				c_in.set(j, (Double) funcOut[2]);
				int index = (int) funcOut[1];
				if (index >= 0) {
					for (int k = nearMetabolites[j].size()-1; k > index; k--) {
						nearMetabolites[j].remove(k);
					}
				}
			}

		}
		int ps_size_2 = PS.size();

		System.out.println("size" + nearMetabolites[0].size());

		//modifies sign of input fluxes
		for (int j = 0; j < v_in.size(); j++) {
			v_in.set(j, v_in.get(j)*-exRxnsDirection.get(j));
		}

		setDegradableEat(getT_d());
        setTimeEat(EnvironmentSimulation.ticks + (int)getDegradableEat());

		ArrayList<Double> v_out = new ArrayList<>();
        for (int j = 0; j <= EnvironmentSimulation.metabolite_count.size(); j++) {
			v_out.add(0.0);
		}

		double miu=0;
		try {
			v_out = runModel(v_in, c_in);
		} catch (JepException e) {
			e.printStackTrace();
		}

		System.out.println("v_in "+v_in.get(0));
		System.out.println("v_out "+v_out.get(0));
		System.out.println("miu "+v_out.get(v_out.size()-1));

		ArrayList<Double> v_produce = new ArrayList<>();
        for (int j = 0; j < v_out.size(); j++) {
			v_produce.add(0.0);
		}
		for (int k = v_out.size()-2; k >= 0; k--) {
			if (exRxnsDirection.get(k) == 0) {
				v_produce.set(k, 0.0);
			} else if ( v_in.get(k) - v_out.get(k) == 0 ) {
				for (int l = nearMetabolites[k].size()-1; l >= 0; l--) {
					int index = nearMetabolites[k].get(l);
					PS.get(index).setLive(false);
					PS.remove(index);
				}
				EnvironmentSimulation.metabolite_count.set(k, EnvironmentSimulation.metabolite_count.get(k) - nearMetabolites[k].size());
				v_produce.set(k, 0.0);

			} else if (growth_substrate.contains(k) && Math.abs(v_in.get(k)) < 1 && v_out.get(k) == 0) {
				boolean boo = true;
				for (int j = growth_substrate.indexOf(k)-1; j >= 0; j--) {
					if (nearMetabolites[j].size() != 0) {
						boo = false;
					}
				}
				if (boo) {
					for (int l = nearMetabolites[k].size()-1; l >= 0; l--) {
						int index = nearMetabolites[k].get(l);
						PS.get(index).setLive(false);
						PS.remove(index);
					}
					EnvironmentSimulation.metabolite_count.set(k, EnvironmentSimulation.metabolite_count.get(k) - nearMetabolites[k].size());
					v_produce.set(k, 0.0);
				}
			} else if (v_out.get(k) == 0) {
				v_produce.set(k, 0.0);
			} else if (v_out.get(k) * exRxnsDirection.get(k) > 0) {
				v_produce.set(k, v_out.get(k));

			} else if (-exRxnsDirection.get(k)*(v_in.get(k) - v_out.get(k)) > 0) {

				double m_t = 0.0;
				double c = 0.0;
				double x = 0.0;
				double v = 0.0;
				double v2 = 0.0;
				int index = -1;
				while ( v2 < Math.abs(v_out.get(k)) ) {
					index++;
					v = v2;
					m_t += PS.get(nearMetabolites[k].get(index)).getMass();
					c = (m_t/n_a) / (getV_bac()); // unit = [mol/m^3]
					x = 0.33*mass/(getV_bac());
					v2 = c/(x*(t_d/ EnvironmentSimulation.getNorm()))*Math.pow(10, 3); //unit = [mmol/gr.hr]
				}
				double m2 = vToMass( v2 - Math.abs(v_out.get(k)) );
				PS.get(nearMetabolites[k].get(index)).setMass(m2);
//    			System.out.println("innn"+index);
//    			System.out.println(PS.size());
				for (int j = index-1; j >= 0; j--) {
					PS.get(nearMetabolites[k].get(j)).setLive(false);
					int ii = nearMetabolites[k].get(j);
					PS.remove(ii);
				}
//    			System.out.println(PS.size());
				EnvironmentSimulation.metabolite_count.set(k, EnvironmentSimulation.metabolite_count.get(k) - index);

			}

		}

		v_produce.set(v_produce.size()-1, v_out.get(v_out.size()-1));

		for (int j = 0; j < ps_size_2 - ps_size_1; j++) {
			PolySaccharides pp = (PolySaccharides) PS.get(PS.size()-1);
			PS.remove(PS.size()-1);
			PS.add(EnvironmentSimulation.metabolite_index.get(pp.getType()), pp);
//			System.out.println("rearrange");
		}
		updateIndexes();

		return v_produce;
	}

	public ArrayList<Double> runModel(ArrayList<Double> f, ArrayList<Double> c)  throws JepException {

		ArrayList<Double> outputs = new ArrayList<>();
		Interpreter interp = null;
		try {

			interp = new SharedInterpreter();

			//setting inputs of matlab function
			String[] exRxnsArray = new String[exRxnsName.size()];
			for (int i = 0; i < exRxnsName.size(); i++) {
				exRxnsArray[i] = exRxnsName.get(i);
			}

			int[] exDirsArray = new int[exRxnsDirection.size()];
			for (int i = 0; i < exRxnsDirection.size(); i++) {
				exDirsArray[i] = exRxnsDirection.get(i);
			}

			double[] fluxArray = new double[f.size()];
			for (int i = 0; i < f.size(); i++) {
				fluxArray[i] = f.get(i);
			}

			int time = EnvironmentSimulation.ticks * EnvironmentSimulation.getTickTime();

			double[] cArray = new double[c.size()];
			for (int i = 0; i < c.size(); i++) {
				cArray[i] = c.get(i);
			}

			//Script para integragação com o CobraPy
			String scriptEat = "/files_simulation/eat_v2.py";
//			String scriptEat = "./src/main/resources/static/eat_v2.py";
//			String scriptEat = "./src/main/resources/static/eat_v2.py";

			//metabolites, directions, metabolic_model - parametros dentro do script que precisam ser settados
			interp.set("reactions", exRxnsArray);
			interp.set("directions", exDirsArray);
			interp.set("v_in", fluxArray);
			String ext = ".json";
			interp.set("file_metabolic_model", mFileName + ext);//MUDAR para pegar direto a extensão
			interp.set("time", time);
			interp.set("c_in", cArray);

			// call matlab function
//			Object[] modelOutput = proxy.returningFeval("/files_simulation/eat", 1, input1, input2, input3, input4, input5, input6);
			interp.runScript(scriptEat);
			double[] result = (double[]) ((NDArray) interp.getValue("result")).getData();
			double[] fluxArray2 = result;

			for (int i = 0; i < fluxArray2.length; i++) {
				outputs.add(fluxArray2[i]);
			}

		} catch (JepException exc) {
			exc.printStackTrace();
		} finally {
			if(interp != null) {
				interp.close();
			}
		}

		return outputs;
	}

	public static ArrayList<Integer> substrateFinder( ArrayList<String> exRxnsName, ArrayList<Integer> exRxnsDirection, String mFileName, String pathSubstrateScript, String pathEatsScript ) throws JepException {

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

    public boolean checkCollision(EnvironmentSimulation env) {
			int x_temp = (int)Math.round(getStepX());
			int y_temp = (int)Math.round(getStepY());
			int z_temp = (int)Math.round(getStepZ());

			double l;
			if (l_bac == 0) {
				l = 2*r_bac;
			} else {
				l = l_bac;
			}
//    	System.out.println(x_temp + " " + y_temp + " " + z_temp + " ");

			for (int x_iterator = 0; x_iterator < l; x_iterator++) {
				for (int y_iterator = 0; y_iterator < 2*r_bac; y_iterator++) {
					for (int z_iterator = 0; z_iterator < 2*r_bac; z_iterator++) {
						if (env.getMesh()[x_temp + x_iterator][y_temp + y_iterator][z_temp + z_iterator] > 0) {
							return true;
						}
					}
				}
			}

			return false;
		}
    
    public boolean findEmptySpace(EnvironmentSimulation env) {
			int x_temp = (int)Math.round(getStepX());
			int y_temp = (int)Math.round(getStepY());
			int z_temp = (int)Math.round(getStepZ());

			double l;
			if (l_bac == 0) {
				l = 2*r_bac;
			} else {
				l = l_bac;
			}

			int x1_collision = -1;
			int y1_collision = -1;
			int z1_collision = -1;
			int x2_collision = -1;
			int y2_collision = -1;
			int z2_collision = -1;
			boolean collision = false;
			for (int x_iterator = 0; x_iterator < l; x_iterator++) {
				for (int y_iterator = 0; y_iterator < 2*r_bac; y_iterator++) {
					for (int z_iterator = 0; z_iterator < 2*r_bac; z_iterator++) {
						if (env.getMesh()[x_temp + x_iterator][y_temp + y_iterator][z_temp + z_iterator] > 0) {
							if (!collision) {
								x1_collision = x_iterator;
								y1_collision = y_iterator;
								z1_collision = z_iterator;
								collision = true;
							}
							x2_collision = x_iterator;
							y2_collision = y_iterator;
							z2_collision = z_iterator;
						}
					}
				}
			}

			int x_move_direction;
			int y_move_direction;
			int z_move_direction;
			if (x1_collision > x_temp) {
				x_move_direction = 1;
			} else {
				x_move_direction = -1;
			}
			if (y1_collision > y_temp) {
				y_move_direction = 1;
			} else {
				y_move_direction = -1;
			}
			if (z1_collision > z_temp) {
				z_move_direction = 1;
			} else {
				z_move_direction = -1;
			}
			int newX = x_temp + (x2_collision - x1_collision) * x_move_direction;
			int newY = y_temp + (y2_collision - y1_collision) * y_move_direction;
			int newZ = z_temp + (z2_collision - z1_collision) * z_move_direction;

			int[] XS = {x_temp, newX};
			int[] YS = {y_temp, newY};
			int[] ZS = {z_temp, newZ};

			for (int i = 0; i < XS.length; i++) {
				for (int j = 0; j < YS.length; j++) {
					for (int j2 = 0; j2 < ZS.length; j2++) {
						setStepX(XS[i]);
						setStepY(YS[j]);
						setStepZ(ZS[j2]);
						wall();
						if (!checkCollision(env)) {
							System.out.println("ffffffffff");
							return true;
						}
					}
				}
			}
			return false;
    }
    
    public ArrayList<Coordinates> occupiedMesh() {
			ArrayList<Coordinates> elements = new ArrayList<>();

			double l;
			if (l_bac == 0) {
				l = 2*r_bac;
			} else {
				l = l_bac;
			}

			for (int x_iterator = 0; x_iterator < l; x_iterator++) {
				for (int y_iterator = 0; y_iterator < 2*r_bac; y_iterator++) {
					for (int z_iterator = 0; z_iterator < 2*r_bac; z_iterator++) {
						elements.add(new Coordinates(getX()+x_iterator, getY()+y_iterator, getZ()+z_iterator));
					}
				}
			}
//    	System.out.println(elements.size() + " ele " + elements.get(0).getX());
			return elements;
		}
    
    //for closed system, boundary of reactor    
    public void wall() {

			setStepX((int)Math.round(getStepX()));
        setStepY((int)Math.round(getStepY()));
        setStepZ((int)Math.round(getStepZ()));

			double l;
			if (l_bac == 0) {
				l = 2*r_bac;
			} else {
				l = l_bac;
			}
//    	System.out.println("l " + l + " r " + 2*r_bac + " L " + Environment.getL());
//    	System.out.println("step "+ getStepX() + " " + getStepY() + " " + getStepZ() + " ");

			while ( (this.getStepX() < 0) || (this.getStepX() + l > EnvironmentSimulation.getL()) || (this.getStepY() < 0) || (this.getStepY() + 2 * r_bac > EnvironmentSimulation.getW()) || (this.getStepZ() < 0) || (this.getStepZ() + 2 * r_bac > EnvironmentSimulation.getD())) {
				if (this.getStepX() < 0) {
					setStepX(-this.getStepX());
				}
				if ( (this.getStepX() + l) > EnvironmentSimulation.getL()) {
					setStepX(EnvironmentSimulation.getL() - (this.getStepX() + l - EnvironmentSimulation.getL()) - l);
				}
				if (this.getStepY() < 0) {
					setStepY(-this.getStepY());
				}
				if ( (this.getStepY() + 2*r_bac) > EnvironmentSimulation.getW() ) {
					setStepY(EnvironmentSimulation.getW() - (this.getStepY() + 2*r_bac - EnvironmentSimulation.getW()) - 2*r_bac);
				}
				if (this.getStepZ() < 0) {
					setStepZ(-this.getStepZ());
				}
				if ( (this.getStepZ() + 2*r_bac) > EnvironmentSimulation.getD()) {
					setStepZ(EnvironmentSimulation.getD() - (this.getStepZ() + 2*r_bac - EnvironmentSimulation.getD()) - 2*r_bac);
				}
			}
//    	System.out.println((this.getStepZ() + 2*r_bac));


		}
    
  //action for each tick of program
    public void tick(List<Entity> PS, List<Entity> A, List<Entity> B, EnvironmentSimulation env)
            throws IOException{

			if (EnvironmentSimulation.ticks == 0) {
				return;
			}

			//calculate positions of metabolites in a PS list
			updateIndexes();
			//then bacteria finds eat in r-distance
			int index = -1;
			int counter = 0;
        do {
			index = scan(PS, searchRadius, growth_substrate.get(counter));
			counter++;
        } while (index == -1 && counter < growth_substrate.size());
        //if bacteria has been found eat
        if (index != -1) {
			//then bacteria directs to eat
			direct(PS,index);
			PolySaccharides ps = (PolySaccharides) PS.get(index);
			//and moves to it
			if (CheckDistance(ps.getX(), getX(), ps.getY(), getY(), ps.getZ(), getZ(), (getSpeed()/ EnvironmentSimulation.getNorm())* EnvironmentSimulation.getTickTime())) {
				setStepX(ps.getX());
				setStepY(ps.getY());
				setStepZ(ps.getZ());
			} else {
				Move();
			}
			wall();

            while (checkCollision(env)) {
				RandomMove();
				wall();
				System.out.println("collision");
			}

			//if bacteria reaches to near of the coordinate of metabolite
			//se a bactéria chegar perto da coordenada do metabólito
			if (CheckDistance(ps.getX(), getX(), ps.getY(), getY(), ps.getZ(), getZ(), eatRadius)) {
				//then bacteria eats
				//então a bactéria come
				produce_fluxes = eat(PS, index);

				//calculation of new biomass
				//cálculo de biomassa nova
				calculateBiomass(produce_fluxes.get(produce_fluxes.size()-1));

				//then bacteria produces metabolite
				//então a bactéria produz metabólito
                produce_fluxes.remove(produce_fluxes.size()-1);
                produce(PS, produce_fluxes);

				//and divides
				//e divide
                divide(B);
                } else {
				//if bacteria couldn't eat metabolite
                EnvironmentSimulation.notEat++;
                }
            } else {
			//if bacteria couldn't find metabolite
			RandomMove();
                //check intersect with boundary
                wall();
                //check if new coordinate is empty and if not again does random moving
                while (checkCollision(env)) {
					RandomMove();
					wall();
					System.out.println("collision");
				}
			EnvironmentSimulation.notEat++;
            }

        //free up current space elements of environment
        env.freeUpMesh(occupiedMesh());
        //set new coordinates
        SetNewCoordinate();
        //fill the new space elements of environment
        env.fillMesh(occupiedMesh());
        //check life
        CheckTime();

    }
}




