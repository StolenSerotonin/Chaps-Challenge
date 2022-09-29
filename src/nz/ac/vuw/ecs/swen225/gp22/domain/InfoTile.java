package nz.ac.vuw.ecs.swen225.gp22.domain;

public class InfoTile extends Tile{

	public InfoTile(int xp, int yp){
		setPosition(xp, yp);
		setImg("info.jpeg");
		setPassable(true);
		setPushable(false);
	}
	
	public void onWalk(Chap c){
		//display info
	}
	
	//public void onPush(Block b){}
	
	public void reset(){}
	
}
