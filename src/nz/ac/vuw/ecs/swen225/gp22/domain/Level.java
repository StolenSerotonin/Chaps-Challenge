package nz.ac.vuw.ecs.swen225.gp22.domain;

import java.awt.Point;
import java.util.Iterator;

public class Level implements Iterable<Point>{
	private static Tile[][] tiles;
	private static SolidObject[][] objects;
	private Point startingPosition;
	private Point chapPosition;
	private static int chipsRequired;
	private int timer;
	
	public Level(int xDimension, int yDimension, int startX, int startY, int chipsRequired){
		tiles = new Tile[xDimension][yDimension];
		objects = new SolidObject[xDimension][yDimension];
		startingPosition = new Point(startX, startY);
		Level.chipsRequired = chipsRequired;
	}
	
	public Level(int xDimension, int yDimension, int chipsRequired){
		tiles = new Tile[xDimension][yDimension];
		objects = new SolidObject[xDimension][yDimension];
		startingPosition = new Point(0, 0);
		chapPosition = new Point((int)(startingPosition.getX()), (int)(startingPosition.getY()));
		Level.chipsRequired = chipsRequired;
	}
	
	public Level(int chipsRequired){
		tiles = new Tile[25][25];
		objects = new SolidObject[25][25];
		startingPosition = new Point(0, 0);
		chapPosition = new Point((int)(startingPosition.getX()), (int)(startingPosition.getY()));
		Level.chipsRequired = chipsRequired;
	}
	
	public Level(){
		tiles = new Tile[25][25];
		objects = new SolidObject[25][25];
		startingPosition = new Point(0, 0);
		chapPosition = new Point((int)(startingPosition.getX()), (int)(startingPosition.getY()));
		chipsRequired = 0;
	}
	
	public Tile[][] getTiles(){
		return tiles;
	}
	
	public static SolidObject[][] getObjects(){
		return objects;
	}
	
	public static Tile getTile(int x, int y){
		return tiles[x][y];
	}
	
	public Tile getTile(Point p){
		return tiles[(int)p.getX()][(int)p.getY()];
	}
	
	public static SolidObject getObject(int x, int y){
		return objects[x][y];
	}

	public static Boolean hasObject(int x, int y){
		return objects[x][y] != null;
	}
		
	public void setTile(int x, int y, Tile tile){
		tiles[x][y] = tile;
	}
	
	public void setObject(int x, int y, SolidObject object){
		objects[x][y] = object;
	}
	
	public void setObject(int x, int y, Door object){
		objects[x][y] = object;
	}
	
	public void setObject(int x, int y, Key object){
		objects[x][y] = object;
	}
	
	public Point getStartingPosition(){
		return startingPosition;
	}
	
	public int getStartingX(){
		return (int)(startingPosition.getX());
	}
	public int getStartingY(){
		return (int)(startingPosition.getY());
	}
	
	public void setStartingPosition(int x, int y){
		startingPosition.setLocation(x, y);
	}
	
	public int getXMax(){
		return tiles.length - 1;
	}
	public int getYMax(){
		return tiles[0].length - 1;
	}
	public Point getChapPosition(){
		return chapPosition;
	}
	
	public static int getChipsRequired(){
		return chipsRequired;
	}
	public void setChipsRequired(int chipsRequired){
		Level.chipsRequired = chipsRequired;
	}

	public int getTimer(){
		return this.timer;
	}

	public void setTimer(int timer){
		this.timer = timer;
	}
	
	public void reset(){
		for(int i = 0; i < objects.length; i++){
			for(int j = 0; j < objects[i].length; j++){
				if(objects[i][j] != null){
					objects[i][j].initialize();
				}
				tiles[i][j].reset();
			}
		}
	}
	
	public Iterator<Point> iterator(){
		return new LevelIterator(this);
	}
}
