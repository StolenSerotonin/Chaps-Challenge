package nz.ac.vuw.ecs.swen225.gp22.domain;

import nz.ac.vuw.ecs.swen225.gp22.renderer.Images;

public class ExitLock extends SolidObject{
	
	public ExitLock(int xp, int yp){
		setPosition(xp, yp);
		setImg(Images.ExitLock);
		initialize();
	}
	
	public void onCollision(Chap c, Level l){
		if(getCollided()){}
		else if(c.getChips() >= l.getChipsRequired()){
			setImg(Images.Floor);
			setCollided(true);
		}
		else{
			c.setPosition(c.getLastXPos(), c.getLastYPos());
		}
	}
	
	public void initialize(){
		setImg(Images.ExitLock);
		setCollided(false);
	}
}
