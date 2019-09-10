import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.ImageIO;

public class Bowser extends MoveableObj {

	BufferedImage bowser;
	BufferedImage bowser2;
	String bowserFile = "files/bowser.png";
	String bowserFile2 = "files/bowser2.jpg";
	
	BufferedImage invbowser;
	BufferedImage invbowser2;
	String invbowserFile = "files/invbowser.jpg";
	String invbowserFile2 = "files/invbowser2.jpg";
	
	private boolean awakened = false;
	private int maxTime = 800;
	private int actionTimer = 0;
	
	private int maxDistance = 100;
	private int displacement = 0;
	private int invincible = 0;
	private int health = 5;
	
	private Player p;
	
	public Bowser(int px, int py, int vx, int vy, int width, int height, GameCourt gc, Player p) {
		super(px, py, vx, vy, width, height);
		
		this.p = p;

		try {
            if (bowser == null) {
                bowser = ImageIO.read(new File(bowserFile));
            }
        } catch (IOException e) {
            System.out.println("Internal Error:" + e.getMessage());
        }
		
		try {
            if (bowser2 == null) {
                bowser2 = ImageIO.read(new File(bowserFile2));
            }
        } catch (IOException e) {
            System.out.println("Internal Error:" + e.getMessage());
        }
		try {
            if (invbowser == null) {
                invbowser = ImageIO.read(new File(invbowserFile));
            }
        } catch (IOException e) {
            System.out.println("Internal Error:" + e.getMessage());
        }
		
		try {
            if (invbowser2 == null) {
                invbowser2 = ImageIO.read(new File(invbowserFile2));
            }
        } catch (IOException e) {
            System.out.println("Internal Error:" + e.getMessage());
        }
	}

	public void setLives (int max) {
		health = max;
	}
	
	public int getLives () {
		return health;
	}
	
	public boolean isAwakened() {
		return awakened;
	}
	
	public void setAwakened(boolean x) {
		awakened = x;
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
	
	@Override
	public void move() {
		if (p.getPx() - getPx() < 0) {
			setForward(false);
		} else {
			setForward(true);
		}
		if (awakened) {
			if (actionTimer == 0) {
				double action = Math.random();
				if (action < 0.5) {
					setVx(0);
					setVy(5);
					
				} else {
					setVx(5);
					setVy(0);
				}
				actionTimer = maxTime;
			} else {
				actionTimer--;
			}
		}
		if (displacement < maxDistance) {
			setPx(getPx() + getVx());
			setPy(getPy() + getVy());
			displacement++;			
		} else if (displacement == maxDistance) {
			setVx(-1 * getVx());
			setVy(-1 * getVy());
			displacement = 0;
		}
	}

	@Override
	public void draw(Graphics g) {
		if (isInvincible()) {
			if (isForward()) {
				g.drawImage(invbowser2, getPx(), getPy(), getWidth(), getHeight(), null);
			} else {
				g.drawImage(invbowser, getPx(), getPy(), getWidth(), getHeight(), null);
			}
		} else {
			if (isForward()) {
				g.drawImage(bowser2, getPx(), getPy(), getWidth(), getHeight(), null);
			} else {
				g.drawImage(bowser, getPx(), getPy(), getWidth(), getHeight(), null);
			}
		}
	}

	@Override
	public void drawRelative(Graphics g, Player p) {
		if (isInvincible()) {
			if (isForward()) {
				g.drawImage(invbowser2, getPxr(), getPyr(), getWidth(), getHeight(), null);
			} else {
				g.drawImage(invbowser, getPxr(), getPyr(), getWidth(), getHeight(), null);
			}
		} else {
			if (isForward()) {
				g.drawImage(bowser2, getPxr(), getPyr(), getWidth(), getHeight(), null);
			} else {
				g.drawImage(bowser, getPxr(), getPyr(), getWidth(), getHeight(), null);
			}
		}
	}
	
}