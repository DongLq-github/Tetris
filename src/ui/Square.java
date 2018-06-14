package ui;

import java.awt.Image;

public class Square {
	//方块的图片
	private Image image;
	//开始横坐标
	private int beginX;
	//开始纵坐标
	private int beginY;
	//省略setter和getter方法
	

	
	public Square(Image image, int beginX,int beginY){
		this.image = image;
		this.beginX = beginX;//左上角X
		this.beginY = beginY;//左上角Y
	}

	public Square(int i, int j) {
		this.beginY = j;
		this.beginX = i;
	}

	public int getBeginX() {
		return beginX;
	}

	public void setBeginX(int beginX) {
		this.beginX = beginX;
	}

	public int getBeginY() {
		return beginY;
	}

	public void setBeginY(int beginY) {
		this.beginY = beginY;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}
	
	
}

