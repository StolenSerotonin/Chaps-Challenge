package nz.ac.vuw.ecs.swen225.gp22.domain;

import java.util.Map;

public class Chap{
	private Map<String, Integer> inventory;
	private int chips;
	private int x, y, xPos, yPos; 
	private int lastXPos, lastYPos; 
	private Direction direction; 
	
	public enum Direction{
		UP, DOWN, LEFT, RIGHT
	}
	
	private ChapState state; 
	private AliveState alive;
	private DeadState dead;
	private WinState victory;
	
	
	public Chap(int xPos, int yPos){
		this.xPos = xPos;
		this.yPos = yPos;
		x = xPos * 24; //decided on 24 by carefull maths
		y = yPos * 24;
		inventory = Map.of("blue", 0, "red", 0, "green", 0, "yellow", 0);
		direction = Direction.DOWN;
		alive = new AliveState(this);
		dead = new DeadState(this);
		victory = new WinState(this);
		state = alive;
	}
	
	public int getXPos(){
		return xPos;
	}
	public int getYPos(){
		return yPos;
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public void setPosition(int xPos, int yPos){
		this.xPos = xPos;
		this.yPos = yPos;
		x = xPos * 24;
		y = yPos * 24;
	}
	public Direction getDirection(){
		return direction;
	}
	public void setDirection(Direction dir){
		direction = dir;
	}
	
	public int getLastXPos(){
		return lastXPos;
	}
	public int getLastYPos(){
		return lastYPos;
	}
	
	public ChapState getState(){
		return state;
	}
	public AliveState getAliveState(){
		return alive;
	}
	public DeadState getDeadState(){
		return dead;
	}
	
	public WinState getWinState(){
		return victory;
	}
	public void setState(ChapState state){
		this.state = state;
	}
	
	public Map keyStatus(){
		return inventory;
	}
	
	public boolean hasRedKey(){
		return inventory.get("red") > 0;
	}
	public boolean hasBlueKey(){
		return inventory.get("blue") > 0;
	}
	public boolean hasYellowKey(){
		return inventory.get("yellow") > 0;
	}
	public boolean hasGreenKey(){
		return inventory.get("green") > 0;
	}
	
	public int getChips(){
		return chips;
	}
	
	public void obtainChip(){
		chips++;
	}
	
	public void clearChips(){
		chips = 0;
	}
	
	public void getRedKey(){
		int count = inventory.get("red");
		inventory.put("red", count+1);
	}
	public void getBlueKey(){
		int count = inventory.get("blue");
		inventory.put("blue", count+1);
	}
	public void getYellowKey(){
		int count = inventory.get("yellow");
		inventory.put("yellow", count+1);
	}
	public void getGreenKey(){
		int count = inventory.get("green");
		inventory.put("green", count+1);
	}
	public void loseKeys(){
		inventory.forEach((k, v) -> inventory.put(k,0));
	}
	
	public void useRedKey(){
		int count = inventory.get("red");
		if(count>0){
			inventory.put("red", count-1);
		}
	}
	public void useBlueKey(){
		int count = inventory.get("blue");
		if(count>0){
			inventory.put("blue", count-1);
		}
	}
	public void useYellowKey(){
		int count = inventory.get("yellow");
		if(count>0){
			inventory.put("yellow", count-1);
		}
	}
	public void useGreenKey(){
		int count = inventory.get("green");
		if(count>0){
			inventory.put("green", count-1);
		}
	}
	
	public void move(int dx, int dy){
		if(Level.getTile(xPos+dx, yPos+dy).isPassable()) {
			lastYPos = yPos;
			xPos += dx;
			yPos += dy;
			x += (dx * 24);
			y += (dy * 24);
		}
	}
	
	public void moveUp(){
		if(state == alive){
			move(0, -1);
			if(direction != Direction.UP)
				direction = Direction.UP;
		}
	}
	public void moveDown(){
		if(state == alive){
			move(0, 1);
			if(direction != Direction.DOWN)
				direction = Direction.DOWN;
		}
		
	}
	public void moveLeft(){
		if(state == alive){
			move(-1, 0);
			if(direction != Direction.LEFT)
				direction = Direction.LEFT;
		}
	}
	public void moveRight(){
		if(state == alive){
			move(1, 0);
			if(direction != Direction.RIGHT)
				direction = Direction.RIGHT;
		}
	}
	
	public String toString(){
		return "chap";
	}
}
