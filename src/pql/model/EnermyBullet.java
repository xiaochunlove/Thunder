package pql.model;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import pql.action.BulletAction;
import pql.action.PlaneAction;
import pql.util.Config;

public class EnermyBullet implements IBullet {
	// FIXME: 修改硬编码速度值 
	public int speed = 7;
	private BulletAction bulletAction = null;
	private PlaneAction planeAction = null;
	public Image img = new ImageIcon(Config.ENERMYBULLET).getImage();
	
	public EnermyBullet(BulletAction b) {
		this.bulletAction = b;
		this.planeAction = (PlaneAction) bulletAction.mainPanel.actions.get("PlaneAction");
	}
	
	
	@Override
	public void add() {
		
		
		for(int i=0; i<planeAction.enermies.size(); i++) {
			int ran = (int) (Math.random() * 300);
			
			if(ran == 50) {
				Point p = new Point();		
				// FIXME: 修改硬编码偏移量
				p.x = planeAction.enermies.get(i).getX() + planeAction.enermies.get(i).getImg().getWidth(null)/2;
				p.y = planeAction.enermies.get(i).getY();
				bulletAction.enermyBullets.add(p);
			}
		}
	}
	
	
	@Override
	public void draw(Graphics g) {
		for(int i=0; i<bulletAction.enermyBullets.size(); i++) {
			Point p = bulletAction.enermyBullets.get(i);
			g.drawImage(img, p.x, p.y, null);
		}
	}
	
	
	@Override
	public Image getImg() {		
		return img;
	}
	
	
	@Override
	public void route() {
		for(int i=0; i<bulletAction.enermyBullets.size(); i++) {
			Point p = bulletAction.enermyBullets.get(i);
			p.y += this.speed;
			if(p.y >= Config.BGHEIGHT) {
				bulletAction.enermyBullets.remove(i);
			}
		}
	}
}
