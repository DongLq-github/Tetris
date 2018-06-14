package ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Piece {
	
	Random random = new Random();
	
	//��ǰ�󷽿���������С����
	private List<Square> squares;
	//�ô󷽿�ı仯
	protected List<List<Square>> changes = new ArrayList<List<Square>>();
	//��ǰ�仯����������changes�����е�������
	protected int currentIndex;
	//ÿ��С��ı߳�
	public final static int SQUARE_BORDER = 40;
	public final static int DOWN_HEIGHT = 40;
	
	
	public void setSquares(List<Square> Squares) {
		// ������״̬
		this.squares = Squares;
	}
	
	
	public List<Square> getSquares() {
		//��õ�ǰ״̬
		return this.squares;
	}


	public List<Square> getDefault() {
		//��changes����������õ�����һ�ֱ仯
		int defaultChange = random.nextInt(changes.size());
		//���õ�ǰ�仯������
		this.currentIndex = defaultChange;
		return changes.get(defaultChange);
	}
	
	//��Piece�����е�����Square�����x���궼���ϲ���x
	public void setSquaresXLocation(int x) {
		for (int i = 0; i < this.changes.size(); i++) {
			List<Square> change = this.changes.get(i);
			for (int j = 0; j < change.size(); j++) {
				Square s = change.get(j);
				s.setBeginX(s.getBeginX() + x);
			}
		}
	}
	
	//��Piece�����е�����Square�����y���궼���ϲ���y
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
		//�����ǰ�仯����changes���ϵĴ�С, ���0��ʼ�仯
		if (this.currentIndex >= this.changes.size()) this.currentIndex = 0; 
		this.squares = this.changes.get(this.currentIndex);
	}

	//�õ���ǰ�仯��x�������Сֵ
	public int getMinXLocation() {
		int result = Integer.MAX_VALUE;
		System.out.println("Piece 72 this.squares.size():"+this.squares.size());
		for (int i = 0; i < this.squares.size(); i++) {
			Square s = this.squares.get(i);
			if (s.getBeginX() < result) result = s.getBeginX();
		}
		System.out.println("Piece 77: X������Сֵ:"+result);
		return result;
	}
	//�õ���ǰ�仯��x��������ֵ
	public int getMaxXLocation() {
		int result = 0;
		System.out.println("Piece 82 this.squares.size():"+this.squares.size());
		for (int i = 0; i < this.squares.size(); i++) {
			Square s = this.squares.get(i);
			if (s.getBeginX() > result) result = s.getBeginX();
		}
		System.out.println("Piece 87: X �������ֵ:"+result + SQUARE_BORDER);
		return result + SQUARE_BORDER;
	}

	//�õ���ǰ�仯��Y�������ֵ
	public int getMaxYLocation() {
		int result = 0;
		for (int i = 0; i < this.squares.size(); i++) {
			Square s = this.squares.get(i);
			if (s.getBeginY() > result) result = s.getBeginY();
		}
		System.out.println("Piece 98: Y �������ֵ:"+(result + SQUARE_BORDER));
		return result + SQUARE_BORDER;
	}




}

