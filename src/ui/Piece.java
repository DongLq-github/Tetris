package ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Piece {
	
	Random random = new Random();
	
	//当前大方块所包含的小方块
	private List<Square> squares;
	//该大方块的变化
	protected List<List<Square>> changes = new ArrayList<List<Square>>();
	//当前变化的索引（在changes集合中的索引）
	protected int currentIndex;
	//每个小块的边长
	public final static int SQUARE_BORDER = 40;
	public final static int DOWN_HEIGHT = 40;
	
	
	public void setSquares(List<Square> Squares) {
		// 设置新状态
		this.squares = Squares;
	}
	
	
	public List<Square> getSquares() {
		//获得当前状态
		return this.squares;
	}


	public List<Square> getDefault() {
		//从changes集合中随机得到其中一种变化
		int defaultChange = random.nextInt(changes.size());
		//设置当前变化的索引
		this.currentIndex = defaultChange;
		return changes.get(defaultChange);
	}
	
	//让Piece对象中的所有Square对象的x座标都加上参数x
	public void setSquaresXLocation(int x) {
		for (int i = 0; i < this.changes.size(); i++) {
			List<Square> change = this.changes.get(i);
			for (int j = 0; j < change.size(); j++) {
				Square s = change.get(j);
				s.setBeginX(s.getBeginX() + x);
			}
		}
	}
	
	//让Piece对象中的所有Square对象的y座标都加上参数y
	public void setSquaresYLocation(int y) {
		for (int i = 0; i < this.changes.size(); i++) {
			List<Square> change = this.changes.get(i);
			for (int j = 0; j < change.size(); j++) {
				Square s = change.get(j);
				s.setBeginY(s.getBeginY() + y);
			}
		}
	}
	
	public void change(){
		System.out.println("currentIndex:"+this.currentIndex);
		System.out.println("changes.size:"+this.changes.size());
		if (this.changes.size() == 0) return;
		this.currentIndex += 1;
		//如果当前变化超过changes集合的大小, 则从0开始变化
		if (this.currentIndex >= this.changes.size()) this.currentIndex = 0; 
		this.squares = this.changes.get(this.currentIndex);
	}

	//得到当前变化中x座标的最小值
	public int getMinXLocation() {
		int result = Integer.MAX_VALUE;
		System.out.println("Piece 72 this.squares.size():"+this.squares.size());
		for (int i = 0; i < this.squares.size(); i++) {
			Square s = this.squares.get(i);
			if (s.getBeginX() < result) result = s.getBeginX();
		}
		System.out.println("Piece 77: X坐标最小值:"+result);
		return result;
	}
	//得到当前变化中x座标最大的值
	public int getMaxXLocation() {
		int result = 0;
		System.out.println("Piece 82 this.squares.size():"+this.squares.size());
		for (int i = 0; i < this.squares.size(); i++) {
			Square s = this.squares.get(i);
			if (s.getBeginX() > result) result = s.getBeginX();
		}
		System.out.println("Piece 87: X 坐标最大值:"+result + SQUARE_BORDER);
		return result + SQUARE_BORDER;
	}

	//得到当前变化中Y座标最大值
	public int getMaxYLocation() {
		int result = 0;
		for (int i = 0; i < this.squares.size(); i++) {
			Square s = this.squares.get(i);
			if (s.getBeginY() > result) result = s.getBeginY();
		}
		System.out.println("Piece 98: Y 坐标最大值:"+(result + SQUARE_BORDER));
		return result + SQUARE_BORDER;
	}




}

