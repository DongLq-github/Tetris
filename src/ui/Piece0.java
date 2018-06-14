package ui;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

public class Piece0 extends Piece {
	
//	Image image = new ImageIcon("/����˹����/res/square1.jpg").getImage();
	
	public Piece0(Image image) {
		//��������С���飬һ������Ϊһ�ֱ仯
		List<Square> squares = new ArrayList<Square>();
		squares.add(new Square(image, 0, 0));
		squares.add(new Square(image, 0, image.getHeight(null)));
		squares.add(new Square(image, image.getWidth(null), 0));
		squares.add(new Square(image, image.getWidth(null), image.getHeight(null)));
		//���뵽�仯��
		super.changes.add(squares);
		super.setSquares(squares);
	}
}

