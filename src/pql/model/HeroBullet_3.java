package pql.model;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import pql.action.BulletAction;
import pql.action.PlaneAction;
import pql.util.Config;

public class HeroBullet_3 implements IBullet {
	// FIXME: 修改硬编码速度值 
	public int speed = 4;
	public BulletAction bulletAction = null;
	public Image img = new ImageIcon(Config.ENERMYBULLET).getImage();
	private HeroPlane heroPlane = null;
		
	public HeroBullet_3(BulletAction b) {
		this.bulletAction = b;
		PlaneAction planeAction = (PlaneAction)this.bulletAction.mainPanel.actions.get("PlaneAction");
		this.heroPlane = planeAction.hero;
	}
		
	@Override
	public void add() {
		Point p1 = new Point();
		Point p2 = new Point();
		Point p3 = new Point();
		Point p4 = new Point();
		Point p5 = new Point();
		// FIXME: 修改硬编码偏移量	
		p1.x = heroPlane.x + 20;
		p1.y = heroPlane.y;
		p2.x = heroPlane.x + 20;
		p2.y = heroPlane.y;
		p3.x = heroPlane.x + 20;
		p3.y = heroPlane.y;
		p4.x = heroPlane.x + 20;
		p4.y = heroPlane.y;
		p5.x = heroPlane.x + 20;
		p5.y = heroPlane.y;
		
		p1.direction = 0;
		p2.direction = 1;
		p3.direction = -1;
		p4.direction = 2;
		p5.direction = -2;
			
		bulletAction.heroBullets.add(p1);
		bulletAction.heroBullets.add(p2);
		bulletAction.heroBullets.add(p3);
		bulletAction.heroBullets.add(p4);
		bulletAction.heroBullets.add(p5);
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
			
			if(p.direction == 0) {
				p.y -= this.speed;
			}
				
			if(p.direction == 1) {
				int dis = (int)(this.speed / 1.1); 
				p.y -= dis;
				p.x += this.speed / 2;
			}
				
			if(p.direction == -1) {
				int dis = (int)(this.speed / 1.1); 
				p.y -= dis;
				p.x -= this.speed / 2;
			}
			
			if(p.direction == 2) {
				int dis = (int)(this.speed / 2); 
				p.y -= dis;
				p.x += (int)(dis * 1.8);
			}
				
			if(p.direction == -2) {
				int dis = (int)(this.speed / 2); 
				p.y -= dis;
				p.x -= (int)(dis * 1.8);
			}
				
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
