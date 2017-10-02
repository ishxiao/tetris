/*
Created on Thu May 18 18:21:43 2017

file: Tetris.java

@author: Xiao Shang
~Email~: me@ishxiao.com

run:

javac .\Tetris.java -encoding utf8
java Tetris
*/

import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.KeyEvent;
//import java.awt.event.KeyListener;
import javax.swing.*;
//import javax.swing.Timer;
//import Tetris;

// define whether the debug message is output
//boolean DEBUG = true;

public class TetrisApp extends JFrame {
	private static final long serialVersionUID = 1L;
	public TetrisApp() {
		Tetris a = new Tetris();
		addKeyListener(a);
		add(a);
	}

	public static void main(String[] args) {
		TetrisApp frame = new TetrisApp();
		JMenuBar menu = new JMenuBar();
		frame.setJMenuBar(menu);
		JMenu game = new JMenu("Game");
//		JMenuItem newgame = game.add("New Game");
//		JMenuItem pause = game.add("Pause");
//		JMenuItem resume = game.add("Resume");
//		JMenuItem exit = game.add("Exit");
		JMenu help = new JMenu("Help");
//		JMenuItem about = help.add("About");
		menu.add(game);
		menu.add(help);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
//		Dimension   screenSize   =   Toolkit.getDefaultToolkit().getScreenSize();        
//		int screenWidth = (int) screenSize.getWidth();
//		int screenHeight = (int) screenSize.getHeight();
		int dpi = Toolkit.getDefaultToolkit().getScreenResolution();
		int origin_dpi = 96;
		double scale = 1.0*dpi/origin_dpi;
		//int tdpi = (int)(dpi/72.0+0.5);
		//int fwidth = (int) Math.round(220*tdpi);
		//int fheight = (int) Math.round(250*tdpi);
		// iPhone 5 320x568 @2x
		// iPhone 6 375x667 @2x
		// iPhone plus 414x736 @3x
		int fwidth = (int) Math.round(300*scale);
		int fheight = (int) Math.round(400*scale);
		//frame.setSize(220, 275);
		frame.setSize(fwidth, fheight);
		frame.setTitle("Tetris");
		// frame.setUndecorated(true);
		frame.setVisible(true);
		frame.setResizable(false);

	}
}