package wcr;

import javax.swing.JFrame;

public class GameJFrame extends JFrame {

	public GameJFrame(){
		this.setTitle("主菜单");
		this.setBounds(500, 100, 400, 600);
		GameJPanel pjp = new GameJPanel();
		pjp.runD();
		pjp.runEnermy();
		add(pjp);
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		new GameJFrame();
	}

}