package nz.ac.vuw.ecs.swen225.gp22.domain;

import nz.ac.vuw.ecs.swen225.gp22.renderer.Images;

public class ComputerChip extends SolidObject{
	
	public ComputerChip(int xp, int yp){
		setPosition(xp, yp);
		setImg(Images.ComputerChip);
		initialize();
	}
	
	public void onCollision(Chap c, Level l){
		if(!getCollided()){
			setCollided(true);
			setImg(Images.Floor);
			c.obtainChip();
		}
	}
	
	public void initialize(){
		setCollided(false);
		setImg(Images.ComputerChip);
	}
}
