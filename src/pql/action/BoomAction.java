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

/**
 * 爆炸处理类实现Action借口
 * 
 * @author chunlan
 * @Time 2015-1-18 10:30:00
 */
 
public class BoomAction implements Action {
	private PlaneAction planeAction = null;
	private BulletAction bulletAction = null;
	private ScoreAction scoreAction = null;
	private int bulletWidth = 0;							// 子弹宽度
	private List<Point> booms = new ArrayList<Point>();				// 爆炸集合
	private Image boomimg_1 = new ImageIcon(Config.BOOM_1).getImage();		// 爆炸图片1
	private Image boomimg_2 = new ImageIcon(Config.BOOM_2).getImage();		// 爆炸图片2
	
	/**
	 * 爆炸处理类的构造函数，初始化飞机处理类，子弹处理类和分数处理类以及子弹宽度
	 *  
	 * @param m 面板
	 */
	 
	public BoomAction(MainPanel m) {
		this.planeAction = (PlaneAction)m.actions.get("PlaneAction");
		this.bulletAction = (BulletAction)m.actions.get("BulletAction");
		this.scoreAction = (ScoreAction)m.actions.get("ScoreAction");
		this.bulletWidth = bulletAction.heroBullet.getImg().getWidth(null);
	}
	
	/**
	 * 该方法用于判断booNum是否大于配置类中的参数，英雄机的子弹是否打中敌机，英雄机和敌机是否相撞，以及敌机的子弹是否打中英雄机
	 * 
	 * @return void
	 * @exception  
	 * @author chunlan
	 * @Time 2015-1-18 10:30:00
	 */
	 
	@Override
	public void doAction() {
		
		// 判断booNum是否大于配置类中的参数
		for(int i = 0; i<booms.size(); i++) {
			Point p = booms.get(i);
			if(p.boomNum <= Config.BOOMNUM) {
				p.boomNum++;
			}
			else {
				booms.remove(i);
			}
		}
		
		// 判断英雄机的子弹是否打中敌机
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
		
		// 判断英雄机和敌机是否相撞
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
		
		// 判断敌机的子弹是否打中英雄机
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

	/**
	 * 该方法用于绘画爆炸图片
	 * 
	 * @param g 画笔
	 * @return void
	 * @exception  
	 * @author chunlan
	 * @Time 2015-1-18 10:30:00
	 */
	 
	@Override
	public void drawAction(Graphics g) {
		
		// 根据条件画出爆炸图片
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
