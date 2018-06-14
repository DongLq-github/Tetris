package ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import util.ImageUtil;

public class MainFrame extends JFrame {
	
	GamePanel gamePanel = new GamePanel(this); //��Ϸ���
	Box toolPanel = Box.createVerticalBox();//�ٿ����
	JPanel scoreTextBox = new JPanel();//��ŷ�����ǩ�����
	JLabel scoreTextLabel = new JLabel("��  ��");//������ǩ
	JPanel scoreBox = new JPanel();//��ŷ��������
	JLabel scoreLabel = new JLabel("0");//��ʾ����
	int score = 0;
	JPanel levelTextBox = new JPanel();//��ż����ǩ�����
	JLabel levelTextLabel = new JLabel("��  ��");//�����ǩ
	JPanel levelBox = new JPanel();//��ż�������
	JLabel levelLabel = new JLabel("1");//��ʾ����
	int currentLevel = 1;
	JPanel resumeBox = new JPanel();//��ż�����ť��С���
	JButton resumeButton = new JButton("��  ��");//������ť
	JPanel pauseBox = new JPanel();//�����ͣ��ť��С���
	JButton pauseButton = new JButton("��  ͣ");//��ͣ��ť
	JPanel startBox = new JPanel();//��ſ�ʼ��ť��С���
	JButton startButton = new JButton("��  ʼ");//��ʼ��ť
	JPanel nextTextBox = new JPanel();//�����һ����ǩ�����
	JLabel nextLabel = new JLabel("  ");//��һ����ť
	JPanel nextPanel = new JPanel();//�����һ���󷽿�����
	Font font1 = new Font("����",Font.BOLD,30);
	Font font2 = new Font("����",Font.BOLD,50);
	Color blue1 = new Color(41,139,204);
	TetrisTask tetrisTask = new TetrisTask(this,this.nextPanel);
	Timer timer = new Timer();
	boolean pauseFlag = false,gameover = false,gamestart = false;
	Square[][] squares;
	Image huaji = ImageUtil.getImage("res/huaji.png");
	
	public MainFrame() {
		
		this.init();
		//����
		this.scoreTextBox.add(this.scoreTextLabel);
		this.scoreLabel.setText(String.valueOf(this.score));
		this.scoreBox.add(this.scoreLabel);
		//����
		this.levelTextBox.add(this.levelTextLabel);
		this.levelLabel.setText(String.valueOf(this.currentLevel));
		this.levelBox.add(this.levelLabel);
		//������ť
		this.resumeBox.add(this.resumeButton);
		//��ͣ��ť
		this.pauseBox.add(this.pauseButton);
		//��ʼ
		this.startBox.add(this.startButton);
		//��һ��
		this.nextTextBox.add(this.nextLabel);
		//ʡ���������ִ���
		
		this.setSize(900, 890);
		this.add(this.gamePanel);
		this.add(this.toolPanel);
		this.setLayout(null);
//		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setTitle("����˹����");
//		this.setUndecorated(true);	//���α�����
		this.setPicBack();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
		//������ť����¼�
		resumeButton.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				resumeButton.setBackground(Color.red);
			}
			public void mouseExited(MouseEvent e) {
				resumeButton.setBackground(blue1);
				resumeButton.setForeground(Color.white);
			}
			public void mouseClicked(MouseEvent e) {
				resume();
			}
		});
		pauseButton.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				pauseButton.setBackground(Color.red);
			}
			public void mouseExited(MouseEvent e) {
				pauseButton.setBackground(blue1);
				pauseButton.setForeground(Color.white);
			}
			public void mouseClicked(MouseEvent e) {
				pause();//����Ϊ��ͣ
				
			}
		});
		startButton.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				startButton.setBackground(Color.red);
			}
			public void mouseExited(MouseEvent e) {
				startButton.setBackground(blue1);
				startButton.setForeground(Color.white);
			}
			public void mouseClicked(MouseEvent e) {
				start();
			}
		});
		
		//��Ӽ��̼�����
		startButton.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				System.out.println("��⵽�û������˼���:"+e.getKeyCode());
				//��
				if (e.getKeyCode() == 38) change();
				//��
				if (e.getKeyCode() == 37) left(1);
				//��
				if (e.getKeyCode() == 39) right(1);
				//��
				if (e.getKeyCode() == 40) down();	
				if (e.getKeyCode() == KeyEvent.VK_ENTER){
					if(pauseFlag) resume();
					else pause();
				}
			}
		});
		//��Ӽ��̼�����
		pauseButton.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				System.out.println("��⵽�û������˼���:"+e.getKeyCode());
				//��
				if (e.getKeyCode() == 38) change();
				//��
				if (e.getKeyCode() == 37) left(1);
				//��
				if (e.getKeyCode() == 39) right(1);
				//��
				if (e.getKeyCode() == 40) down();
				if (e.getKeyCode() == KeyEvent.VK_ENTER){
					if(pauseFlag) resume();
					else pause();
				}
			}
		});
		//��Ӽ��̼�����
		resumeButton.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				System.out.println("��⵽�û������˼���:"+e.getKeyCode());
				//��
				if (e.getKeyCode() == 38) change();
				//��
				if (e.getKeyCode() == 37) left(1);
				//��
				if (e.getKeyCode() == 39) right(1);
				//��
				if (e.getKeyCode() == 40) down();
				//enter��
				if (e.getKeyCode() == KeyEvent.VK_ENTER){
					if(pauseFlag) resume();
					else pause();
				}
			}
		});		
		
	
	}//MainFrame
	
	//����ͼƬ����
	public void setPicBack(){
        String path = "res/2.jpg";  
        // ����ͼƬ  
        ImageIcon background = new ImageIcon(path);  
        // �ѱ���ͼƬ��ʾ��һ����ǩ����  
        JLabel label = new JLabel(background);  
        // �ѱ�ǩ�Ĵ�Сλ������ΪͼƬ�պ�����������  
        System.out.println("����ͼƬ����"+this.getWidth()+this.getHeight());
        label.setBounds(0, 0, this.getWidth(), this.getHeight());  
        // �����ݴ���ǿ��ת��ΪJPanel���������÷���setOpaque()��ʹ���ݴ���͸��  
        JPanel imagePanel = (JPanel) this.getContentPane();  
        //����͸��
        imagePanel.setOpaque(false);  
        // �ѱ���ͼƬ��ӵ��ֲ㴰�����ײ���Ϊ����  
        this.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE)); 
	}
	
	//��ʼ������
	public void init(){
		//gamePanel
		gamePanel.setBounds(0, 0, 600, 840);
		
		//toolPanel
		toolPanel.setBounds(600, 0, 300, 600);
		toolPanel.setBackground(Color.gray);
		toolPanel.setOpaque(false);
		
		//�ɼ����
		scoreTextLabel.setSize(300, 100);
		scoreTextLabel.setFont(font1);
		scoreTextLabel.setForeground(Color.white);
		scoreTextLabel.setOpaque(false);
		scoreTextBox.setOpaque(false);
		toolPanel.add(scoreTextBox);
		
		//�ɼ���ǩ
		scoreLabel.setSize(300, 100);
		scoreLabel.setFont(font2);
		scoreLabel.setForeground(Color.red);
		scoreBox.setOpaque(false);
		toolPanel.add(scoreBox);
		
		//�������
		levelTextLabel.setSize(300, 100);
		levelTextLabel.setFont(font1);
		levelTextLabel.setForeground(Color.white);
		levelTextLabel.setOpaque(false);
		levelTextBox.setOpaque(false);
		toolPanel.add(levelTextBox);
		
		//�����ǩ
		levelLabel.setSize(300, 100);
		levelLabel.setFont(font2);
		levelLabel.setForeground(Color.RED);
		levelBox.setOpaque(false);
		toolPanel.add(levelBox);
		
		//�������
		resumeButton.setFont(font1);
		resumeButton.setBackground(blue1);
		resumeButton.setForeground(Color.white);
		resumeBox.setOpaque(false);
		toolPanel.add(resumeBox);
		
		//��ͣ���
		pauseButton.setFont(font1);
		pauseButton.setBackground(blue1);
		pauseButton.setForeground(Color.white);
		pauseBox.setOpaque(false);
		toolPanel.add(pauseBox);
		
		//��ʼ���
		startButton.setFont(font1);
		startButton.setBackground(blue1);
		startButton.setForeground(Color.white);
		startBox.setOpaque(false);
		toolPanel.add(startBox);
		
		//��һ��
		nextLabel.setFont(font1);
		nextLabel.setForeground(Color.white);
		nextTextBox.setOpaque(false);
		toolPanel.add(nextTextBox);
		
		//��һ���������
		nextPanel.setBounds(600, 600, 300, 300);
		nextPanel.setOpaque(false);
		this.add(nextPanel);
	}
	
	//��ǰ�����˶��Ķ���
	private Piece currentPiece;
	//��һ���󷽿����
	private Piece nextPiece;

	public Piece getCurrentPiece() {
		return this.currentPiece;
	}

	public void setCurrentPiece(Piece currentPiece) {
		this.currentPiece = currentPiece;
	}

	public Piece getNextPiece() {
		return this.nextPiece;
	}

	public void setNextPiece(Piece nextPiece) {
		this.nextPiece = nextPiece;
	}	

	PieceCreatorImpl creator = new PieceCreatorImpl();
	
	//��һ��Piece��λ��
	private final static int NEXT_X = 80;
	private final static int NEXT_Y = 50;
	//��ǰPiece�Ŀ�ʼX����
	private final static int BEGIN_X = Piece.SQUARE_BORDER * 6;
	//��ǰPiece�Ŀ�ʼY����
	private final static int BEGIN_Y = 0;
	//������һ��
	void createNextPiece() {
		this.nextPiece = this.creator.createPiece(NEXT_X, NEXT_Y);
		this.repaint();
	}
	//��ʼ��Ϸ
	public void start() {
		if(gamestart) return;
		gameover = false;
		this.squares = null;
		this.initSquares();
		this.currentLevel = 1;
		this.score = 0;
		this.scoreLabel.setText(String.valueOf(score));
		this.levelLabel.setText(String.valueOf(this.currentLevel));
		this.timer = null;
		this.timer = new Timer();
		nextLabel.setText("��һ��");
		System.out.println();
		
		//������һ���󷽿�
		createNextPiece();
//		ImageUtil.paintNextPiece(nextPanel.getGraphics(), getNextPiece());
		//������ǰ�˶��Ĵ󷽿�
		this.currentPiece = creator.createPiece(BEGIN_X, BEGIN_Y);		
		change();
		//��ʼ����ʱ��
		this.tetrisTask = new TetrisTask(this,this.nextPanel);
		int time = 800 / this.currentLevel;		//�½��ٶ�
		this.timer.schedule(this.tetrisTask, 0, time);
		
		gamestart = true;
	}
	
	//һ��Piece��������½�
	void finishDown() {
		//����ǰ��Piece����Ϊ��һ��Piece
		this.setCurrentPiece(getNextPiece());
		//�����µ�Piece����
		this.currentPiece.setSquaresXLocation(-NEXT_X);
		this.currentPiece.setSquaresXLocation(BEGIN_X);
		this.currentPiece.setSquaresYLocation(-NEXT_Y);
		this.currentPiece.setSquaresYLocation(BEGIN_Y);
		//������һ��Piece
		createNextPiece();
	}
	
	
	//��������ʱ�����ķ���
	public void change() {
		if(pauseFlag) return;
		if (this.currentPiece == null) return;
		this.currentPiece.change();
		//�ж�ת��������Ƿ�Խ��
		//�õ���ǰ������С��X����
		int minX = this.currentPiece.getMinXLocation();
		//���Խ��
		if (minX < 0) {
			//���Ƴ����Ĳ���
			this.currentPiece.setSquaresXLocation(-minX);
		}
		//�ж�ת�����ұ��Ƿ�Խ��
		int maxX = this.currentPiece.getMaxXLocation();
		int gamePanelWidth = this.gamePanel.getWidth();
		//�ұ�Խ��
		if (maxX > gamePanelWidth) {
			//���Ƴ���GamePanel��Ĳ���
			this.currentPiece.setSquaresXLocation(-(maxX - gamePanelWidth));
		}
		this.gamePanel.repaint();
	}
	
	//��, ����Ϊ����
	public void left(int size) {
		if(pauseFlag) return;
		if (this.currentPiece == null) return;
		//�ж��Ƿ��Ѿ�������߽߱�
		if (this.currentPiece.getMinXLocation() <= 0) return;
		//�ó��ƶ�����
		int distance = -(Piece.SQUARE_BORDER * size);
		this.currentPiece.setSquaresXLocation(distance);
		this.gamePanel.repaint();
	}
	
	//��, ����Ϊ����(һ��)
	public void right(int size) {
		if(pauseFlag) return;
		if (this.currentPiece == null) return;
		//�ж��Ƿ񳬹�GamePanel�Ŀ�
		if (this.currentPiece.getMaxXLocation() >= this.gamePanel.getWidth()) return;
		int distance = Piece.SQUARE_BORDER * size;
		this.currentPiece.setSquaresXLocation(distance);
		this.gamePanel.repaint();
	}
	
	//�¼���
	public void down() {
		if(pauseFlag) return;
		if (isButtom()||isBlock()) {
			if(this.isLost()){
				gameover = true;
				this.gamePanel.repaint();
				ImageUtil.paintHuaji(nextPanel.getGraphics(), this.huaji);
				this.initSquares();
				timer.cancel();
				tetrisTask.cancel();
				//�ȼ�����Ϊ1
				currentLevel = 1;
				gamestart = false;
			}
			else{
				appendToSquares();
				cleanRows();
				finishDown();
			}
		}
		else{
			if (this.pauseFlag) return;
			if (this.currentPiece == null) return;
			int distance = Piece.DOWN_HEIGHT;
			//����Piece�ķ������ı��������ֵ
			this.currentPiece.setSquaresYLocation(distance);
			this.gamePanel.repaint();
		}
	}


	public Component getGamePanel() {
		return this.gamePanel;
	}

	//�ж��Ƿ񵽽�����ײ�
	public boolean isButtom() {
		System.out.println("MainFrame 353 getMaxYLocation:"+this.currentPiece.getMaxYLocation());
		System.out.println("MainFrame 354 getPanelHeight:"+this.gamePanel.getHeight());
		return this.currentPiece.getMaxYLocation() >= this.gamePanel.getHeight();
	}


	//��ʼ�������ά����
	void initSquares() {
		System.out.println("���鱻��ʼ����");
		//�õ�����Դ�ŵķ������
		int xSize = this.gamePanel.getWidth()/Piece.SQUARE_BORDER;
		System.out.println("xsize:"+xSize);
		//�õ��߿��Դ�ŵķ������
		int ySize = this.gamePanel.getHeight()/Piece.SQUARE_BORDER;
		System.out.println("ysize:"+ySize);
		//�������Ķ�ά����
		this.squares = new Square[ySize][xSize];
		int i,j,k,p;
		for(i = 0,p = 0,k = 0; i < this.squares.length; i++,k++,p = 0) {
			for (j = 0; j < this.squares[i].length;j++,p++) {
				this.squares[i][j] = new Square(Piece.SQUARE_BORDER * p, 
						Piece.SQUARE_BORDER * k);
			}
		}
		System.out.println("�����ʼ�����");
	}

	public Square[][] getSquares() {
		// TODO �Զ����ɵķ������
		return squares;
	}
	
	//��Piece�����е�Square�����뵽��ά������
	void appendToSquares() {
		List<Square> squares = this.currentPiece.getSquares();
		//����Piece�е�Square,������squares�����е�С����
		System.out.println("MainFrame:383:this.squares.length:"+this.squares.length);
		for(Square square : squares) {
			//������ά�����е�С����
			for(int i = 0; i < this.squares.length; i++) {
				for (int j = 0; j < this.squares[i].length; j++) {
					//�õ���ǰ��������е�Square
					Square s = this.squares[i][j];
					//�ж�Square�Ƿ�һ��
//					System.out.println("����s��XY:"+s.getBeginX()+","+s.getBeginY());
//					System.out.println("square��XY:"+square.getBeginX()+","+square.getBeginY());
					if(s.getBeginX()==square.getBeginX()&&s.getBeginY()==square.getBeginY()){
						System.out.println("һ��");
						this.squares[i][j] = square;
					}
				}
			}
		}
		this.gamePanel.repaint();
	}
	
	
	//�жϵ�ǰ��Piece�Ƿ������ϰ�, ����true��ʾ�����ϰ�, ����false��ʾû������
	public boolean isBlock() {
		List<Square> squares = this.currentPiece.getSquares();
		for (int i = 0; i < squares.size(); i++) {
			Square s = squares.get(i);
			//��Ҫ��һ��Square�����Y����
			Square block = getSquare(s.getBeginX(), s.getBeginY() + Piece.SQUARE_BORDER);
			//block�ǿձ�ʾ�����ϰ�
			if (block != null) return true;
		}
		return false;
	}
	//���ݿ�ʼ�����ڵ�ǰ���������в�����Ӧ��Square
	private Square getSquare(int beginX, int beginY) {
		for (int i = 0; i < this.squares.length; i++) {
			for (int j = 0; j < this.squares[i].length; j++) {
				Square s = this.squares[i][j];
				//������Ŀ�ʼ������ͬ����ͼƬ��Ϊ��
				if (s.getImage() != null && (s.getBeginX() == beginX) && 
						(s.getBeginY() == beginY)) {
					return s;
				}
			}
		}
		return null;
	}
	
	//�õ����������м���
	void cleanRows() {
		//ʹ��һ�����ϱ��汻ɾ�����е�����
		List<Integer> rowIndexs = new ArrayList<Integer>();
		for (int i = 0; i < this.squares.length; i++) {
			int k = 0;//��¼ͼƬ��
			for (int j = 0; j < this.squares[i].length; j++) {
				Square s = this.squares[i][j];
				//����ø���ͼƬ, ��k+1
				if (s.getImage() != null) k++;
			}
			System.out.println("K = "+k);
			System.out.println("squares[i].length = "+this.squares[i].length);
			//������ж���ͼƬ, ������
			if (k == this.squares[i].length) {
				System.out.println("��"+(i+1)+"�����з�������");
				//�ٴζԸ��н��б���, ���ø������и��ͼƬΪnull
				for ( int j = 0; j < this.squares[i].length; j++) {
					Square s = this.squares[i][j];
					s.setImage(null);
				}
				rowIndexs.add(i);
				addScore();
			}
		}
		//����������Square
		this.handleDown(rowIndexs);
	}
	
	//����������������Square��"�½�", ����Ϊ��ɾ�����е���������
	private void handleDown(List<Integer> rowIndexs) {
		if (rowIndexs.size() == 0) return;
		System.out.println("���б��������������Ҫ�½�");
		//�ӱ�ɾ���������ó�������С����
		int minCleanRow = rowIndexs.get(0);
		int cleanRowSize = rowIndexs.size();
		System.out.println("minCleanRow:"+minCleanRow);
		System.out.println("cleanRowSize:"+cleanRowSize);
		//�����½���Square
		//������һ��һ�д�������һ��һ�д���
		for (int j = this.squares[0].length - 1; j >= 0; j--) {
			if (j < minCleanRow) {
				System.out.println("��ʼ������������");
				//�����������, ����������
				for (int i = minCleanRow - 1; i >= 0; i--) {
					Square s = this.squares[i][j];
					if (s.getImage() != null) {
						//�õ��½�ǰ��ͼƬ
						Image image = s.getImage();
						s.setImage(null);
						//�õ��½����Ӧ��Square����, ����Ķ�άֵҪ���������е�����
						System.out.println("�����ǰҪ�½��ַ�����кź��к�"+i+","+j);
						Square sdown = this.squares[i+ cleanRowSize][j];
						sdown.setImage(image);
					}
				}
			}
		}		
	}
	
	//�������
	private void addScore() {
		//�ӷ�
		this.score += 10;
		this.scoreLabel.setText(String.valueOf(score));
		//������Ա�100����, ���һ��
		if ((this.score % 100) == 0) {
			this.currentLevel += 1;
			this.levelLabel.setText(String.valueOf(this.currentLevel));
			//�������ö�ʱ��
			this.timer.cancel();
			this.timer = new Timer();
			this.tetrisTask = new TetrisTask(this,this.nextPanel);
			//���÷����½��ٶ�
			int time = 800 / this.currentLevel;
			timer.schedule(this.tetrisTask, 0, time);
		}
	}
	
	//��ͣ��Ϸ
	public void pause() {	
		if(gameover) return;
		this.pauseFlag = true;		
		if (this.timer != null) this.timer.cancel();
		this.timer = null;
	}
	
	//������Ϸ
	public void resume() {		
		if (!this.pauseFlag) return;
		if (gameover) return;
		this.timer = new Timer();
		this.tetrisTask = new TetrisTask(this,this.nextPanel);
		int time = 800 / this.currentLevel;
		timer.schedule(this.tetrisTask, 0, time);
		this.pauseFlag = false;
	}
	
	//�ж��Ƿ�ʧ��, trueΪʧ��, false��֮
	boolean isLost() {
		for (int i = 0,j = 0; j < this.squares[i].length; j++) {
			Square s = this.squares[i][j];
			if (s.getImage() != null) return true;
		}
		return false;
	}
	
	
	

}

class TetrisTask extends TimerTask{

	//���������
	private MainFrame mainFrame;
	private JPanel nextPanel;
	public TetrisTask(MainFrame mainFrame,JPanel nextPanel) {
		this.mainFrame = mainFrame;
		this.nextPanel = nextPanel;
	}
	public void run() {
		//���������ײ���ߵ���ײ�ʱ
		if (this.mainFrame.isButtom()||this.mainFrame.isBlock()){
			//���ʧ���ˡ�����
			if(this.mainFrame.isLost()){
				mainFrame.gameover = true;
				mainFrame.gamePanel.repaint();
				ImageUtil.paintHuaji(this.nextPanel.getGraphics(),this.mainFrame.huaji );
				//��ʼ������
				mainFrame.initSquares();
				//ȡ��ʱ��ƻ�
				mainFrame.timer.cancel();
				//ȡ������
				mainFrame.tetrisTask.cancel();
				//�ȼ�����Ϊ1
				mainFrame.currentLevel = 1;
				mainFrame.gamestart = false;
			}
			else{
				this.mainFrame.appendToSquares();
				this.mainFrame.cleanRows();
				this.mainFrame.finishDown();
			}
		} 
		else{
			//�õ���ǰ�����˶��Ĵ󷽿�
			Piece currentPiece = this.mainFrame.getCurrentPiece();
			//����Piece��setSquaresYLocation����
			currentPiece.setSquaresYLocation(Piece.DOWN_HEIGHT);
			this.mainFrame.getGamePanel().repaint();
			ImageUtil.paintNextPiece(this.nextPanel.getGraphics(),this.mainFrame.getNextPiece());
		}
		
	}

	
}
