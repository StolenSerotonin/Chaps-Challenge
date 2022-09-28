package nz.ac.vuw.ecs.swen225.gp22.domain;

public class WallTile extends Tile{

	public WallTile(int xPos, int yPos){
		setPosition(xPos, yPos);
		setPassable(false); 
		setPushable(false);
		setImg("wall.jpg");
	}
	
	public void onWalk(Chap c){}
	
	//public void onPush(Block b){}
	
	public void reset(){}
	
}

