package nz.ac.vuw.ecs.swen225.gp22.domain;

import nz.ac.vuw.ecs.swen225.gp22.renderer.Images;

/*
 * Class for Computer Chips (Treasure)
 */
public class ComputerChip extends SolidObject{
	
	public ComputerChip(int xp, int yp){
		setPosition(xp, yp);
		setImg(Images.ComputerChip);
		initialize();
	}
	
	/*
	 * Handles when Chap collides with this object
	 */
	public void onCollision(Chap c){
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

	public String toString(){
		return "computerChip";
	}
}
