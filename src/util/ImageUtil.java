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
		
	//在界面上画一个Piece对象
	public static void paintPiece(Graphics g, Piece piece) {
		
		if (piece == null) 
			return;
		
		for (int i = 0; i < piece.getSquares().size(); i++) {
			Square s = piece.getSquares().get(i);
			//得到各个小方块后，将这些小方块画到界面中
			g.drawImage(s.getImage(), s.getBeginX(), s.getBeginY(), null);
		}
	}
	
	//创建输入流读取图片
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
		     System.out.println("读入图片成功");
		     System.out.println("width:" + sourceImg.getWidth());
		     System.out.println("height:" + sourceImg.getHeight());
		} catch (IOException e1) {
		     e1.printStackTrace();
		}
		return sourceImg;
	}
	
	//在界面上画下一个Piece对象
	public static void paintNextPiece(Graphics g, Piece piece) {
		
		if (piece == null) return;
		
		for (int i = 0; i < piece.getSquares().size(); i++) {
			Square s = piece.getSquares().get(i);
			//得到各个小方块后，将这些小方块画到界面中
			g.drawImage(s.getImage(), s.getBeginX(), s.getBeginY(), null);
		}
	}
	
	public static void paintHuaji(Graphics g,Image image){
		g.drawImage(image, 40, 15,200,200, null);
	}

	
	
	
	
	
	
	
	
	
	
	
	
}
