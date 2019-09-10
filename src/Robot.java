import java.awt.Color;
import java.awt.Graphics;

public class Robot extends MoveableObj {

	private int maxDistance;
	private int displacement = 0;
	private int type;
	private int shootTimer = 100;
	private int timer = shootTimer;
	
	public Robot(int px, int py, int vx, int vy, int width, int height, int typeOfRobot, int distance) { //implement types of robots
		super(px, py, vx, vy, width, height);
		
		maxDistance = distance;
		type = typeOfRobot;
	}

	public int getType () {
		return type;
	}
	
	@Override
	public void move() {
		if (type == 1) {
			if (displacement < maxDistance) {
				setPx(getPx() + getVx());
				displacement++;
			} else if (displacement == maxDistance) {
				setVx(-1 * getVx());
				displacement = 0;
			}
		}
		else if (type == 2) {
			if (displacement < maxDistance) {
				setPy(getPy() + getVy());
				displacement++;
			} else if (displacement == maxDistance) {
				setVy(-1 * getVy());
				displacement = 0;
			}
		}
	}
	
	public void shoot(GameCourt gc) {
		if (timer == 0) {
			gc.shootBullet(getPx() + 1, getPy() + 1, getForward() * 6, 0, 1 * GameCourt.M - 2, 1 * GameCourt.M - 2, this);
			setForward(!isForward());
			timer = shootTimer;
		} else {
			timer--;
		}
	}
	
	@Override
	public void draw (Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(getPx(), getPy(), getWidth(), getHeight());
	}

	@Override
	public void drawRelative(Graphics g, Player p) {
		g.setColor(Color.BLACK);
		g.fillRect(getPxr(), getPyr(), getWidth(), getHeight());
	}
	
}