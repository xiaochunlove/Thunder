package pql.action;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class ScoreAction implements Action{
	public int socre = 0;
	
	@Override
	public void doAction() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void drawAction(Graphics g) {
		Font f = new Font("黑体", Font.BOLD, 20);
		g.setFont(f);
		g.setColor(Color.RED);
		g.drawString("Score: " + socre, 270, 30);
	}
	
}
