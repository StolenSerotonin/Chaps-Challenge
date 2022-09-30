package nz.ac.vuw.ecs.swen225.gp22.domain;

import nz.ac.vuw.ecs.swen225.gp22.renderer.Images;

public class FloorTile extends Tile{

	public FloorTile(int xPos, int yPos){
		setPosition(xPos, yPos);
		setPassable(true);
		setPushable(true);
		setImg(Images.Floor);
	}
	
	public void onWalk(Chap c){
		
	}
	
	//public void onPush(Block b){}
	
	public void reset(){}
	
}
