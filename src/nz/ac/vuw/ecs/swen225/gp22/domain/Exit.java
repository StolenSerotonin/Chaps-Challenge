package nz.ac.vuw.ecs.swen225.gp22.domain;

public class Exit extends Tile{

	public Exit(int xp, int yp){
		setPosition(xp, yp);
		setImg("exit.jpeg");
		setPassable(true);
		setPushable(false);
	}
	
	public void onWalk(Chap c){
		c.getState().win();
	}
	
	//public void onPush(Block b){}
	
	public void reset(){}
	
}
