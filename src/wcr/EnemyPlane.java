package wcr;

import java.awt.Graphics;
import java.awt.Image;
import java.util.List;

import javax.swing.ImageIcon;

public class EnemyPlane {
	private int id;
	//敌机图片
	public Image enemyImage = null;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	//敌机坐标
	private int x;
	private int y;
	//图片代号
	private int e; 
	
	public EnemyPlane(int id) {
		this.id = id;
		x = (int)(Math.random() * 300);
		y = - 40;
		e = (int)(Math.random() * 4) + 2;
		enemyImage = new ImageIcon("images/e" + e + ".png").getImage();
	}
	//定义一个方法绘画敌机
	public void drawPlane(Graphics g){
		g.drawImage(enemyImage, x, y, null);
	}
	//定义一个方法移动敌机
	public int runPlane(List< PointPath > points, int hx, int hy , Image img){
		if(y >= 600) {
			return 0;
		}
		
		//检测飞机是否碰撞
		int c = x + enemyImage.getWidth(null) / 2;
		int b = y + enemyImage.getHeight(null) / 2;
		int s = (int)(Math.sqrt((c - hx) * (c - hx) + (b - hy) * (b - hy)));
		
		if( s < ( enemyImage.getWidth(null) / 2 + img.getWidth(null) / 2 ) ){
			return 3;
		}
		//子弹是否打中敌机
		for(int i = 0; i < points.size(); i++) {
			PointPath p = points.get(i);
			if( (this.x - 13 < p.getX() && p.getX() < this.x+enemyImage.getWidth(null) ) &&
					(p.getY() <= this.y + enemyImage.getHeight(null)) ) {
				points.remove(p);
				return 2;
			}
		}
		
		switch(e){
		case 2:
			y += 4;
			break;
		case 3:
			y += 5;
			break;
		case 4:
			y += 4;
			break;
		case 5:
			y += 7;
			break;
		case 6:
			y += 9;
			break;
		}
		
		return 1;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
}
