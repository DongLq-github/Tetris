package ui;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

public class Piece0 extends Piece {
	
//	Image image = new ImageIcon("/俄罗斯方块/res/square1.jpg").getImage();
	
	public Piece0(Image image) {
		//创建各个小方块，一个集合为一种变化
		List<Square> squares = new ArrayList<Square>();
		squares.add(new Square(image, 0, 0));
		squares.add(new Square(image, 0, image.getHeight(null)));
		squares.add(new Square(image, image.getWidth(null), 0));
		squares.add(new Square(image, image.getWidth(null), image.getHeight(null)));
		//加入到变化中
		super.changes.add(squares);
		super.setSquares(squares);
	}
}

