package pql.action;

import java.awt.Graphics;
import java.awt.Image;
import java.io.Console;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import pql.MainPanel;
import pql.model.HeroPlane;
import pql.model.IPlane;
import pql.model.Point;
import pql.util.Config;
import pql.util.util;

public class BoomAction implements Action {
	private PlaneAction planeAction = null;
	private BulletAction bulletAction = null;
	private ScoreAction scoreAction = null;
	private int bulletWidth = 0;
	private List<Point> booms = new ArrayList<Point>();
	private Image boomimg_1 = new ImageIcon(Config.BOOM_1).getImage();
	private Image boomimg_2 = new ImageIcon(Config.BOOM_2).getImage();
	
	public BoomAction(MainPanel m) {
		this.planeAction = (PlaneAction)m.actions.get("PlaneAction");
		this.bulletAction = (BulletAction)m.actions.get("BulletAction");
		this.scoreAction = (ScoreAction)m.actions.get("ScoreAction");
		this.bulletWidth = bulletAction.heroBullet.getImg().getWidth(null);
	}
	
	@Override
	public void doAction() {
		
		for(int i = 0; i<booms.size(); i++) {
			Point p = booms.get(i);
			if(p.boomNum <= Config.BOOMNUM) {
				p.boomNum++;
			}
			else {
				booms.remove(i);
			}
		}
		
		for(int i=0; i<planeAction.enermies.size(); i++) {
			IPlane enermy = planeAction.enermies.get(i);
			for(int j=0; j<bulletAction.heroBullets.size(); j++) {
				Point p = bulletAction.heroBullets.get(j);

				int length = p.y - (enermy.getY() + enermy.getImg().getHeight(null));
				if( (enermy.getX() - bulletWidth < p.x && p.x < enermy.getX() + enermy.getImg().getWidth(null) ) &&
						(Math.abs(length) < 6) ) {
					scoreAction.socre += enermy.getScore();
					
					Point boom = new Point();
					boom.x = enermy.getX();
					boom.y = enermy.getY();
					booms.add(boom);
					
					bulletAction.heroBullets.remove(j);
					if(i<planeAction.enermies.size()) {
						planeAction.enermies.remove(i);
					}
				}
			}
		}
		
		HeroPlane heroPlane = planeAction.hero;
		for(int i=0; i<planeAction.enermies.size(); i++) {
			IPlane enermy = planeAction.enermies.get(i);
			double dis = (heroPlane.height + enermy.getImg().getHeight(null) + heroPlane.width + enermy.getImg().getWidth(null)) / 4;
			int enermyX = enermy.getX() + enermy.getImg().getWidth(null) / 2;
			int enermyY = enermy.getY() + enermy.getImg().getHeight(null) / 2;
			
			if(util.getDis(heroPlane.x, heroPlane.y, enermyX, enermyY) <dis ) {
				heroPlane.hp--;
				Point boom = new Point();
				boom.x = enermy.getX();
				boom.y = enermy.getY();
				booms.add(boom);
				
				planeAction.enermies.remove(i);
			}
		}
		
		
		for(int i=0; i<bulletAction.enermyBullets.size(); i++) {
			Point p = bulletAction.enermyBullets.get(i);
			int length = p.y - (heroPlane.getY() + heroPlane.getImg().getHeight(null));
			if( (heroPlane.getX() - bulletWidth < p.x && p.x < heroPlane.getX() + heroPlane.getImg().getWidth(null) ) &&
					(Math.abs(length) < 6) ) {
				Point boom = new Point();
				boom.x = heroPlane.getX();
				boom.y = heroPlane.getY();
				booms.add(boom);
				
				heroPlane.hp--;
				bulletAction.enermyBullets.remove(i);
			}
		}
	}

	@Override
	public void drawAction(Graphics g) {
		
		for(int i = 0; i<booms.size(); i++) {
			Point p = booms.get(i);
			if(p.boomNum <= Config.BOOMNUM / 2 ) {
				g.drawImage(boomimg_1, p.x, p.y, Config.BOOMWIDTH, Config.BOOMHEIGHT, null);
			}
			else {
				g.drawImage(boomimg_2, p.x, p.y, Config.BOOMWIDTH, Config.BOOMHEIGHT, null);
			}
		}
	}
}
