package pql.action;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import pql.util.Config;

/**
 * 背景处理类实现处理接口
 * 
 * @author chunlan
 * @Time 2015-1-18 10:30:00
 */
 
public class BackgroundAction implements Action{
	public int x      = 0;					//背景x坐标
	public int y      = 0;					//背景y坐标
	public int height = 0;					//背景图片的高度
	public int width  = 0;					//背景图片的宽度
	public Image img  = null;				//背景图片
	
	/**
	 * 背景处理类的构造函数，初始化背景图片，背景图片的宽度和高度以及背景的坐标y
	 * 
	 */
	 
	public BackgroundAction() {
		this.img    = new ImageIcon(Config.BACKGROUND).getImage();
		this.height = this.img.getHeight(null);
		this.width  = this.img.getWidth(null);
		this.y      = Config.BGHEIGHT - this.height;
	}
	
	/**
	 * 该方法用于修改背景图片的y坐标以及判断y坐标是否超出边界
	 * 
	 * @return void
	 * @exception  
	 * @author chunlan
	 * @Time 2015-1-18 10:30:00
	 */
	 
	@Override
	public void doAction() {
		this.y += Config.BGSPEED;
		if(this.y >= 0) {
			this.y = Config.BGHEIGHT - this.height;
		}
	}
	
	/**
	 * 该方法用于画背景图片
	 * 
	 * @return void
	 * @exception  
	 * @author chunlan
	 * @Time 2015-1-18 10:30:00
	 */
	 
	@Override
	public void drawAction(Graphics g) {
		g.drawImage(img, x, y, null);
	}

}
