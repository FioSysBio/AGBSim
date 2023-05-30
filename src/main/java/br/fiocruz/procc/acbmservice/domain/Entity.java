package br.fiocruz.procc.acbmservice.domain;/*
* this class describes same functions to all other subclasses
* */

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.util.Random;
import java.util.List;


@Getter
@Setter
public abstract class Entity {

    //Constructor
    public Entity (int x, int y,int z) {
        this.x = x; this.y = y; this.z = z;
        this.StepX = x;
        this.StepY = y;
        this.StepZ = z;
    }

    //coordinate[mkm]
    private int x, y, z;

    //direction vectors [mkm]
    protected double dx, dy, dz;

    //helpful coordinate [mkm]]
    private double StepX, StepY, StepZ;

    //speed [mkm/hour]
    private int Speed;

    //size : SizeX = SizeY = SizeZ
    private int SizeX;

    //mass
    protected double mass;

	//color variable; visual version
    protected int color_r, color_g, color_b;

    // live`s label
    private boolean live = true;
    //create panel for painting for visual version

    protected void setColor (int r, int g, int b){
        this.color_r = r;
        this.color_g = g;
        this.color_b = b;
    }

    //action for each program step
    public abstract void tick(List<Entity> PS, List<Entity> ANT, List<Entity> B, EnvironmentSimulation g)
            throws IOException ;
    //draw object; visual version
//    public abstract void draw(Graphics g);// {  }

    public abstract void draw();// {  }

    public abstract void saveToDraw();

    public void SetProperty (int x, int y, int z){
        setStepX(x);
        setStepY(y);
        setStepZ(z);
    }

    public void SetNewCoordinate(){
        setX((int)Math.round(getStepX()));
        setY((int)Math.round(getStepY()));
        setZ((int)Math.round(getStepZ()));
    }


    //fluctuation moving (random)
    public void RandomMove(EnvironmentSimulation env){
    	int phi = new Random().nextInt(360);
    	int teta = new Random().nextInt(360);
        setDz(Math.sin(phi));
        setDy(Math.cos(phi) * Math.sin(teta));
        setDx(Math.cos(phi) * Math.cos(teta));

        setStepX(getStepX() - getSpeed()/ env.getNorm() * getDx());
        setStepY(getStepY() - getSpeed()/ env.getNorm() * getDy());
        setStepZ(getStepZ() - getSpeed()/ env.getNorm() * getDz());
    }
    
    //for closed system, boundary of reactor    
    public void wall(EnvironmentSimulation env) {
    	
    	while (this.getStepX() < 0
                || this.getStepX() > env.getL()
                || this.getStepY() < 0
                || this.getStepY() > env.getW()
                || this.getStepZ() < 0
                || this.getStepZ() > env.getD())
        {
    		if (this.getStepX() < 0)
    			setStepX(-this.getStepX());
    		if (this.getStepX() > env.getL())
    			setStepX(env.getL() - (this.getStepX() - env.getL()));
    		if (this.getStepY() < 0)
    			setStepY(-this.getStepY());
    		if (this.getStepY() > env.getW())
    			setStepY(env.getW() - (this.getStepY() - env.getW()));
    		if (this.getStepZ() < 0)
    			setStepZ(-this.getStepZ());
    		if (this.getStepZ() > env.getD())
    			setStepZ(env.getD() - (this.getStepZ() - env.getD()));
    	}

    		
	}
}

