package ui;

import java.awt.Image;

public class Square {
	//�����ͼƬ
	private Image image;
	//��ʼ������
	private int beginX;
	//��ʼ������
	private int beginY;
	//ʡ��setter��getter����
	

	
	public Square(Image image, int beginX,int beginY){
		this.image = image;
		this.beginX = beginX;//���Ͻ�X
		this.beginY = beginY;//���Ͻ�Y
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

