package ui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

import util.ImageUtil;

public class GamePanel extends JPanel {
	
//	Image background = ImageUtil.getImage("res/background.jpg");
	Image gameover = ImageUtil.getImage("res/gameover.jpg");
//	Image dongtu = ImageUtil.getImage("res/dongtai.gif");
//	ImageIcon dongtu = new ImageIcon("res/dongtai.gif");
	
	public void paint(Graphics g) {
		
		if(this.mainFrame.gameover){
			g.drawImage(this.gameover, 0, 0, this.getWidth(), 
					this.getHeight() , null);
		}
		else{
//			g.drawImage(background, 0, 0, this.getWidth(), 
//					this.getHeight() , null);
			Piece currentPiece = this.mainFrame.getCurrentPiece();
			ImageUtil.paintPiece(g, currentPiece);
			//绘画小方块的二维数组
			Square[][] squares = this.mainFrame.getSquares();
			if (squares == null) return;
			for (int i = 0; i < squares.length; i++) {
				for (int j = 0; j < squares[i].length; j++) {
					Square s = squares[i][j];
					if (s != null) {
						g.drawImage(s.getImage(), s.getBeginX(), s.getBeginY(), this);
					}
				}
			}
		}
			
	}
		



	MainFrame mainFrame;
	public GamePanel(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		this.setOpaque(false);		//设置透明
	}

	
	
	
}
