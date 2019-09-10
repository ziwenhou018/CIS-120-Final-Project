import java.awt.*;

public class Bullet extends MoveableObj {

	MoveableObj shooter;
	boolean bulletForward;
	private boolean friendly;
	
	public Bullet(int px, int py, int vx, int vy, int width, int height, MoveableObj shooter) {
		super(px, py, vx, vy, width, height);
		this.shooter = shooter;
		if (shooter.isForward()) {
			bulletForward = true;
		} else {
			bulletForward = false;
		}
		if (shooter instanceof Player) {
			friendly = true;
		} else {
			friendly = false;
		}
	}

	public boolean isFriendly () {
		return friendly;
	}
	
	@Override
	public void move() {
		setPx(getPx() + getVx());
		setPy(getPy() + getVy());
	}

	@Override
	public void draw(Graphics g) {
		if (friendly) {
			g.drawImage(GameCourt.turtle, getPx(), getPy(), getWidth(), getHeight(), null);
		} else {
			if (bulletForward) {
				g.drawImage(GameCourt.bulletf, getPx(), getPy(), getWidth(), getHeight(), null);
			} else {
				g.drawImage(GameCourt.bulletb, getPx(), getPy(), getWidth(), getHeight(), null);
			}
		}
	}

	@Override
	public void drawRelative(Graphics g, Player p) {
		if (friendly) {
			g.drawImage(GameCourt.turtle, getPx(), getPy(), getWidth(), getHeight(), null);
		} else {
			if (bulletForward) {
				g.drawImage(GameCourt.bulletf, getPxr(), getPyr(), getWidth(), getHeight(), null);
			} else {
				g.drawImage(GameCourt.bulletb, getPxr(), getPyr(), getWidth(), getHeight(), null);
			}
		}
	}
}