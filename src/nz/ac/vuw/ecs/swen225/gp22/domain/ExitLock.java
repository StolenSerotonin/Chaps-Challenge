package nz.ac.vuw.ecs.swen225.gp22.domain;

import nz.ac.vuw.ecs.swen225.gp22.renderer.Images;

public class ExitLock extends SolidObject{
	
	public ExitLock(int xp, int yp){
		setPosition(xp, yp);
		setImg(Images.ExitLock);
		initialize();
	}
	
	public void onCollision(Chap c){
		if(getCollided()){}
		else if(c.getChips() >= c.getLevel().getChipsRequired()){
			setImg(Images.Floor);
			setCollided(true);
		}
		else{
			throw new IllegalArgumentException("Chap does not have enough chips");
		}
	}
	
	public void initialize(){
		setImg(Images.ExitLock);
		setCollided(false);
	}

	public String toString(){
		return "exitLock";
	}
}
