package pql;

import java.awt.Graphics;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JPanel;

import pql.action.Action;
import pql.action.BackgroundAction;
import pql.action.BoomAction;
import pql.action.BuffAction;
import pql.action.BulletAction;
import pql.action.HPAction;
import pql.action.PlaneAction;
import pql.action.ScoreAction;
import pql.thread.MainThread;

public class MainPanel extends JPanel{
	
	public MainThread mainThread = null;
	public Map<String, Action> actions = new LinkedHashMap<String, Action>();
	
	
	public MainPanel() {
		actions.put("BackgroundAction", new BackgroundAction());
		actions.put("ScoreAction", new ScoreAction());
		actions.put("PlaneAction", new PlaneAction(this));
		actions.put("BulletAction", new BulletAction(this));
		actions.put("BuffAction", new BuffAction(this));		
		actions.put("BoomAction", new BoomAction(this));
		actions.put("HPAction", new HPAction(this));
		
		
		mainThread = new MainThread(this);
		mainThread.start();
	}
	
	@Override
	public void paint(Graphics g) {		
		super.paint(g);
		Iterator ite = actions.entrySet().iterator();
		while(ite.hasNext()) {
			Entry e       = (Entry) ite.next();
			Action action = (Action) e.getValue();
			action.drawAction(g);
		}		
	}
	
}
