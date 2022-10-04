package nz.ac.vuw.ecs.swen225.gp22.domain;

import nz.ac.vuw.ecs.swen225.gp22.renderer.Images;

public class InfoTile extends Tile{
	private static String info;

	public InfoTile(int xp, int yp){
		setPosition(xp, yp);
		setImg(Images.InfoTile);
		setPassable(true);
		setPushable(false);
	}
	
	public void onWalk(Chap c){
		//display info
	}

	public void setInfo(String info){
		this.info = info;
	}

	public String getInfo(){
		return this.info;
	}
	
	public void reset(){}

	public String toString(){
		return "infoField";
	}
	
}
