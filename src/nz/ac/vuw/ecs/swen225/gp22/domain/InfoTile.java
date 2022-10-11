package nz.ac.vuw.ecs.swen225.gp22.domain;

import nz.ac.vuw.ecs.swen225.gp22.renderer.Images;


public class InfoTile extends Tile{
	private static String info;

	public InfoTile(int xp, int yp, String info){
		setPosition(xp, yp);
		setImg(Images.InfoTile);
		setPassable(true);
		setPushable(false);
		setInfo(info);
	}
	
	public void onWalk(Chap c){
		//display info
		if(c.getX() != this.getX() && c.getY() != this.getY()){
			//infopanel.dispose()
		}
	}

	public void setInfo(String info){
		InfoTile.info = info;
	}

	public String getInfo(){
		return this.info;
	}
	
	public void reset(){}

	public String toString(){
		return "infoField";
	}
	
}
