package nz.ac.vuw.ecs.swen225.gp22.domain;

import java.awt.Point;
import java.util.Iterator;

public class LevelIterator implements Iterator<Point>{
	
	private int x, y;
	private Level l;
	
	public LevelIterator(Level l){
		this.l = l;
		x = 0;
		y = 0;
	}
	
	public boolean hasNext(){
		return(x < l.getXMax() || y < l.getYMax());
	}
	
	public Point next(){
		if(x < l.getXMax()){
			x++;
		}
		else if(y < l.getYMax()){
			x = 0;
			y++;
		}
		return new Point(x, y);
	}
	
	public Point currentItem(){
		return new Point(x, y);
	}
}
