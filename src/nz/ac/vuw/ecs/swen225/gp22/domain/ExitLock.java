package nz.ac.vuw.ecs.swen225.gp22.domain;

public class ExitLock extends SolidObject{
	
	public ExitLock(int xp, int yp){
		setPosition(xp, yp);
		setImg("exit lock.jpg");
		initialize();
	}
	
	public void onCollision(Chap c, Level l){
		if(getCollided()){}
		else if(c.getChips() >= l.getChipsRequired()){
			setImg("Blank img");
			setCollided(true);
		}
		else{
			c.setPosition(c.getLastXPos(), c.getLastYPos());
		}
	}
	
	public void initialize(){
		setImg("exit lock");
		setCollided(false);
	}
}
