package pql.model;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import pql.util.Config;

public class SEnermyPlane implements IPlane{
	public int speed = 2;
	public int score = 5;
	public int     x = 0;
	public int     y = 0;
	public Image img = null;
	public int height = 0;
	public int  width = 0;
	
	public void init(String p, int speed, int score) {
		this.speed  = speed;
		this.img    = new ImageIcon(p).getImage();
		this.height = img.getHeight(null);
		this.width  = img.getWidth(null);
		this.score = score;
	}
	
	@Override
	public void draw(Graphics g) {
		g.drawImage(img, x, y, null);
	}
	
	
	@Override
	public void route() {
		this.y += this.speed;
		this.x = 200 + (int) (Math.sin(y*Math.PI/300) * Config.BGWIDTH * 0.2);
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
	public Image getImg() {
		return this.img;
	}
	
	@Override
	public int getScore() {
		return this.score;
	}
}
