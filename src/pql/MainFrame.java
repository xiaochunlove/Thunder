package pql;

import javax.swing.JFrame;

import pql.util.Config;


public class MainFrame extends JFrame {
	// 这是注释
	public MainFrame() {
		this.setTitle("主菜单");
		this.setBounds(500, 100, Config.BGWIDTH, Config.BGHEIGHT);
		
		MainPanel mainPanel = new MainPanel();
		this.add(mainPanel);
		
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		new MainFrame();
	}
}
