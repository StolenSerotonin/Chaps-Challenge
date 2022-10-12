package nz.ac.vuw.ecs.swen225.gp22.domain;

public class Chap{
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
	
	public int getChips(){
		return chips;
	}
	
	public void obtainChip(){
		if(!(Level.getObject(this.xPos, this.yPos) instanceof ComputerChip)){
			throw new IllegalStateException("There is no ComputerChip here: " + getYPos() + getXPos());
		}
		int uncollectedChips = Level.getChipsRequired() - getChips(); 
		chips++;
		int uncollectedChips2 = Level.getChipsRequired() - getChips();
		assert uncollectedChips2 == uncollectedChips - 1;

	}
	
	public void clearChips(){
		chips = 0;
	}
	
	public void getRedKey(){
		if(!(Level.getObject(this.xPos, this.yPos) instanceof Key)){
			throw new IllegalStateException("There is no Key here: " + getYPos() + getXPos());
		}
		int count = Level.getKey("red");
		Level.putKey("red", count + 1);
		int count2 = Level.getKey("red");
		assert count2 == count + 1;
	}
	public void getBlueKey(){
		if(!(Level.getObject(this.xPos, this.yPos) instanceof Key)){
			throw new IllegalStateException("There is no Key here: " + getYPos() + getXPos());
		}
		int count = Level.getKey("blue");
		Level.putKey("blue", count + 1);
		int count2 = Level.getKey("blue");
		assert count2 == count + 1;
	}
	public void getYellowKey(){
		if(!(Level.getObject(this.xPos, this.yPos) instanceof Key)){
			throw new IllegalStateException("There is no Key here: " + getYPos() + getXPos());
		}
		int count = Level.getKey("yellow");
		Level.putKey("yellow", count + 1);
		int count2 = Level.getKey("yellow");
		assert count2 == count + 1;
	}
	public void getGreenKey(){
		if(!(Level.getObject(this.xPos, this.yPos) instanceof Key)){
			throw new IllegalStateException("There is no Key here: " + getYPos() + getXPos());
		}
		int count = Level.getKey("green");
		Level.putKey("green", count + 1);
		int count2 = Level.getKey("green");
		assert count2 == count + 1;
	}
	
	public void useRedKey(){
		int count = Level.getKey("red");
		if(count<=0){
			throw new IllegalStateException("Chap has no Red Key");
		} else{
			Level.putKey("red", count-1);
			int count2 = Level.getKey("red");
			assert count2 == count - 1;
		}
	}
	public void useBlueKey(){
		int count = Level.getKey("blue");
		if(count<=0){
			throw new IllegalStateException("Chap has no Blue Key");
		} else{
			Level.putKey("blue", count-1);
			int count2 = Level.getKey("blue");
			assert count2 == count - 1;
		}
	}
	public void useYellowKey(){
		int count = Level.getKey("yellow");
		if(count<=0){
			throw new IllegalStateException("Chap has no Yellow Key");
		} else{
			Level.putKey("yellow", count-1);
			int count2 = Level.getKey("yellow");
			assert count2 == count - 1;
		}
	}
	public void useGreenKey(){
		int count = Level.getKey("green");
		if(count<=0){
			throw new IllegalStateException("Chap has no Green Key");
		} else{
			Level.putKey("green", count-1);
			int count2 = Level.getKey("green");
			assert count2 == count - 1;
		}
	}
	
	public void move(int dx, int dy){
		if(!Level.getTile(xPos+dx, yPos+dy).isPassable()) {
			throw new IllegalArgumentException("Chap cannot phase through walls");
		}
		if(Level.hasObject(xPos + dx, yPos + dy)){
			CollisionCheck(xPos + dx, yPos + dy);
		}
		lastYPos = yPos;
		xPos += dx;
		yPos += dy;
		x += (dx * 24);
		y += (dy * 24);
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

	public void CollisionCheck(int x, int y){
		Level.getObject(x, y).onCollision(this);
	}
	
	public String toString(){
		return "chap";
	}
}
