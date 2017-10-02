/*
Created on Thu May 18 18:21:43 2017

file: Tetris.java

@author: ishxiao
~Email~: me@ishxiao.com

run:

javac .\Tetris.java -encoding utf8
java Tetris
*/
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;
import javax.swing.Timer;

// define whether the debug message is output
//boolean DEBUG = true;

// create Tetris classs
public class Tetris extends JPanel implements KeyListener {
	private static final long serialVersionUID = 2L;

	// blockType
	// turnState
	private int blockType;
	private int nextBlockType;
	private int score = 0;

	private int turnState;
	private int nextTurnState;

	private int x;

	private int y;

	private int i = 0;

	int j = 0;
	int flag = 0;
	
	// define map x=0-11,y=0-21
	int[][] map = new int[13][23];

	// block shape
	private final int shapes[][][] = new int[][][] {
	// i
		{ { 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0 },
		  { 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0 },
		  { 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0 },
		  { 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0 } },
	// s
		{ { 0, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
		  { 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 },
		  { 0, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
		  { 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 } },
	// z
		{ { 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
		  { 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0 },
		  { 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
		  { 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0 } },
	// j
		{ { 0, 1, 0, 0, 0, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0 },
		  { 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
		  { 1, 1, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0 },
		  { 1, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 } },
	// o
		{ { 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
		  { 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
		  { 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
		  { 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } },
	// l
		{ { 1, 0, 0, 0, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0 },
		  { 1, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
		  { 1, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 },
		  { 0, 0, 1, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 } },
	// t
		{ { 0, 1, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
		  { 0, 1, 0, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 },
		  { 1, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
		  { 0, 1, 0, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0 } } 
	};
	
	// initialized constructor
	Tetris() {
		newblock();
		newblock();
		newmap();
		drawwall();
		Timer timer = new Timer(1000, new TimerListener());
		timer.start();
	}
	// create next block
	public void nextBlock() {
		nextBlockType = (int) (Math.random() * 1000) % 7;
		nextTurnState = (int) (Math.random() * 1000) % 4;
	}

	// create new block
	public void newblock() {
//		blockType = (int) (Math.random() * 1000) % 7;
//		turnState = (int) (Math.random() * 1000) % 4;
		blockType = nextBlockType;
		turnState = nextTurnState;
		x = 4;
		y = 0;
		if (gameover(x, y) == 1) {

			newmap();
			drawwall();
			score = 0;
			JOptionPane.showMessageDialog(null, "GAME OVER");
		}
		nextBlock();
	}

	// paint wall
	public void drawwall() {
		for (i = 0; i < 12; i++) {
			map[i][21] = 2;
		}
		for (j = 0; j < 22; j++) {
			map[11][j] = 2;
			map[0][j] = 2;
		}
	}

	// initialized map
	public void newmap() {
		for (i = 0; i < 12; i++) {
			for (j = 0; j < 22; j++) {
				map[i][j] = 0;
			}
		}
	}

	// turning method
	public void turn() {
		int tempturnState = turnState;
		turnState = (turnState + 1) % 4;
		if (blow(x, y, blockType, turnState) == 1) {
		}
		if (blow(x, y, blockType, turnState) == 0) {
			turnState = tempturnState;
		}
		repaint();
	}

	// move left
	public void left() {
		if (blow(x - 1, y, blockType, turnState) == 1) {
			x = x - 1;
		}
		;
		repaint();
	}

	// move right
	public void right() {
		if (blow(x + 1, y, blockType, turnState) == 1) {
			x = x + 1;
		}
		;
		repaint();
	}

	// move down 
	public void down() {
		if (blow(x, y + 1, blockType, turnState) == 1) {
			y = y + 1;
			delline();
		}
		;
		if (blow(x, y + 1, blockType, turnState) == 0) {
			add(x, y, blockType, turnState);
			newblock();
			delline();
		}
		;
		repaint();
	}

	// is legal?
	public int blow(int x, int y, int blockType, int turnState) {
		for (int a = 0; a < 4; a++) {
			for (int b = 0; b < 4; b++) {
				if (((shapes[blockType][turnState][a * 4 + b] == 1) && (map[x
						+ b + 1][y + a] == 1))
						|| ((shapes[blockType][turnState][a * 4 + b] == 1) && (map[x
								+ b + 1][y + a] == 2))) {

					return 0;
				}
			}
		}
		return 1;
	}
	
	public void calcScore(int n) {
/*		if (n == 1) {
			score += 10;
		}
		else if (n == 2) {
			score += 40;
		}
		else if (n == 3) {
			score += 80;
		}
		else if (n == 4) {
			score += 160;
		}*/
		if (n == 1) {
			score += 1;
		}
		else if (n > 1) {
			score += Math.scalb(1, n);
		}
	}

	// fixed method
	public void delline() {
		int c = 0;
		int iFullLine = 0;
		for (int b = 0; b < 22; b++) {
			for (int a = 0; a < 12; a++) {
				if (map[a][b] == 1) {
					c = c + 1;
					if (c == 10) {
						iFullLine++;
						for (int d = b; d > 0; d--) {
							for (int e = 0; e < 11; e++) {
								map[e][d] = map[e][d - 1];

							}
						}
					}
				}
			}
			c = 0;
		}
		calcScore(iFullLine);
	}

	// is failed ?
	public int gameover(int x, int y) {
		if (blow(x, y, blockType, turnState) == 0) {
			return 1;
		}
		return 0;
	}

	// added current block
	public void add(int x, int y, int blockType, int turnState) {
		int j = 0;
		for (int a = 0; a < 4; a++) {
			for (int b = 0; b < 4; b++) {
				if (map[x + b + 1][y + a] == 0) {
					map[x + b + 1][y + a] = shapes[blockType][turnState][j];
				}
				;
				j++;
			}
		}
	}
	
	//paint single block
	public void paintSingleComponent(Graphics g, int x, int y) {
		g.drawRect(x, y,   20,   20);
		g.fillRect(x+2, y+2, 20-3, 20-3);
	}

	// paint block
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		// paint current block
		for (j = 0; j < 16; j++) {
			if (shapes[blockType][turnState][j] == 1) {
				paintSingleComponent(g, (j % 4 + x + 1) * 20, (j / 4 + y) * 20);
			}
		}
		// paint solid block
		for (j = 0; j < 22; j++) {
			for (i = 0; i < 12; i++) {
				if (map[i][j] == 1) {
					paintSingleComponent(g, i * 20, j * 20);

				}
				if (map[i][j] == 2) {
					g.drawRect(i * 20, j * 20, 20, 20);

				}
			}
		}
		// paint next block
		for (j = 0; j < 16; j++) {
			if (shapes[nextBlockType][nextTurnState][j] == 1) {
				paintSingleComponent(g, 240 + (j % 4 + 1 + 1)  * 20, (j / 4 + 1) * 20);
			}
		}
		g.drawString("score = " + score, 250, 120);

	}

	// keyboard listener
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_DOWN:
			down();
			break;
		case KeyEvent.VK_UP:
			turn();
			break;
		case KeyEvent.VK_RIGHT:
			right();
			break;
		case KeyEvent.VK_LEFT:
			left();
			break;
		}

	}

	// none
	public void keyReleased(KeyEvent e) {
	}

	// none
	public void keyTyped(KeyEvent e) {
	}

	// timer listener
	class TimerListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			repaint();
			if (blow(x, y + 1, blockType, turnState) == 1) {
				y = y + 1;
				delline();
			}
			;
			if (blow(x, y + 1, blockType, turnState) == 0) {

				if (flag == 1) {
					add(x, y, blockType, turnState);
					delline();
					newblock();
					flag = 0;
				}
				flag = 1;
			}
			;
		}
	}
}
