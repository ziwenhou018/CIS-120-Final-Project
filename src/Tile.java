import java.awt.*;

public class Tile extends Entity {
	
	private int tileId;
	
	public Tile(int px, int py, int width, int height, int tileCode) {
		super(px, py, width, height);
		
		tileId = tileCode;
	}

	@Override
	public void draw(Graphics g) {
		switch (tileId) {
			case 0:		g.drawImage(GameCourt.wood, getPx(), getPy(), getWidth(), getHeight(), null);	
						break;
			case 1:		g.drawImage(GameCourt.cloud, getPx(), getPy(), getWidth(), getHeight(), null);	
						break;
			case 2:		g.drawImage(GameCourt.brick, getPx(), getPy(), getWidth(), getHeight(), null);	
			default: 	break;
		}
	}
	
	@Override
	public void drawRelative(Graphics g, Player p) {
		switch (tileId) {
			case 0:		g.drawImage(GameCourt.wood, getPxr(), getPyr(), getWidth(), getHeight(), null);	
						break;
			case 1:		g.drawImage(GameCourt.cloud, getPxr(), getPyr(), getWidth(), getHeight(), null);	
						break;
			case 2:		g.drawImage(GameCourt.brick, getPxr(), getPyr(), getWidth(), getHeight(), null);	
			default: 	break;
		}
			
	}
}