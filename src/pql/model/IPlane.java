package pql.model;

import java.awt.Graphics;
import java.awt.Image;

public interface IPlane {
	public void init(String path, int speed, int score);
	public void route();
	public void draw(Graphics g);
	public int getX();
	public int getY();
	public int getScore();
	public Image getImg();
}
