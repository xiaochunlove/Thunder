package pql.model;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import pql.util.Config;

public class HeroPlane implements IPlane{
	public int      x = 100;
	public int      y = 500;
	public Image  img = null;
	public int height = 0;
	public int  width = 0;
	public int     hp = Config.HP;
	
	public HeroPlane() {
		this.img    = new ImageIcon(Config.HEROPLANE).getImage();
		this.height = img.getHeight(null);
		this.width  = img.getWidth(null);
	}
	
	
	@Override
	public void draw(Graphics g) {
		g.drawImage(img, x, y, null);
	}
	
	@Override
	public int getX() {
		return this.x;
	}
	
	@Override
	public int getY() {
		return this.y;
	}

	@Override
	public void route() {}


	@Override
	public void init(String path, int speed, int score) {}
	
	@Override
	public Image getImg() {
		return this.img;
	}
	
	@Override
	public int getScore() {
		return 0;
	}
}
