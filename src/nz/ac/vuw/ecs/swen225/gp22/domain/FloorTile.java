package nz.ac.vuw.ecs.swen225.gp22.domain;

public class FloorTile extends Tile{

	public FloorTile(int xPos, int yPos){
		setPosition(xPos, yPos);
		setPassable(true);
		setPushable(true);
		setImg("floor.jpg");
	}
	
	public void onWalk(Chap c){
		
	}
	
	//public void onPush(Block b){}
	
	public void reset(){}
	
}
