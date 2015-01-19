package pql.action;

import java.awt.Graphics;

/**
 * 把所有要处理的事件抽象为一个Action借口
 * 
 * @author chunlan
 * @Time 2015-1-18 10:30:00
 */
 
public interface Action {
	
	/**
	 * 该方法用于处理所有要完成的一系列的动作
	 * 
	 * @param color按钮的颜色
	 * @return void
	 * @exception  
	 * @author chunlan
	 * @Time 2015-1-18 10:30:00
	 */
	 
	public void doAction();
	
	/**
	 * 该方法用于在面板中绘出方法内的所有实体
	 * 
	 * @param g 画笔
	 * @return void
	 * @exception  
	 * @author chunlan
	 * @Time 2015-1-18 10:30:00
	 */
	 
	public void drawAction(Graphics g);
}
