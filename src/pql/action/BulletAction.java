package pql.action;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import pql.MainPanel;
import pql.model.EnermyBullet;
import pql.model.HeroBullet_1;
import pql.model.HeroBullet_2;
import pql.model.HeroBullet_3;
import pql.model.IBullet;
import pql.model.Point;

/**
 * 子弹处理类实现Action借口
 * 
 * @author chunlan
 * @Time 2015-1-18 10:30:00
 */
public class BulletAction implements Action{
	public MainPanel mainPanel = null;
	private PlaneAction planeAction = null;
	public List<Point> heroBullets = new ArrayList<Point>();			//英雄机子弹集
	public List<Point> enermyBullets = new ArrayList<Point>();			//敌机子弹集
	public IBullet heroBullet = null;
	public IBullet enermyBullet = null;
	
	/**
	 * 子弹处理类的构造函数，初始化飞机处理类，子弹处理类和分数处理类以及子弹宽度
	 *  
	 * @param m 面板
	 */
	public BulletAction(MainPanel m) {
		this.mainPanel = m;
		this.heroBullet = new HeroBullet_1(this);
		this.enermyBullet = new EnermyBullet(this);
		this.planeAction = (PlaneAction) mainPanel.actions.get("PlaneAction");
		
		mainPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				heroBullet.add();
			}
		});
	}
	
	/**
	 * 该方法内通过调用英雄机子弹的路径方法以及敌机子弹的添加和路径方法来绘制子弹的运动轨迹
	 * 
	 * @return void
	 * @exception  
	 * @author chunlan
	 * @Time 2015-1-18 10:30:00
	 */
	@Override
	public void doAction() {
		heroBullet.route();
		enermyBullet.add();
		enermyBullet.route();
	}
	
	/**
	 * 该方法内通过调用英雄机子弹和敌机子弹的绘图方法来绘制子弹
	 * 
	 * @param g 画笔
	 * @return void
	 * @exception  
	 * @author chunlan
	 * @Time 2015-1-18 10:30:00
	 */
	@Override
	public void drawAction(Graphics g) {
		heroBullet.draw(g);
		enermyBullet.draw(g);
	}
	
	/**
	 * 该方法设置子弹的类型
	 * 
	 * @param type 类型
	 * @return void
	 * @exception  
	 * @author chunlan
	 * @Time 2015-1-18 10:30:00
	 */
	public void setHeroBullet(int type) {
		if(type == 1) {
			this.heroBullet = new HeroBullet_1(this);
		}
		if(type == 2) {
			this.heroBullet = new HeroBullet_2(this);
		}
		if(type == 3) {
			this.heroBullet = new HeroBullet_3(this);
		}
	}
}
