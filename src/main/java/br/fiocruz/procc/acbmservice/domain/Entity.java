package br.fiocruz.procc.acbmservice.domain;/*
* this class describes same functions to all other subclasses
* */

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.io.IOException;
import java.util.Random;
import java.util.List;


public abstract class Entity {

    //Constructor
    public Entity (int x, int y,int z) {
        this.x = x; this.y = y; this.z = z;
        this.StepX = x;
        this.StepY = y;
        this.StepZ = z;
    }

    //coordinate[mkm]
    @Getter(AccessLevel.PROTECTED)@Setter(AccessLevel.PROTECTED)
    private int x, y, z;

    //direction vectors [mkm]
    @Getter(AccessLevel.PROTECTED)@Setter(AccessLevel.PROTECTED)
    protected double dx, dy, dz;

    //helpful coordinate [mkm]]
    @Getter(AccessLevel.PROTECTED)@Setter(AccessLevel.PROTECTED)
    private double StepX, StepY, StepZ;

    //speed [mkm/hour]
    @Getter(AccessLevel.PROTECTED)@Setter(AccessLevel.PROTECTED)
    private int Speed;

    //size : SizeX = SizeY = SizeZ
    @Getter(AccessLevel.PROTECTED)@Setter(AccessLevel.PROTECTED)
    private int SizeX;

    //mass
    @Getter(AccessLevel.PUBLIC)@Setter(AccessLevel.PUBLIC)
    protected double mass;

	//color variable; visual version
    @Getter(AccessLevel.PROTECTED)@Setter(AccessLevel.PROTECTED)
    protected int color_r, color_g, color_b;

    // live`s label
    private boolean live = true;
    //create panel for painting for visual version

    //voids to get/set value of variable
    protected void setX (int x){
        this.x = x ;
    }

    protected void setColor (int r, int g, int b){
        this.color_r = r;
        this.color_g = g;
        this.color_b = b;
    }

    protected void setLive (boolean live){
        this.live = live;
    }
    protected boolean getLive(){
        return this.live;
    }

    protected void setSpeed(double s){
        this.Speed = (int) s;
    }
    protected double getSpeed(){
        return  Speed;
    }

    public int getSizeX(){
        return  SizeX;
    }
    public void setSizeX(int s) {
        this.SizeX = s;
    }

    protected void setStepX(double x){
        this.StepX = x;
    }
    protected double getStepX(){
        return StepX;
    }

    protected double getStepY(){
        return StepY;
    }
    protected void setStepY(double y){
        this.StepY = y;
    }
    
    protected double getStepZ(){
        return StepZ;
    }
    protected void setStepZ(double z){
        this.StepZ = z;
    }

    //action for each program step
    public abstract void tick(List<Entity> PS, List<Entity> ANT, List<Entity> B, Environment g)
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
    public void RandomMove(){
    	int phi = new Random().nextInt(360);
    	int teta = new Random().nextInt(360);
        setDz(Math.sin(phi));
        setDy(Math.cos(phi) * Math.sin(teta));
        setDx(Math.cos(phi) * Math.cos(teta));

        setStepX(getStepX() - getSpeed()/Environment.getNorm() * getDx());
        setStepY(getStepY() - getSpeed()/Environment.getNorm() * getDy());
        setStepZ(getStepZ() - getSpeed()/Environment.getNorm() * getDz());
    }
    
    //for closed system, boundary of reactor    
    public void wall() {
    	
    	while (this.getStepX() < 0 || this.getStepX() > Environment.getL() || this.getStepY() < 0 || this.getStepY() > Environment.getW() || this.getStepZ() < 0 || this.getStepZ() > Environment.getD()) {
    		if (this.getStepX() < 0)
    			setStepX(-this.getStepX());
    		if (this.getStepX() > Environment.getL())
    			setStepX(Environment.getL() - (this.getStepX() - Environment.getL()));
    		if (this.getStepY() < 0)
    			setStepY(-this.getStepY());
    		if (this.getStepY() > Environment.getW())
    			setStepY(Environment.getW() - (this.getStepY() - Environment.getW()));
    		if (this.getStepZ() < 0)
    			setStepZ(-this.getStepZ());
    		if (this.getStepZ() > Environment.getD())
    			setStepZ(Environment.getD() - (this.getStepZ() - Environment.getD()));
    	}

    		
	}
}

