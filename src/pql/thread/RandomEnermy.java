package pql.thread;

import pql.action.PlaneAction;
import pql.model.IPlane;
import pql.model.SEnermyPlane;
import pql.model.StraEnermyPlane;
import pql.util.Config;

public class RandomEnermy extends Thread {
	private PlaneAction planeAction = null;
	
	public RandomEnermy(PlaneAction p) {
		this.planeAction = p;
	}
	
	@Override
	public void run() {
		while(true) {
			StraEnermyPlane ener1 = new StraEnermyPlane();
			StraEnermyPlane ener2 = new StraEnermyPlane();
			random(ener1);
			random(ener2);
			ener1.x = (int)(Math.random() * 300);
			ener1.y = -40;
			ener2.x = (int)(Math.random() * 300);
			ener2.y = -40;
			planeAction.enermies.add(ener1);
			planeAction.enermies.add(ener2);
			
			if((int)(Math.random()*4) == 2) {
				SEnermyPlane ener = new SEnermyPlane();
				random(ener);
				ener.x = (int)(Math.random() * 300);
				ener.y = -40;
				planeAction.enermies.add(ener);
			}
			
			try {
				Thread.sleep(1200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	public void random(IPlane ener) {
		int e = (int)(Math.random() * 4) + 2;
		String path = Config.ENERMYPLANE_1;
		int speed   = Config.ENERMYSPEED_1;
		int score   = Config.ENERMYSCORE_1;
		switch(e) {
			case 2:
				path = Config.ENERMYPLANE_1;
				speed = Config.ENERMYSPEED_1;
				score = Config.ENERMYSCORE_1;
				break;
			case 3:
				path = Config.ENERMYPLANE_2;
				speed = Config.ENERMYSPEED_2;
				score = Config.ENERMYSCORE_2;
				break;
			case 4:
				path = Config.ENERMYPLANE_3;
				speed = Config.ENERMYSPEED_3;
				score = Config.ENERMYSCORE_3;
				break;
			case 5:
				path = Config.ENERMYPLANE_4;
				speed = Config.ENERMYSPEED_4;
				score = Config.ENERMYSCORE_4;
				break;
			case 6:
				path = Config.ENERMYPLANE_5;
				speed = Config.ENERMYSPEED_5;
				score = Config.ENERMYSCORE_5;
				break;
		}
		ener.init(path, speed, score);
	}

}
