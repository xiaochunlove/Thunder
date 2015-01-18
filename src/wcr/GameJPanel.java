package wcr;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class GameJPanel extends JPanel implements MouseMotionListener{
	
	Image bgImage;      //背景图片
	Image heroImage;    //战机图片
	Image ziDanImage;   //战机子弹图片
	Image bulletImage;  //敌机子弹图片
	
	Thread rund = null; //定义线程
	
	final List<PointPath> points = new ArrayList<PointPath>();   //战机子弹集
	List<PointPath>       bullets= new ArrayList<PointPath>();	 //敌机子弹集
	List<EnemyPlane>      planes = new ArrayList<EnemyPlane>();	 //敌机集
	List<Boom>            booms  = new ArrayList<Boom>();        //爆炸图片集
	
	//背景坐标
	int bx = 0;     
	int by = -5400;
	
	//战机坐标
	int hx = 150;
	int hy = 500;
	//血条
	Healthy heal = new Healthy(); 
	//存储碰撞敌机的ID
	int colEnemy = -1;
	//存储敌机的Id
	int enemyId = 0;
	
	int a = 0;
	
	public GameJPanel(){
		//引入图片
		bgImage    = new ImageIcon("images/bk.jpg").getImage();
		heroImage  = new ImageIcon("images/hero.GIF").getImage();
		ziDanImage = new ImageIcon("images/dijizidan.gif").getImage();
		bulletImage= new ImageIcon("images/bullet5.png").getImage();
		
		addMouseMotionListener(this);
		addMouseListener(new MouseAdapter(){
			//鼠标按下发射子弹
			public void mousePressed(MouseEvent e){				
				PointPath p = new PointPath(hx, hy);
				PointPath p1 = new PointPath(hx + 25, hy);
				points.add(p);
				points.add(p1);
			}
		});
	}
	
	//绘画方法
	public void paint(Graphics g){
		super.paint(g);
		
		g.drawImage(bgImage, bx, by, null);
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.RED);
		g2.setStroke(new BasicStroke(10));
		//画血条
		int healthy = heal.heal;
		int width;
		if(healthy == 3) {
			width = 46;
			g2.drawLine(hx, hy+53+15, hx+width, hy+53+15);
		}
		else if(healthy == 2) {
			width = 20;
			g2.drawLine(hx, hy+53+15, hx+width, hy+53+15);
		}
		else if(healthy == 1) {
			width = 5;
			g2.drawLine(hx, hy+53+15, hx+width, hy+53+15);
		}		
		//画战机
		g.drawImage(heroImage, hx, hy, null);
		
		//循环画出战机子弹
		for (int i = 0; i< points.size(); i++) {
			g.drawImage(ziDanImage, points.get(i).getX(), points.get(i).getY(), null);
		}
		//循环画出敌机
		for (int i = 0; i < planes.size(); i++) {
			planes.get(i).drawPlane(g);
		}
		//循环画出敌机子弹
		for (int i = 0; i< bullets.size(); i++) {
			g.drawImage(bulletImage, bullets.get(i).getX(), bullets.get(i).getY(), null);
		}
		//循环画出爆炸的图片
		for(int i=0; i<booms.size(); i++) {
			Boom boom = booms.get(i);
			g.drawImage(boom.getBoomImage(), boom.getX(), boom.getY(), 40, 40, null);
			if(boom.isFinished == 1) {
				booms.remove(i);
			}
		}
		//游戏结束
		if(a == 1){
			Font f = new Font("黑体", Font.BOLD, 60);
			g.setFont(f);
			g.setColor(Color.RED);
			g.drawString("Game Over", 50, 300);
			rund.stop();
		}
	}
	
	//定义一个方法实现敌机随机的产生
	public void runEnermy() {
		new Thread() {
			public void run() {
				while(true) {
					EnemyPlane ep = new EnemyPlane(enemyId);
					PointPath p1 = new PointPath(ep.getX(), ep.getY());
					enemyId++;
					EnemyPlane ep2 = new EnemyPlane(enemyId);
					PointPath p2 = new PointPath(ep2.getX(), ep2.getY());
					enemyId++;
					
					bullets.add(p1);
					bullets.add(p2);
					planes.add(ep);
					planes.add(ep2);
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}.start();
	}
	//定义一个方法实现多线程
	public void runD(){
		rund =  new Thread(){
			public void run(){
				while(true){
					//战机子弹的发射
					for (int i = 0; i < points.size(); i++) {
						int re = points.get(i).pointRun();
						if(re == 0) {
							points.remove(i);
						}
					}
					//敌机子弹的发射
					for (int i = 0; i < bullets.size(); i++) {
						int re = bullets.get(i).bulletRun();
						if(re == 0) {
							bullets.remove(i);
						}
					}
					//敌机的销毁
					for (int i = 0; i < planes.size(); i++) {
						int re = planes.get(i).runPlane(points,hx,hy,heroImage);
						if(re == 0) {
							planes.remove(i);
						}
						if(re == 2) {
							booms.add(new Boom(planes.get(i).getX(), planes.get(i).getY()));
							planes.remove(i);
						}
						if(re == 3){
							booms.add(new Boom(planes.get(i).getX(), planes.get(i).getY()));
							planes.remove(i);
							if(colEnemy != planes.get(i).getId()) {
								synchronized (heal) {
									heal.heal--;
								}
							}							
							colEnemy = planes.get(i).getId();							
							
							System.out.println(heal.heal);
							
							if(heal.heal <= 0) {								
								a = 1;
							}							
						}
					}
					//背景的移动
					by++;
					if(by == 0){
						by = -5400;
					}
					repaint();
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			} 
		};
		rund.start();
	}
	
	//鼠标拖拽
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	//鼠标移动
	@Override
	public void mouseMoved(MouseEvent e) {
		hx = e.getX() - heroImage.getWidth(null) / 2;
		hy = e.getY() - heroImage.getHeight(null) / 2;
	}
}
