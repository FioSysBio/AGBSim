package br.fiocruz.procc.acbmservice.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.io.IOException;
import java.util.Random;


@Getter @Setter
public class PolySaccharides extends Entity {
	
	private String name;

	private int type;

    // /Speed variable
    //OY - to environment boundary
    private double speedFlux;
    //OX - out from environment
    private double speedOut;
    //mass parameter

    private EnvironmentSimulation environmentSimulation;

	//Constructor
    public PolySaccharides (int x, int y, int z, int type, EnvironmentSimulation env) {
        super(x, y, z);
        this.type = type;
        this.environmentSimulation = env;
        this.name = environmentSimulation.getMetabolite_name().get(type);
        setProperty();
    }
    
	public void setProperty() {
        setSizeX(2);
        setColor(environmentSimulation.getMetabolite_color().get(type).getRed(),
                environmentSimulation.getMetabolite_color().get(type).getGreen(),
                environmentSimulation.getMetabolite_color().get(type).getBlue());
        setMass( new Random().nextGaussian() * ( environmentSimulation.getBacteria_n_real() / 7) + (environmentSimulation.getBacteria_n_real() ) );
//        setMass(Bacteria.n_real);

	}

    //chose direction of oy speed
    public void MoveFlux (double Speed_out,double Speed_trans){

//        setStepX(getStepX() + Speed_out);
        if (getStepY() > environmentSimulation.getW()/2){
            setStepY(getStepY() + Speed_trans);
        }
        else {
            setStepY(getStepY() - Speed_trans);
        }
    }

    //draw PS in the environment
//    public void draw(Graphics g){
//
//        GradientPaint gp = new GradientPaint((int)(getX()/Environment.getTickX()), (int) (getZ()/Environment.getTickY()), new Color(getColor_r(),getColor_g(),getColor_b(), 180),
//                (int)(getX()/Environment.getTickX()), (int) (getY()/Environment.getTickY()) + getSizeX(), new Color(getColor_r() ,getColor_g(), getColor_b(), 180));
//        Graphics2D g2d = (Graphics2D) g;
//        g2d.setPaint(gp);
////        int x = (int) (getX() / Environment.getTickX());
////        int y = (int) (getY() / Environment.getTickY());
////        System.out.println("X "+getX() + " tickx"+ Environment.getTickX());
////        System.out.println("x: "+x+", y: "+y);
//        g2d.fillRect((int) (getX()/Environment.getTickX()), (int) (getY()/Environment.getTickY()), getSizeX(), getSizeX());
//    }

    public void draw(){

        GradientPaint gp = new GradientPaint(
                (int)(getX()/ environmentSimulation.getTickX()),
                (int) (getZ() / environmentSimulation.getTickY()),
                new Color(getColor_r(),getColor_g(),getColor_b(), 180),
                (int)(getX()/ environmentSimulation.getTickX()),
                (int) (getY()/ environmentSimulation.getTickY()) + getSizeX(),
                new Color(getColor_r() ,getColor_g(), getColor_b(), 180));

        System.out.println("Desenho Retângulo: "
                + "Coord x1: " + (int) (getX()/ environmentSimulation.getTickX())
                + " / Coord y1" + (int) (getY()/ environmentSimulation.getTickY())
                + " / Largura: " + getSizeX()
                + " / Altura: " + getSizeX());
    }

    public void saveToDraw(){

        GradientPaint gp = new GradientPaint(
                (int)(getX()/ environmentSimulation.getTickX()),
                (int) (getZ()/ environmentSimulation.getTickY()),
                new Color(getColor_r(),getColor_g(),getColor_b(), 180),
                (int)(getX()/ environmentSimulation.getTickX()),
                (int) (getY()/ environmentSimulation.getTickY()) + getSizeX(),
                new Color(getColor_r() ,getColor_g(), getColor_b(), 180)
        );

        System.out.println("Desenho Retângulo: "
                + "Coord x1: " + (int) (getX()/ environmentSimulation.getTickX())
                + " / Coord y1" + (int) (getY()/ environmentSimulation.getTickY())
                + " / Largura: " + getSizeX()
                + " / Altura: " + getSizeX()
        );
    }


    //action for each program tick
    public void tick(java.util.List<Entity> PS, java.util.List<Entity> A,
                     java.util.List<Entity> B, EnvironmentSimulation g)
            throws IOException {
    	RandomMove(g);
    	wall(g);
        SetNewCoordinate();
    }


}

