import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.ImageIO;

public class Coin extends Entity {

    public static final String IMG_FILE = "files/Coin.png";
    private static BufferedImage img;
    
	
	public Coin(int px, int py, int width, int height) {
		super(px, py, width, height);
		
        try {
            if (img == null) {
                img = ImageIO.read(new File(IMG_FILE));
            }
        } catch (IOException e) {
            System.out.println("Internal Error:" + e.getMessage());
        }
	}

	
	@Override
	public void draw(Graphics g) {
		g.drawImage(img, getPx(), getPy(), getWidth(), getHeight(), null);
	}

	@Override
	public void drawRelative(Graphics g, Player p) {
		g.drawImage(img, getPxr(), getPyr(), getWidth(), getHeight(), null);
	}
}