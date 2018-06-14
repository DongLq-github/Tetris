package ui;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

public class Piece4 extends Piece {
	
	public Piece4(Image image) {
		//第一种变化
		List<Square> squares0 = new ArrayList<Square>();
		squares0.add(new Square(image, 0, 0));
		squares0.add(new Square(image, 0,image.getHeight(null)));
		squares0.add(new Square(image, 0, image.getHeight(null)*2));
		squares0.add(new Square(image, image.getWidth(null), image.getHeight(null)*2));
		//第二种变化
		List<Square> squares1 = new ArrayList<Square>();
		squares1.add(new Square(image, 0,0));
		squares1.add(new Square(image, image.getWidth(null), 0));
		squares1.add(new Square(image, image.getWidth(null)*2, 0));
		squares1.add(new Square(image, 0, image.getHeight(null)));
		//第三种变化
		List<Square> squares2 = new ArrayList<Square>();
		squares2.add(new Square(image, 0, 0));
		squares2.add(new Square(image, image.getWidth(null), 0));
		squares2.add(new Square(image, image.getWidth(null), image.getHeight(null)));
		squares2.add(new Square(image, image.getWidth(null), image.getHeight(null)*2));
		//第四种变化
		List<Square> squares3 = new ArrayList<Square>();
		squares3.add(new Square(image,0, image.getHeight(null)));
		squares3.add(new Square(image, image.getWidth(null), image.getHeight(null)));
		squares3.add(new Square(image, image.getWidth(null)*2, image.getHeight(null)));
		squares3.add(new Square(image, image.getWidth(null)*2, 0));
		
		//第一种变化
		List<Square> squares4 = new ArrayList<Square>();
		squares4.add(new Square(image, 0, 0));
		squares4.add(new Square(image, 0,image.getHeight(null)));
		squares4.add(new Square(image, image.getWidth(null), image.getHeight(null)));
		squares4.add(new Square(image, image.getWidth(null)*2, image.getHeight(null)));
		//第二种变化
		List<Square> squares5 = new ArrayList<Square>();
		squares5.add(new Square(image, 0,0));
		squares5.add(new Square(image, image.getWidth(null), 0));
		squares5.add(new Square(image, 0, image.getHeight(null)*2));
		squares5.add(new Square(image, 0, image.getHeight(null)));
		//第三种变化
		List<Square> squares6 = new ArrayList<Square>();
		squares6.add(new Square(image, 0, image.getHeight(null)*2));
		squares6.add(new Square(image, image.getWidth(null), 0));
		squares6.add(new Square(image, image.getWidth(null), image.getHeight(null)));
		squares6.add(new Square(image, image.getWidth(null), image.getHeight(null)*2));
		//第四种变化
		List<Square> squares7 = new ArrayList<Square>();
		squares7.add(new Square(image,0, 0));
		squares7.add(new Square(image, image.getWidth(null), 0));
		squares7.add(new Square(image, image.getWidth(null)*2, image.getHeight(null)));
		squares7.add(new Square(image, image.getWidth(null)*2, 0));		
		
		super.changes.add(squares0);
		super.changes.add(squares1);
		super.changes.add(squares2);
		super.changes.add(squares3);
		super.changes.add(squares4);
		super.changes.add(squares5);
		super.changes.add(squares6);
		super.changes.add(squares7);
		//随机获得变化
		super.setSquares(getDefault());
	}

}
