package nz.ac.vuw.ecs.swen225.gp22.domain;

import nz.ac.vuw.ecs.swen225.gp22.renderer.Images;

public class Key extends SolidObject{
	private Images colour;
	
	public Key(int xp, int yp, Images colour){
		setPosition(xp, yp);
		this.colour = colour;
		setImg(colour); //eg BLUEkey.jpg
		initialize();
	}
	
	public void onCollision(Chap c, Level l){
		if(!getCollided()){
			if(this.colour == Images.BlueKey) {
				c.getBlueKey();
				setImg(Images.Floor);
				setCollided(true);
			}
			else if(this.colour == Images.RedKey) {
				c.getRedKey();
				setImg(Images.Floor);
				setCollided(true);
			}
			else if(this.colour == Images.GreenKey) {
				c.getGreenKey();
				setImg(Images.Floor);
				setCollided(true);
			}
			else if(this.colour == Images.YellowKey) {
				c.getYellowKey();
				setImg(Images.Floor);
				setCollided(true);
			}
		}
	}
	
	public void initialize(){
		setImg(this.colour);
		setCollided(false);
	}
	
}

