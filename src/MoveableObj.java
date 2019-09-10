public abstract class MoveableObj extends Entity {
	/**
	 * The class is used to represent various moving objects in the game
	 */
	
	//The current velocity of the object
	private int vx;
	private int vy;
	private boolean forward = true;
	
	public MoveableObj (int px, int py, int vx, int vy, int width, int height) {
		super(px, py, width, height);
		this.vx = vx;
		this.vy = vy;
	}
	
	/*** GETTERS **********************************************************************************/
    
    public int getVx() {
        return this.vx;
    }
    
    public int getVy() {
        return this.vy;
    }
    
    public boolean isForward() {
    	return forward;
    }
    
    public int getForward() {
    	if (forward) {
    		return 1;
    	} else {
    		return -1;
    	}
    }
    
    /*** SETTERS **********************************************************************************/

    public void setVx (int vx) {
        this.vx = vx;
    }

    public void setVy (int vy) {
        this.vy = vy;
    }
    
    public void setForward(boolean x) {
    	forward = x;
    }
    
	public boolean intersects (Entity that) {
        return (this.getPx() + this.getWidth() >= that.getPx()
            && this.getPy() + this.getHeight() >= that.getPy()
            && that.getPx() + that.getWidth() >= this.getPx() 
            && that.getPy() + that.getHeight() >= this.getPy());
    }
	
	public boolean willIntersect (Entity that) {
        int thisNextX = this.getPx() + this.vx;
        int thisNextY = this.getPy() + this.vy;
        int thatNextX;
        int thatNextY;
        if (that instanceof MoveableObj) {
        	thatNextX = that.getPx() + ((MoveableObj) that).vx;
        	thatNextY = that.getPy() + ((MoveableObj) that).vy;
        } else {
        	thatNextX = that.getPx();
        	thatNextY = that.getPy();
        }
    
        return (thisNextX + this.getWidth() >= thatNextX
            && thisNextY + this.getHeight() >= thatNextY
            && thatNextX + that.getWidth() >= thisNextX 
            && thatNextY + that.getHeight() >= thisNextY);
    }
	
	public abstract void move ();
}