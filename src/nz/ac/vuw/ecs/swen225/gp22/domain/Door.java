package nz.ac.vuw.ecs.swen225.gp22.domain;

import nz.ac.vuw.ecs.swen225.gp22.renderer.Images;

public class Door extends SolidObject{

	private Images colour;
	
	public Door(int xp, int yp, Images colour){
		setPosition(xp, yp);
		this.colour = colour;
		setImg(colour);//example BLUEdoor.jpg
		initialize();
	}
	
	public void onCollision(Chap c, Level l){
		if(getCollided()){}
		else if(this.colour == Images.BlueDoor){
			if(c.hasBlueKey()){
				c.useBlueKey();
				setImg(Images.Floor);
				setCollided(true);
			}
		}
		else if(this.colour == Images.RedDoor){
			if(c.hasRedKey()){
				c.useRedKey();
				setImg(Images.Floor);
				setCollided(true);
			}
		}
		else if(this.colour == Images.GreenDoor){
			if(c.hasGreenKey()){
				c.useGreenKey();
				setImg(Images.Floor);
				setCollided(true);
			}
		}
		else if(this.colour == Images.YellowDoor){
			if(c.hasYellowKey()){
				c.useYellowKey();
				setImg(Images.Floor);
				setCollided(true);
			}
		}
		else{
			c.setPosition(c.getLastXPos(), c.getLastYPos());
		}
	}
	
	public void initialize(){
		setImg(this.colour);
		setCollided(false);
	}
}
