package ui;

import java.awt.Image;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import util.ImageUtil;

public class PieceCreatorImpl {
	
	int COLOR_SIZE = 5;
	int SQUARE_SIZE = 5;//大方块类型数量
	Random random = new Random();
	private Map<Integer, Image> images = new HashMap<Integer, Image>();
	
	//从map中得到图片对象，如果map中没有存在图片对象, 则创建
	private Image getImage(int key) {
		if (this.images.get(key) == null) {
			Image s = ImageUtil.getImage("res/square" + key + ".jpg");
			this.images.put(key, s);
		}
		return this.images.get(key);
	}
	
//	可以这样随机得到一张方块图片
//	Image image = getImage(random.nextInt(COLOR_SIZE));
	

	//初始化一个Piece对象
	private Piece initPiece(Image image) {
		Piece piece = null;
		int pieceType = random.nextInt(SQUARE_SIZE);
		//初始化Piece，随机创建各个大方块
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
		//加入其他Piece对象
		return piece;
	}

	public Piece createPiece(int x, int y) {
		//随机得到一张方块图片
		Image image = getImage(random.nextInt(COLOR_SIZE));
		Piece piece = initPiece(image);
		piece.setSquaresXLocation(x);
		piece.setSquaresYLocation(y);
		return piece;
	}



}
