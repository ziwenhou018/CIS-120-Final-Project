/**
 * 
 * @author Ziwen Hou CIS 120 Game
 */

import java.awt.*;

/**
 * The class is used to represent any objects in the game.
 * All objects take up space, each with a position and a size.
 *
 */

public abstract class Entity implements Comparable <Entity> {
	
	//The current position of the entity, determined by the top left corner
	private int px;
	private int py;
	
	//The positions relative to the player;
	private int pxr;
	private int pyr;
	
	//The size of the entity
	private int width;
	private int height;
	
	public Entity (int px, int py, int width, int height) {
		this.px = px;
		this.py = py;
		this.width = width;
		this.height = height;
	}
	
	/*** GETTERS **********************************************************************************/
    public int getPx () {
        return this.px;
    }

    public int getPy () {
        return this.py;
    }
    
    public int getPxr () {
        return this.pxr;
    }

    public int getPyr () {
        return this.pyr;
    }
    
    public int getWidth () {
        return this.width;
    }
    
    public int getHeight () {
        return this.height;
    }

    /*** SETTERS **********************************************************************************/
    public void setPx (int px) {
        this.px = px;
    }

    public void setPy (int py) {
        this.py = py;
    }
    
    public void setPxr (int px) {
        this.pxr = px;
    }

    public void setPyr (int py) {
        this.pyr = py;
    }

    public int compareTo (Entity that) {
    	if (this.px < that.getPx()) {
    		return -1;
    	} else if (this.px > that.getPx()) {
    		return 1;
    	} else if (this.py < that.getPx()) {
    		return -1;
    	} else if (this.py > that.getPy()) {
    		return 1;
    	} else {
    		return 0;
    	}
    }
    
    public boolean pointExists (int x, int y) {
    	return (x <= this.getPx() + this.getWidth() 
    		&& x >= this.getPx() 
    		&& y <= this.getPy() + this.getHeight()
    		&& y >= this.getPy());
    }
    
	public abstract void draw (Graphics g);
	
	public void updateRelative (Player p) {
		pxr = GameCourt.GAME_WIDTH / 2 - (p.getPx() - px);
		pyr = GameCourt.GAME_HEIGHT / 2 - (p.getPy() - py);
	}
	
	public abstract void drawRelative (Graphics g, Player p);
}