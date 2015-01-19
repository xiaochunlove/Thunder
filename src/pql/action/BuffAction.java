package pql.action;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import pql.util.Config;

public class BuffAction implements Action {
	private int bufftime = 0;
	private int x = 0;
	private int y = 0;
	private int type = 0;
	private Image img = new ImageIcon(Config.BUFF).getImage();
	
	
	@Override
	public void doAction() {
		this.y += 4;
		if(this.y >= Config.BGHEIGHT) {
			random();
		}
	}
	
	@Override
	public void drawAction(Graphics g) {
		g.drawImage(img, x, y, x+28, y+24, 0, 24*(type-1), 28, 24*type, null);
	}

	
	
	private void random() {
		this.type = (int)(Math.random() * 3) + 1;
		this.x = (int)(Math.random() * (Config.BGWIDTH - 40)) + 20;
		this.y = -Config.BUFFTIME;		
	}
	
	
}
