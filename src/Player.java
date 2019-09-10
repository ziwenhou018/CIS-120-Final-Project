import java.awt.*;

public class Player extends MoveableObj {

	private int vxmax = 1;
	private int vymax = 1;
	private boolean alive = true;
	private int maxJumps = 1;
	private int jumps = maxJumps; //possible more jumps
	private int maxLives = 1;
	private int lives = maxLives;
	private int invincible = 0;
	
	public Player(int px, int py, int vx, int vy, int width, int height) {
		super(px, py, vx, vy, width, height);
		setPxr(GameCourt.GAME_WIDTH / 2);
		setPyr(GameCourt.GAME_WIDTH / 2);
	}
	
	public void setVxMax (int max) {
		vxmax = max;
	}
	
	public void setVyMax (int max) {
		vymax = max;
	}
	
	public int getVxMax () {
		return vxmax;
	}
	
	public int getVyMax () {
		return vymax;
	}
	
	public void setAlive (boolean x) {
		alive = x;
	}
	
	public boolean isAlive () {
		return alive;
	}
	
	public void setMaxJumps (int max) {
		maxJumps = max;
	}
	
	public int getMaxJumps () {
		return maxJumps;
	}
	
	public void setJumps (int max) {
		jumps = max;
	}
	
	public int getJumps () {
		return jumps;
	}
	
	public void setLives (int max) {
		lives = max;
	}
	
	public int getLives () {
		return lives;
	}
	
	public void setMaxLives (int max) {
		maxLives = max;
	}
	
	public int getMaxLives () {
		return maxLives;
	}
	
	public boolean isInvincible () {
		if (invincible != 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public int getInvincible () {
		return invincible;
	}
	
	public void setInvincible (int max) {
		invincible = max;
	}
	
	public void move () {
		setPx(getPx() + getVx());
		setPy(getPy() + getVy());
	}

	public void draw (Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(getPx(), getPy(), getWidth(), getHeight());
	}

	@Override
	public void drawRelative(Graphics g, Player p) {
		if (isInvincible() && lives > 0) {
			g.setColor(Color.YELLOW);
		} else {
			g.setColor(Color.WHITE);
		}
		
		g.fillRect(getPxr(), getPyr(), getWidth(), getHeight());	
	}
	
}