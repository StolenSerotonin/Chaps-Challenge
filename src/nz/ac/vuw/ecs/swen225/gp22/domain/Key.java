package nz.ac.vuw.ecs.swen225.gp22.domain;

public class Key extends SolidObject{
	private KeyC colour;
	
	public enum KeyC {
		RED, GREEN, BLUE, YELLOW
	}
	
	public Key(int xp, int yp, KeyC colour){
		setPosition(xp, yp);
		this.colour = colour;
		setImg(this.colour.name()+"key.jpg"); //eg BLUEkey.jpg
		initialize();
	}
	
	public void onCollision(Chap c, Level l){
		if(!getCollided()){
			if(this.colour == KeyC.BLUE) {
				c.getBlueKey();
				setImg("Blank img");
				setCollided(true);
			}
			else if(this.colour == KeyC.RED) {
				c.getRedKey();
				setImg("Blank img");
				setCollided(true);
			}
			else if(this.colour == KeyC.GREEN) {
				c.getGreenKey();
				setImg("Blank img");
				setCollided(true);
			}
			else if(this.colour == KeyC.YELLOW) {
				c.getYellowKey();
				setImg("Blank img");
				setCollided(true);
			}
		}
	}
	
	public void initialize(){
		setImg(this.colour.name()+"key.jpg");
		setCollided(false);
	}
	
}

