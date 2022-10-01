package nz.ac.vuw.ecs.swen225.gp22.domain;

// import java.awt.Image;
// import java.io.File;
// import java.io.IOException;
// import javax.imageio.ImageIO;
// import java.awt.Graphics2D;
// import java.awt.Point;

//import app.PlayChip; something along these lines

public class Chap {
	
	final static int RED = 0, BLUE = 1, YELLOW = 2, GREEN = 3;
	
	private int x, y, xPos, yPos; 
	private int lastXPos, lastYPos; 
	// private String upimg, downimg, rightimg, leftimg;
	private Direction direction; 
	
	public enum Direction{
		UP, DOWN, LEFT, RIGHT
	}
	
	private int[] key; 
	private int chips;
	
	private ChapState state; 
	private AliveState alive;
	private DeadState dead;
	private WinState victory;
	
	//private PlayChap game; // Chip needs to tell the game when he's won.
	
	public Chap(int xPos, int yPos /*, PlayChap game */){
		this.xPos = xPos;
		this.yPos = yPos;
		x = xPos * 32; //Each tile is 32 x 32 pixels.
		y = yPos * 32;
		key = new int[4];
		direction = Direction.DOWN;
		alive = new AliveState(this);
		dead = new DeadState(this);
		victory = new WinState(this);
		state = alive;
		//this.game = game;
		// loadImages();
	}
	
	// private void loadImages(){
	// 	//try{
	// 		upimg = "";
	// 		downimg = "";
	// 		leftimg = "";
	// 		rightimg = "";

	// 	// } catch(IOException e){
	// 	// 	System.err.println("An exception occurred while to load image");
	// 	// 	System.exit(1);
	// 	// }
	// }
	
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
		x = xPos * 32;
		y = yPos * 32;
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
	
	public int[] keyStatus(){
		return key;
	}
	
	public boolean hasRedKey(){
		return key[RED] > 0;
	}
	public boolean hasBlueKey(){
		return key[BLUE] > 0;
	}
	public boolean hasYellowKey(){
		return key[YELLOW] > 0;
	}
	public boolean hasGreenKey(){
		return key[GREEN] > 0;
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
		key[RED]++;
	}
	public void getBlueKey(){
		key[BLUE]++;
	}
	public void getYellowKey(){
		key[YELLOW]++;
	}
	public void getGreenKey(){
		key[GREEN]++;
	}
	public void loseKeys(){
		for(int i = 0; i < 4; i++){
			key[i] = 0;
		}
	}
	
	public void useRedKey(){
		if(hasRedKey())
			key[RED]--;
	}
	public void useBlueKey(){
		if(hasBlueKey())
			key[BLUE]--;
	}
	public void useYellowKey(){
		if(hasYellowKey())
			key[YELLOW]--;
	}
	public void useGreenKey(){
		if(hasGreenKey())
			key[GREEN]--;
	}
	
	public void move(int dx, int dy){
		if(Level.getTile(xPos, yPos).isPassable()) {
			lastYPos = yPos;
			xPos += dx;
			yPos += dy;
			x += (dx * 32);
			y += (dy * 32);
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
	

	// public PlayChip getGame(){
	// 	return game;
	// }
}
