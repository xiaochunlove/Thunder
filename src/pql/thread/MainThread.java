package pql.thread;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JPanel;

import pql.MainPanel;
import pql.action.Action;
import pql.util.Config;

public class MainThread extends Thread {
	MainPanel mainPanel = null;
	public boolean isRun = true;
	
	public MainThread(MainPanel p) {
		mainPanel = p;
	}
	
	@Override
	public void run() {
		while(isRun) {
			Iterator ite = mainPanel.actions.entrySet().iterator();
			while(ite.hasNext()) {
				Entry e       = (Entry) ite.next();
				Action action = (Action) e.getValue();
				action.doAction();
			}
			
			mainPanel.repaint();
			
			try {
				Thread.sleep(1000/Config.FPS);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
