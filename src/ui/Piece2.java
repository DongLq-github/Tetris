package ui;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

public class Piece2 extends Piece {
	
	public Piece2(Image image) {
		//��һ�ֱ仯
		List<Square> squares0 = new ArrayList<Square>();
		squares0.add(new Square(image, image.getWidth(null)*2, 0));
		squares0.add(new Square(image, image.getWidth(null)*2, image.getHeight(null)));
		squares0.add(new Square(image, image.getWidth(null)*2, image.getHeight(null)*2));
		squares0.add(new Square(image, image.getWidth(null)*2, image.getHeight(null)*3));
		//�ڶ��ֱ仯
		List<Square> squares1 = new ArrayList<Square>();
		squares1.add(new Square(image, 0, 0));
		squares1.add(new Square(image, image.getWidth(null), 0));
		squares1.add(new Square(image, image.getWidth(null)*2, 0));
		squares1.add(new Square(image, image.getWidth(null)*3, 0));

		super.changes.add(squares0);
		super.changes.add(squares1);
		//�����ñ仯
		super.setSquares(getDefault());
	}

}

