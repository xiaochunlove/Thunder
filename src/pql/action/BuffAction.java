package pql.action;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import pql.MainPanel;
import pql.model.HeroPlane;
import pql.model.Point;
import pql.util.Config;
import pql.util.util;

public class BuffAction implements Action {
	private PlaneAction planeAction = null;
	private int bufftime = 0;
	private int x = 0;
	private int y = 0;
	private int type = 0;
	private Image img = new ImageIcon(Config.BUFF).getImage();
	private int height = img.getHeight(null) / 3;
	private int width = img.getWidth(null);
	
	
	public BuffAction(MainPanel m) {
		this.planeAction = (PlaneAction) m.actions.get("PlaneAction");
	}
	
	@Override
	public void doAction() {
		HeroPlane heroPlane = planeAction.hero;
		
		double dis = (heroPlane.height + height + heroPlane.width + width) / 4;
		int X = x + width / 2;
		int Y = y + height / 2;
		
		if(util.getDis(heroPlane.x, heroPlane.y, X, Y) < dis) {
			random();
			return;
		}
		
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
