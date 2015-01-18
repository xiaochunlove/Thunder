package wcr;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Boom {
	//爆炸的坐标
	private int x;
	private int y;
	//爆炸的图片
	private Image boomImage = null;
	//爆炸是否完成
	public int isFinished = 0;
	
	public Boom(int x, int y) {
		this.x = x;
		this.y = y;
		new Thread() {
			public void run() {
				boomImage = new ImageIcon("images/db1.gif").getImage();
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				boomImage = new ImageIcon("images/db2.gif").getImage();
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				isFinished = 1;
			}			
		}.start();
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

	public Image getBoomImage() {
		return boomImage;
	}

	public void setBoomImage(Image boomImage) {
		this.boomImage = boomImage;
	}
}
