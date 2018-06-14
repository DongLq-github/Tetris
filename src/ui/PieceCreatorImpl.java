package ui;

import java.awt.Image;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import util.ImageUtil;

public class PieceCreatorImpl {
	
	int COLOR_SIZE = 5;
	int SQUARE_SIZE = 5;//�󷽿���������
	Random random = new Random();
	private Map<Integer, Image> images = new HashMap<Integer, Image>();
	
	//��map�еõ�ͼƬ�������map��û�д���ͼƬ����, �򴴽�
	private Image getImage(int key) {
		if (this.images.get(key) == null) {
			Image s = ImageUtil.getImage("res/square" + key + ".jpg");
			this.images.put(key, s);
		}
		return this.images.get(key);
	}
	
//	������������õ�һ�ŷ���ͼƬ
//	Image image = getImage(random.nextInt(COLOR_SIZE));
	

	//��ʼ��һ��Piece����
	private Piece initPiece(Image image) {
		Piece piece = null;
		int pieceType = random.nextInt(SQUARE_SIZE);
		//��ʼ��Piece��������������󷽿�
		if (pieceType == 0) {
			piece = new Piece0(image);
		} else if (pieceType == 1) {
			piece = new Piece1(image);
		} else if (pieceType == 2){
			piece = new Piece2(image);
		}else if (pieceType == 3){
			piece = new Piece3(image);
		} else if(pieceType == 4){
			piece = new Piece4(image);
		}
		//��������Piece����
		return piece;
	}

	public Piece createPiece(int x, int y) {
		//����õ�һ�ŷ���ͼƬ
		Image image = getImage(random.nextInt(COLOR_SIZE));
		Piece piece = initPiece(image);
		piece.setSquaresXLocation(x);
		piece.setSquaresYLocation(y);
		return piece;
	}



}
