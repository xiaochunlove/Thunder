package pql.action;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import pql.util.Config;

public class BackgroundAction implements Action{
	public int x      = 0;
	public int y      = 0;
	public int height = 0;
	public int width  = 0;
	public Image img  = null;
	
	public BackgroundAction() {
		this.img    = new ImageIcon(Config.BACKGROUND).getImage();
		this.height = this.img.getHeight(null);
		this.width  = this.img.getWidth(null);
		this.y      = Config.BGHEIGHT - this.height;
	}
	
	
	@Override
	public void doAction() {
		this.y += Config.BGSPEED;
		if(this.y >= 0) {
			this.y = Config.BGHEIGHT - this.height;
		}
	}
	
	@Override
	public void drawAction(Graphics g) {
		g.drawImage(img, x, y, null);
	}

}
