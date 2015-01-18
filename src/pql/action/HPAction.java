package pql.action;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import pql.MainPanel;
import pql.util.Config;

public class HPAction implements Action {
	private MainPanel mainPanel = null;
	private PlaneAction planeAction = null;
	private Image img = new ImageIcon(Config.HEROPLANE).getImage();
	
	public HPAction(MainPanel m) {
		this.mainPanel = m;
		this.planeAction = (PlaneAction) m.actions.get("PlaneAction");
	}
	
	@Override
	public void doAction() {
		
	}
	
	@Override
	public void drawAction(Graphics g) {
		
		if(planeAction.hero.hp <= 0) {
			Font f = new Font("黑体", Font.BOLD, 60);
			g.setFont(f);
			g.setColor(Color.RED);
			g.drawString("Game Over", 30, 300);
			mainPanel.mainThread.isRun = false;
			return;
		}
		
		for(int i=0; i<planeAction.hero.hp; i++) {
			int pix = i * 40;
			g.drawImage(img, 20+pix, Config.BGHEIGHT-60, 30, 30, null);
		}
	}
}
