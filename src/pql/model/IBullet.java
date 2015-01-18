package pql.model;

import java.awt.Graphics;
import java.awt.Image;

public interface IBullet {
	public void add();
	public void route();
	public void draw(Graphics g);
	public Image getImg();
}
