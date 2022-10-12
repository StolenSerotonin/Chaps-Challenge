package nz.ac.vuw.ecs.swen225.gp22.domain;

import nz.ac.vuw.ecs.swen225.gp22.renderer.Images;

public class Door extends SolidObject{

	private Images colour;
	
	public Door(int xp, int yp, Images colour){
		setPosition(xp, yp);
		this.colour = colour;
		setImg(colour);
		initialize();
	}
	
	public void onCollision(Chap c){
		if(getCollided()){}
		else if(this.colour == Images.BlueDoor){
			if(c.getLevel().hasBlueKey()){
				c.useBlueKey();
				setImg(Images.Floor);
				setCollided(true);
			}
		}
		else if(this.colour == Images.RedDoor){
			if(c.getLevel().hasRedKey()){
				c.useRedKey();
				setImg(Images.Floor);
				setCollided(true);
			}
		}
		else if(this.colour == Images.GreenDoor){
			if(c.getLevel().hasGreenKey()){
				c.useGreenKey();
				setImg(Images.Floor);
				setCollided(true);
			}
		}
		else if(this.colour == Images.YellowDoor){
			if(c.getLevel().hasYellowKey()){
				c.useYellowKey();
				setImg(Images.Floor);
				setCollided(true);
			}
		}
		else{
			c.setPosition(c.getLastXPos(), c.getLastYPos());
			throw new IllegalArgumentException("Chap does not have the key");
		}
	}
	
	public void initialize(){
		setImg(this.colour);
		setCollided(false);
	}

	public String toString(){
		return this.colour.getName();
	}
}
