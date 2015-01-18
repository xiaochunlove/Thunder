package pql.action;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import pql.MainPanel;
import pql.model.EnermyBullet;
import pql.model.HeroBullet_1;
import pql.model.IBullet;
import pql.model.Point;

public class BulletAction implements Action{
	public MainPanel mainPanel = null;
	private PlaneAction planeAction = null;
	public List<Point> heroBullets = new ArrayList<Point>();
	public List<Point> enermyBullets = new ArrayList<Point>();
	public IBullet heroBullet = null;
	public IBullet enermyBullet = null;
	
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
	
	@Override
	public void doAction() {
		heroBullet.route();
		enermyBullet.add();
		enermyBullet.route();
	}
	
	@Override
	public void drawAction(Graphics g) {
		heroBullet.draw(g);
		enermyBullet.draw(g);
	}
}
