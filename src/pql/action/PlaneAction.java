package pql.action;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import pql.MainPanel;
import pql.model.HeroPlane;
import pql.model.IPlane;
import pql.model.StraEnermyPlane;
import pql.thread.RandomEnermy;
import pql.util.Config;

public class PlaneAction implements Action {
	private MainPanel mainPanel   = null;
	public  HeroPlane hero        = new HeroPlane();
	public  List<IPlane> enermies = new ArrayList<IPlane>();
	private Thread randomAdd      = null;
	
	
	public PlaneAction(MainPanel m) {
		this.mainPanel = m;
		
		m.addMouseMotionListener(new MouseAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				hero.x = e.getX() - hero.width / 2;
				hero.y = e.getY() - hero.height / 2;
			}
		});
		
		randomAdd = new RandomEnermy(this);
		randomAdd.start();
	}
	
	@Override
	public void doAction() {
		for(int i=0; i<enermies.size(); i++) {
			enermies.get(i).route();
			if(enermies.get(i).getY() > Config.BGHEIGHT) {
				enermies.remove(i);
			}
		}
	}

	@Override
	public void drawAction(Graphics g) {
		g.drawImage(hero.img, hero.x, hero.y, null);
		for(int i=0; i<enermies.size(); i++) {
			enermies.get(i).draw(g);
		}
	}
}
