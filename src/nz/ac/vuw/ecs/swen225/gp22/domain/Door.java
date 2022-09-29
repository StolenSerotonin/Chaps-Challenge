package nz.ac.vuw.ecs.swen225.gp22.domain;

public class Door extends SolidObject{
	private DoorC colour;
	
	public enum DoorC {
		RED, GREEN, BLUE, YELLOW
	}
	
	public Door(int xp, int yp, DoorC colour){
		setPosition(xp, yp);
		this.colour = colour;
		setImg(this.colour.name()+"door.jpg");//example BLUEdoor.jpg
		initialize();
	}
	
	public void onCollision(Chap c, Level l){
		if(getCollided()){}
		else if(this.colour == DoorC.BLUE){
			if(c.hasBlueKey()){
				c.useBlueKey();
				setImg("blank");
				setCollided(true);
			}
		}
		else if(this.colour == DoorC.RED){
			if(c.hasRedKey()){
				c.useRedKey();
				setImg("blank");
				setCollided(true);
			}
		}
		else if(this.colour == DoorC.GREEN){
			if(c.hasGreenKey()){
				c.useGreenKey();
				setImg("blank");
				setCollided(true);
			}
		}
		else if(this.colour == DoorC.YELLOW){
			if(c.hasYellowKey()){
				c.useYellowKey();
				setImg("blank");
				setCollided(true);
			}
		}
		else{
			c.setPosition(c.getLastXPos(), c.getLastYPos());
		}
	}
	
	public void initialize(){
		setImg(this.colour.name()+"door.jpg");
		setCollided(false);
	}
}
