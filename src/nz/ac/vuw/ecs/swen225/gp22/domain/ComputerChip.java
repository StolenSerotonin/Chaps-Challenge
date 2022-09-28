package nz.ac.vuw.ecs.swen225.gp22.domain;

public class ComputerChip extends SolidObject{
	
	public ComputerChip(int xp, int yp){
		setPosition(xp, yp);
		setImg("computerchip.jpeg");
		initialize();
	}
	
	public void onCollision(Chap c, Level l){
		if(!getCollided()){
			setCollided(true);
			setImg("Blank img jpg");
			c.obtainChip();
		}
	}
	
	public void initialize(){
		setCollided(false);
		setImg("computerchip jpeg");
	}
}
