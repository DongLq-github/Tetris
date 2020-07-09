package util;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;

import javax.imageio.ImageIO;

import ui.MainFrame;
import ui.Piece;
import ui.Square;

public class ImageUtil {
		
	//�ڽ����ϻ�һ��Piece����
	public static void paintPiece(Graphics g, Piece piece) {
		
		if (piece == null) 
			return;
		
		for (int i = 0; i < piece.getSquares().size(); i++) {
			Square s = piece.getSquares().get(i);
			//�õ�����С����󣬽���ЩС���黭��������
			g.drawImage(s.getImage(), s.getBeginX(), s.getBeginY(), null);
		}
	}
	
	//������������ȡͼƬ
	public static BufferedImage getImage(String imageURL){
//		File file = new File(imageURL);
//		FileInputStream is = null;
//		try {
//		     is = new FileInputStream(file);
//		} catch (FileNotFoundException e2) {
//		     e2.printStackTrace();
//		}
		URL is = ImageUtil.class.getResource(imageURL);
		System.out.println(is.getFile());
		BufferedImage sourceImg = null;
		try {
		     sourceImg = javax.imageio.ImageIO.read(is);
		     System.out.println("����ͼƬ�ɹ�");
		     System.out.println("width:" + sourceImg.getWidth());
		     System.out.println("height:" + sourceImg.getHeight());
		} catch (IOException e1) {
		     e1.printStackTrace();
		}
		return sourceImg;
	}
	
	//�ڽ����ϻ���һ��Piece����
	public static void paintNextPiece(Graphics g, Piece piece) {
		
		if (piece == null) return;
		
		for (int i = 0; i < piece.getSquares().size(); i++) {
			Square s = piece.getSquares().get(i);
			//�õ�����С����󣬽���ЩС���黭��������
			g.drawImage(s.getImage(), s.getBeginX(), s.getBeginY(), null);
		}
	}
	
	public static void paintHuaji(Graphics g,Image image){
		g.drawImage(image, 40, 15,200,200, null);
	}

	
	
	
	
	
	
	
	
	
	
	
	
}
