package pql.model;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import pql.action.BulletAction;
import pql.action.PlaneAction;
import pql.util.Config;
import wcr.PointPath;

public class HeroBullet_1 implements IBullet {
	// FIXME: 修改硬编码速度值 
	public int speed = 4;
	public BulletAction bulletAction = null;
	public Image img = new ImageIcon(Config.HEROBULLET).getImage();
	private HeroPlane heroPlane = null;
	
	public HeroBullet_1(BulletAction b) {
		this.bulletAction = b;
		PlaneAction planeAction = (PlaneAction)this.bulletAction.mainPanel.actions.get("PlaneAction");
		this.heroPlane = planeAction.hero;
	}
	
	@Override
	public void add() {
		Point p1 = new Point();
		Point p2 = new Point();
		// FIXME: 修改硬编码偏移量		
		p1.x = heroPlane.x + 4;
		p1.y = heroPlane.y;
		p2.x = heroPlane.x + heroPlane.width/2 + 4;
		p2.y = heroPlane.y;
		bulletAction.heroBullets.add(p1);
		bulletAction.heroBullets.add(p2);
	}
	
	@Override
	public void draw(Graphics g) {
		for(int i=0; i<bulletAction.heroBullets.size(); i++) {
			Point p = bulletAction.heroBullets.get(i);
			g.drawImage(img, p.x, p.y, null);
		}
	}
	
	@Override
	public void route() {
		for(int i=0; i<bulletAction.heroBullets.size(); i++) {
			Point p = bulletAction.heroBullets.get(i);
			p.y -= this.speed;
			if(p.y <= 0) {
				bulletAction.heroBullets.remove(i);
			}
		}
	}
	@Override
	public Image getImg() {
		return this.img;
	}
}
