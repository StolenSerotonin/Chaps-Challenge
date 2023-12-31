package nz.ac.vuw.ecs.swen225.gp22.domain;

import nz.ac.vuw.ecs.swen225.gp22.renderer.Images;

public abstract class Tile {
	private int x, y, xPos, yPos;
	private boolean isPassable;
	private boolean pushable;
	private Images img;
	
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public int getXPos(){
		return xPos;
	}
	public int getYPos(){
		return yPos;
	}
	
	public void setPosition(int xPos, int yPos){
		this.xPos = xPos;
		this.yPos = yPos;
		x = xPos * 32;
		y = yPos * 32;
	}
	
	public boolean isPassable(){
		return isPassable;
	}
	public void setPassable(boolean passable){
		this.isPassable = passable;
	}
	
	public boolean isPushable(){
		return pushable;
	}
	
	public void setPushable(boolean pushable){
		this.pushable = pushable;
	}
	
	public Images getImg(){
		return img;
	}
	
	public void setImg(Images img){
		this.img = img;
	}
	
	public abstract void onWalk(Chap c);
	
	//public abstract void onPush(Block b); for if we add the moving blocks
	
	public abstract void reset();
}
