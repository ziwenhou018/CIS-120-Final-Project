import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import java.util.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class GameCourt extends JPanel {
	private Set<Tile> tiles = new TreeSet<Tile>();
	private Set<Coin> coins = new TreeSet<Coin>();
	private Set<Robot> robots = new TreeSet<Robot>();
	private Set<Bullet> bullets = new TreeSet<Bullet>();
	private String[][] startingBoard;
	public static int GAME_WIDTH = 1600;
	public static int GAME_HEIGHT = 900;
	
	public final static int M = 75; //multiplier to scale the array
	
	//The gravity constant
	private final int GRAVITY = 1;
	
	private Player p = new Player(0, 0, 0, 0, M * 1 - 2, M * 2 - 2);
	private Bowser b = new Bowser(5000, 5000, 5, 5, 3 * M, 3 * M, this, p);
	private int maxTime = 2000;
	private int time = maxTime;
	private int money = 0;
	boolean win = false;
	
	private int speedUpgrade = 0;
	private int speedUpgradeCost = 1;
	private int jumpUpgrade = 0;
	private int jumpUpgradeCost = 1;
	private int timeUpgrade = 0;
	private int timeUpgradeCost = 1;
	private int livesUpgradeCost = 5;
	private int multiplier = 1;
	private int multiplierUpgradeCost = 10;
	
    static String woodFile = "files/Wooden_Block.PNG";
    static String cloudFile = "files/cloud.png";
	static String brickFile = "files/brick.jpg";
	static String bulletfFile = "files/bulletf.png";
	static String bulletbFile = "files/bulletb.png";
	static String turtleFile = "files/turtle.JPG";
	String shopImgFile = "files/Newhome.png";
	String skateFile = "files/Skate.png";
	String frogFile = "files/frog.jpg";
	String clockFile = "files/clock.png";
	String heartFile = "files/heart.png";
	String moneyImgFile = "files/money.png";
	String playFile = "files/play.png";
	String buyFile = "files/buy.jpg";
	String cantbuyFile = "files/cantbuy.jpg";
	String soldoutFile = "files/soldout.jpg";

	static BufferedImage wood;
	static BufferedImage cloud;
	static BufferedImage brick;
    static BufferedImage bulletf;
    static BufferedImage bulletb;
    static BufferedImage turtle;
	BufferedImage shopImg;
	BufferedImage skate;
	BufferedImage frog;
	BufferedImage clock;
	BufferedImage heart;
	BufferedImage moneyImg;
	BufferedImage play;
	BufferedImage buy;
	BufferedImage cantbuy;
	BufferedImage soldout;
	
	public GameCourt (String[][] board) {
		try {
            if (wood == null) {
                wood = ImageIO.read(new File(woodFile));
            }
        } catch (IOException e) {
            System.out.println("Internal Error:" + e.getMessage());
        }
		try {
            if (cloud == null) {
                cloud = ImageIO.read(new File(cloudFile));
            }
        } catch (IOException e) {
            System.out.println("Internal Error:" + e.getMessage());
        }
		try {
            if (brick == null) {
                brick = ImageIO.read(new File(brickFile));
            }
        } catch (IOException e) {
            System.out.println("Internal Error:" + e.getMessage());
        }
		try {
            if (bulletf == null) {
                bulletf = ImageIO.read(new File(bulletfFile));
            }
        } catch (IOException e) {
            System.out.println("Internal Error:" + e.getMessage());
        }
		try {
            if (bulletb == null) {
                bulletb = ImageIO.read(new File(bulletbFile));
            }
        } catch (IOException e) {
            System.out.println("Internal Error:" + e.getMessage());
        }
		try {
            if (turtle == null) {
                turtle = ImageIO.read(new File(turtleFile));
            }
        } catch (IOException e) {
            System.out.println("Internal Error:" + e.getMessage());
        }
		try {
            if (shopImg == null) {
            	shopImg = ImageIO.read(new File(shopImgFile));
            }
        } catch (IOException e) {
            System.out.println("Internal Error:" + e.getMessage());
        }
		try {
            if (skate == null) {
            	skate = ImageIO.read(new File(skateFile));
            }
        } catch (IOException e) {
            System.out.println("Internal Error:" + e.getMessage());
        }
		try {
            if (frog == null) {
            	frog = ImageIO.read(new File(frogFile));
            }
        } catch (IOException e) {
            System.out.println("Internal Error:" + e.getMessage());
        }
		try {
            if (clock == null) {
            	clock = ImageIO.read(new File(clockFile));
            }
        } catch (IOException e) {
            System.out.println("Internal Error:" + e.getMessage());
        }
		try {
            if (heart == null) {
            	heart = ImageIO.read(new File(heartFile));
            }
        } catch (IOException e) {
            System.out.println("Internal Error:" + e.getMessage());
        }
		try {
            if (moneyImg == null) {
                moneyImg = ImageIO.read(new File(moneyImgFile));
            }
        } catch (IOException e) {
            System.out.println("Internal Error:" + e.getMessage());
        }
		try {
            if (play == null) {
                play = ImageIO.read(new File(playFile));
            }
        } catch (IOException e) {
            System.out.println("Internal Error:" + e.getMessage());
        }
		try {
            if (buy == null) {
                buy = ImageIO.read(new File(buyFile));
            }
        } catch (IOException e) {
            System.out.println("Internal Error:" + e.getMessage());
        }
		try {
            if (cantbuy == null) {
                cantbuy = ImageIO.read(new File(cantbuyFile));
            }
        } catch (IOException e) {
            System.out.println("Internal Error:" + e.getMessage());
        }
		try {
            if (soldout == null) {
                soldout = ImageIO.read(new File(soldoutFile));
            }
        } catch (IOException e) {
            System.out.println("Internal Error:" + e.getMessage());
        }
		
		startingBoard = board;
		reset();
		Timer timer = new Timer(10, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tick();
			}
		});
		timer.start();

		setFocusable(true);
		
		addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    p.setVx(-1 * p.getVxMax());
                    p.setForward(false);
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                	p.setVx(p.getVxMax());
                	p.setForward(true);
                } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                	if (p.getJumps() > 0) {
                		p.setVy(-1 * p.getVyMax());
                		p.setJumps(p.getJumps() - 1);
                	}
                } 
            }

            public void keyReleased(KeyEvent e) {
            	if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            		p.setVx(0);
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                	p.setVx(0);
                }
            }
        });
		
		addMouseListener(new MouseAdapter() {
	        public void mouseClicked(MouseEvent me) { 
	        	if (!p.isAlive()) {
	        		if (me.getX() >= 600 
	        				&& me.getX() <= 800
	        				&& me.getY() >= 225
	        				&& me.getY() <= 325
	        				&& money >= speedUpgradeCost
	        				&& speedUpgrade < 8) {
		        		speedUpgrade++;
		        		money = money - speedUpgradeCost;
		        		switch (speedUpgrade) {
		        			case 1:		speedUpgradeCost = 2;
			        					p.setVxMax(3);
										break;
			        		case 2:		speedUpgradeCost = 3;
										p.setVxMax(5);
										break;
							case 3:		speedUpgradeCost = 8;
										p.setVxMax(7);
										break;
							case 4:		speedUpgradeCost = 18;
										p.setVxMax(9);
										break;
							case 5:		speedUpgradeCost = 33;
										p.setVxMax(11);
										break;
							case 6:		speedUpgradeCost = 54;
										p.setVxMax(13);
										break;
							case 7:		speedUpgradeCost = 80;
					        			p.setVxMax(15);
										break;
		        		}
		        	}
	        		if (me.getX() >= 600 
		        			&& me.getX() <= 800
		        			&& me.getY() >= 425
		        			&& me.getY() <= 525
		        			&& money >= jumpUpgradeCost
		        			&& jumpUpgrade < 8) {
		        		jumpUpgrade++;
		        		money = money - jumpUpgradeCost;
		        		switch (jumpUpgrade) {
			        		case 1:		jumpUpgradeCost = 2;
				    					p.setVyMax(3);
										break;
		        			case 2:		jumpUpgradeCost = 3;
		        						p.setVyMax(5);
		        						break;
		        			case 3:		jumpUpgradeCost = 10;
		        						p.setVyMax(10);
    									break;
		        			case 4:		jumpUpgradeCost = 25;
		        						p.setVyMax(15);
    									break;
		        			case 5:		jumpUpgradeCost = 50;
		        						p.setVyMax(20);
		        						break;
		        			case 6:		jumpUpgradeCost = 80;
		        						p.setVyMax(22);
		        						p.setMaxJumps(p.getMaxJumps() + 1);
    									break;
		        			case 7:		jumpUpgradeCost = 120;
					        			p.setVyMax(25);
			    						p.setMaxJumps(p.getMaxJumps() + 1);
    									break;
		        			default:	break;
		        			}
		        	}
	        		if (me.getX() >= 600 
		        			&& me.getX() <= 800
		        			&& me.getY() >= 625
		        			&& me.getY() <= 725
		        			&& money >= timeUpgradeCost
		        			&& timeUpgrade < 10) {
		        		timeUpgrade++;
		        		money = money - timeUpgradeCost;
		        		switch (timeUpgrade) {
		        		case 1:		timeUpgradeCost = 2;
		        					maxTime = 4000;
									break;
	        			case 2:		timeUpgradeCost = 4;
	        						maxTime = 7000;
	        						break;
	        			case 3:		timeUpgradeCost = 8;
									maxTime = 11000;
									break;
	        			case 4:		timeUpgradeCost = 15;
									maxTime = 15000;
									break;
	        			case 5:		timeUpgradeCost = 27;
									maxTime = 22000;
									break;
	        			case 6:		timeUpgradeCost = 40;
									maxTime = 30000;
									break;
	        			case 7:		timeUpgradeCost = 55;
									maxTime = 40000;
									break;
	        			case 8:		timeUpgradeCost = 72;
									maxTime = 54000;
									break;
	        			case 9:		timeUpgradeCost = 180;
									maxTime = 80000;
									break;
	        			default:	break;
	        			}
		        	}
	        		if (me.getX() >= 1400 
		        			&& me.getX() <= 1600
		        			&& me.getY() >= 225
		        			&& me.getY() <= 325
		        			&& money >= livesUpgradeCost
		        			&& p.getMaxLives() < 5) {
		        		p.setMaxLives(p.getMaxLives() + 1);
		        		money = money - livesUpgradeCost;
		        		switch (p.getMaxLives()) {
	        			case 2:		livesUpgradeCost = 20;
	        						break;
	        			case 3:		livesUpgradeCost = 50;
									break;
	        			case 4:		livesUpgradeCost = 100;
									break;
	        			default:	break;
	        			}
		        	}
	        		if (me.getX() >= 1400 
		        			&& me.getX() <= 1600
		        			&& me.getY() >= 625
		        			&& me.getY() <= 725
		        			&& money >= multiplierUpgradeCost
		        			&& multiplier < 5) {
		        		multiplier++;
		        		money = money - multiplierUpgradeCost;
		        		switch (multiplier) {
		        			case 2:		multiplierUpgradeCost = 20;
		        						break;
		        			case 3:		multiplierUpgradeCost = 50;
		        						break;
		        			case 4:		multiplierUpgradeCost = 120;
    									break;
		        			default:	break;
		        		}
		        	}
	        		if (me.getX() >= 775 
		        			&& me.getX() <= 925
		        			&& me.getY() >= 750
		        			&& me.getY() <= 900) {
		        		reset();
		        	}
	        	}
	        }
		});
	}
	
	public void tick () {
		if (b.getLives() == 0) {
			win = true;
		} else {
			if (time == 0 || p.getLives() == 0) { //do later
				p.setAlive(false);
				p.setInvincible(0);
				time = 0;
			}
			if (p.isInvincible()) {
				p.setInvincible(p.getInvincible() - 1);
			}
			if (b.isInvincible()) {
				b.setInvincible(b.getInvincible() - 1);
			}
			
			//Checks all collisions
			if (p.isAlive()) {
				boolean move = true;
				if (p.intersects(b) && !b.isInvincible()) {
					b.setLives(b.getLives() - 1);
					b.setInvincible(100);
				}
				Iterator<Coin> coinsIter = coins.iterator();
				while (coinsIter.hasNext()) {
					Coin coin = coinsIter.next();
					if (p.intersects(coin)) {
						coinsIter.remove();
						money = money + multiplier;
					}
				}
				Iterator<Bullet> bulletIter = bullets.iterator();
				while (bulletIter.hasNext()) {
					Bullet bullet = bulletIter.next();
					if (p.intersects(bullet)) {
						if (!(p.isInvincible())) {
							if (!bullet.isFriendly()) {
								p.setLives(p.getLives() - 1);
								p.setInvincible(300);
								bulletIter.remove();
							}
						}
					}
					for (Tile tile: tiles) {
						if (bullet.willIntersect(tile)) {
							bulletIter.remove();
						}
					}
				}
				Iterator<Robot> robotIter = robots.iterator();
				while (robotIter.hasNext()) {
					Robot robot = robotIter.next();
					if (p.intersects(robot)) {
						if (!(p.isInvincible())) {
							p.setLives(p.getLives() - 1);
							p.setInvincible(300);
						}
					}
					Iterator<Bullet> bulletIter2 = bullets.iterator();
					while (bulletIter2.hasNext()) {
						Bullet bullet = bulletIter2.next();
						if (robot.intersects(bullet)) {
							if (bullet.isFriendly()) {
								bulletIter.remove();
								robotIter.remove();
								money = money + 3 * multiplier;
							}
						}
					}
				}
				for (Tile tile: tiles) {
					if (p.willIntersect(tile)) {
						move = false;
						if (p.getVy() > 0) {
							for (int i = p.getVy() - 1; i > 0; i--) {
								p.setVy(i);
								if (!p.willIntersect(tile)) {
									move = true;
									break;
								}
							} 
						}
						else {
							for (int i = p.getVy() + 1; i < 0; i++) {
								p.setVy(i);
								if (!p.willIntersect(tile)) {
									move = true;
									break;
								}
							}
						}
						if (p.getVx() > 0) {
							for (int i = p.getVx() - 1; i > 0; i--) {
								p.setVx(i);
								if (!p.willIntersect(tile)) {
									move = true;
									break;
								}
							}
						}
						else {
							for (int i = p.getVx() + 1; i < 0; i++) {
								p.setVx(i);
								if (!p.willIntersect(tile)) {
									move = true;
									break;
								}
							}
						}
						break;
					}
				}
				
				if (move) {
					p.move();
				}
				
				if (onFloor()) {
					p.setJumps(p.getMaxJumps());
					p.setVy(0);
				} else {
					p.setVy(p.getVy() + GRAVITY);
				}
				
				p.updateRelative(p);
				for (Tile tile: tiles) {
					tile.updateRelative(p);
				}
				for (Coin coin: coins) {
					coin.updateRelative(p);
				}
				for (Robot robot: robots) {
					if (robot.getType() == 3) {
						robot.shoot(this);
					} else {
						robot.move();
					}
					robot.updateRelative(p);	
				}
				for (Bullet bullet: bullets) {
					bullet.move();
					bullet.updateRelative(p);
				}
				b.move();
				b.updateRelative(p);
				
				if (Math.abs(p.getPx() - b.getPx()) < 1400
						&& Math.abs(p.getPy() - b.getPy()) < 700) {
					b.setAwakened(true);
				}
			}
			if (time > 0) {
				time = time - 10;
			}
		}
		repaint();
	} 
	
	//Checks if player is on the floor
	private boolean onFloor () {
		boolean value = false;
		for (Tile tile: tiles) {
			for (int i = p.getPx(); i <= p.getPx() + p.getWidth(); i ++) {
				if (tile.pointExists(i , p.getPy() + p.getHeight() + 1)) {
					return true;
				}
			}
		}
		return value;
	}
	
	//Resets everything
	private void reset () {
		tiles = new TreeSet<Tile>();
		coins = new TreeSet<Coin>();
		robots = new TreeSet<Robot>();
		bullets = new TreeSet<Bullet>();
		p.setAlive(true);
		time = maxTime;
		p.setLives(p.getMaxLives());
		for (int y = 0; y < startingBoard.length; y++) {
			for (int x = 0; x < startingBoard[y].length; x++) {
				switch (startingBoard[y][x]) {
					case "P":	p.setPx(M * x);
								p.setPy(M * y);
								break;
					case "W":	Tile wood = new Tile(M * x, M * y, M * 1, M * 1, 0);
								tiles.add(wood);
								break;
					case "*":	Tile cloud = new Tile(M * x, M * y, M * 1, M * 1, 1);
								tiles.add(cloud);
								break;
					case "B":	Tile brick = new Tile(M * x, M * y, M * 1, M * 1, 2);
								tiles.add(brick);
								break;
					case "C": 	Coin c = new Coin(M * x, M * y, M * 1, M * 1);
								coins.add(c);
								break;
					case "1":	Robot r1 = new Robot(M * x, M * y, 3, 0, 1 * M, 2 * M, 1, 100);
								robots.add(r1);
								break;
					case "2":	Robot r2 = new Robot(M * x, M * y, 0, 3, 1 * M, 2 * M, 2, 100);
								robots.add(r2);
								break;
					case "3":	Robot r3 = new Robot(M * x, M * y, 0, 0, 1 * M, 2 * M, 3, 100);
								robots.add(r3);
								break;
					case "S":	b = new Bowser(M * x, M * y, 0, 0, 3 * M, 3 * M, this, p);
								break;
					default:	break;
				}
			}
		}
	}
	
	public void shootBullet (int px, int py, int vx, int vy, int width, int height, MoveableObj shooter) {
		Bullet b = new Bullet(px, py, vx, vy, width, height, shooter);
		bullets.add(b);
	}
	
	public void paintComponent (Graphics g) {
		if (b.getLives() == 0) {
			g.setFont(new Font("SANS_SERIF", Font.PLAIN, 36));
			g.setColor(Color.BLUE);
			g.drawString("You win!!!", 600, 400);
		} else {
			if (p.isAlive()) {
				super.paintComponent(g);
				p.drawRelative(g, p);
				for (Tile tile: tiles) {
					tile.drawRelative(g, p);			
				}
				for (Coin coin: coins) {
					coin.drawRelative(g, p);			
				}
				for (Robot robot: robots) {
					robot.drawRelative(g, p);				
				}
				for (Bullet bullet: bullets) {
					bullet.drawRelative(g, p);			
				}
				b.drawRelative(g, p);
			} else {
				g.drawImage(shopImg, 0, 0, GAME_WIDTH, GAME_HEIGHT, null);
				g.drawImage(skate, 50, 200, 100, 100, null);
				g.drawImage(frog, 50, 400, 100, 100, null);
				g.drawImage(clock, 50, 600, 100, 100, null);
				g.drawImage(heart, 850, 200, 100, 100, null);
				g.drawImage(moneyImg, 850, 600, 100, 100, null);
				g.drawImage(play, 775, 750, 150, 150, null);
				
				if (speedUpgrade == 8) {
					g.drawImage(soldout, 600, 225, 200, 100, null);
				}
				else if (money < speedUpgradeCost) {
					g.drawImage(cantbuy, 600, 225, 200, 100, null);
				} else {
					g.drawImage(buy, 600, 225, 200, 100, null);
				}
				if (jumpUpgrade == 8) {
					g.drawImage(soldout, 600, 425, 200, 100, null);
				}
				else if (money < jumpUpgradeCost) {
					g.drawImage(cantbuy, 600, 425, 200, 100, null);
				} else {
					g.drawImage(buy, 600, 425, 200, 100, null);
				}
				if (timeUpgrade == 10) {
					g.drawImage(soldout, 600, 625, 200, 100, null);
				}
				else if (money < timeUpgradeCost) {
					g.drawImage(cantbuy, 600, 625, 200, 100, null);
				} else {
					g.drawImage(buy, 600, 625, 200, 100, null);
				}
				if (p.getMaxLives() == 5) {
					g.drawImage(soldout, 1400, 225, 200, 100, null);
				}
				else if (money < livesUpgradeCost) {
					g.drawImage(cantbuy, 1400, 225, 200, 100, null);
				} else {
					g.drawImage(buy, 1400, 225, 200, 100, null);
				}
				if (multiplier == 5) {
					g.drawImage(soldout, 1400, 625, 200, 100, null);
				}
				else if (money < multiplierUpgradeCost) {
					g.drawImage(cantbuy, 1400, 625, 200, 100, null);
				} else {
					g.drawImage(buy, 1400, 625, 200, 100, null);
				}
				
				g.setColor(Color.WHITE);
				g.fillRect(200, 200, 350, 150);
				g.fillRect(200, 400, 350, 150);
				g.fillRect(200, 600, 350, 150);
				g.fillRect(1000, 200, 350, 150);
				g.fillRect(1000, 600, 350, 150);
				g.setColor(Color.BLACK);
				g.setFont(new Font("SANS_SERIF", Font.PLAIN, 24));
				g.drawString("Boots let you run faster!", 220, 250);
				g.drawString("Speed Boost: " + speedUpgrade + "/8", 220, 300);
				g.drawString("Cost: " + speedUpgradeCost, 220, 330);
				g.drawString("Frogs let you jump higher!", 220, 450);
				if (jumpUpgrade >= 6) {
					g.setColor(Color.BLUE);
					g.drawString("Upgrade allows you to multijump", 220, 470);
					g.setColor(Color.BLACK);
				}
				g.drawString("Jump Boost: " + jumpUpgrade + "/8", 220, 500);
				g.drawString("Cost: " + jumpUpgradeCost, 220, 530);
				g.drawString("Clocks allow you to", 220, 650);
				g.drawString("last longer", 220, 670);
				g.drawString("Time Boost: " + timeUpgrade + "/10", 220, 700);
				g.drawString("Cost: " + timeUpgradeCost, 220, 730);
				g.drawString("Hearts let you take more", 1020, 250);
				g.drawString("damage", 1020, 270);
				g.drawString("Current Lives: " + p.getMaxLives() + "/5", 1020, 300);
				g.drawString("Cost: " + livesUpgradeCost, 1020, 330);
				g.drawString("Multipler allows you to", 1020, 650);
				g.drawString("get more money per coin", 1020, 670);
				g.drawString("Current Multiplier: " + multiplier + "/5", 1020, 700);
				g.drawString("Cost: " + multiplierUpgradeCost, 1020, 730);
			}
			
			g.setColor(Color.BLACK);
			g.setFont(new Font("SANS_SERIF", Font.PLAIN, 48));
			g.drawString("Coins: " + money, 40, 80);
			if (p.isInvincible()) {
				g.setColor(Color.YELLOW);
			}
			g.drawString("Lives: " + p.getLives(), 440, 80);
			g.setColor(Color.BLACK);
			if (b.isInvincible()) {
				g.setColor(Color.YELLOW);
			}
			if (b.isAwakened()) {
				 
				g.drawString("Bowser HP: " + b.getLives(), 840, 80);
			}
			g.setColor(Color.BLACK);
			if (time < (maxTime * 2 / 5) || time < 5000) {
				g.setColor(Color.RED);
				if (time % 1000 < 400) {
					g.setColor(Color.BLACK);
				}
			}
			g.drawString("Time: " + (time / 1000), 1240, 80);
		}
	}
	
    public Dimension getPreferredSize() {
        return new Dimension(GAME_WIDTH, GAME_HEIGHT);
    }
}