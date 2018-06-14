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
	
	GamePanel gamePanel = new GamePanel(this); //游戏面板
	Box toolPanel = Box.createVerticalBox();//操控面板
	JPanel scoreTextBox = new JPanel();//存放分数标签的面板
	JLabel scoreTextLabel = new JLabel("分  数");//分数标签
	JPanel scoreBox = new JPanel();//存放分数的面板
	JLabel scoreLabel = new JLabel("0");//显示分数
	int score = 0;
	JPanel levelTextBox = new JPanel();//存放级别标签的面板
	JLabel levelTextLabel = new JLabel("级  别");//级别标签
	JPanel levelBox = new JPanel();//存放级别的面板
	JLabel levelLabel = new JLabel("1");//显示级别
	int currentLevel = 1;
	JPanel resumeBox = new JPanel();//存放继续按钮的小面板
	JButton resumeButton = new JButton("继  续");//继续按钮
	JPanel pauseBox = new JPanel();//存放暂停按钮的小面板
	JButton pauseButton = new JButton("暂  停");//暂停按钮
	JPanel startBox = new JPanel();//存放开始按钮的小面板
	JButton startButton = new JButton("开  始");//开始按钮
	JPanel nextTextBox = new JPanel();//存放下一个标签的面板
	JLabel nextLabel = new JLabel("  ");//下一个按钮
	JPanel nextPanel = new JPanel();//存放下一个大方块的面板
	Font font1 = new Font("黑体",Font.BOLD,30);
	Font font2 = new Font("黑体",Font.BOLD,50);
	Color blue1 = new Color(41,139,204);
	TetrisTask tetrisTask = new TetrisTask(this,this.nextPanel);
	Timer timer = new Timer();
	boolean pauseFlag = false,gameover = false,gamestart = false;
	Square[][] squares;
	Image huaji = ImageUtil.getImage("res/huaji.png");
	
	public MainFrame() {
		
		this.init();
		//分数
		this.scoreTextBox.add(this.scoreTextLabel);
		this.scoreLabel.setText(String.valueOf(this.score));
		this.scoreBox.add(this.scoreLabel);
		//级别
		this.levelTextBox.add(this.levelTextLabel);
		this.levelLabel.setText(String.valueOf(this.currentLevel));
		this.levelBox.add(this.levelLabel);
		//继续按钮
		this.resumeBox.add(this.resumeButton);
		//暂停按钮
		this.pauseBox.add(this.pauseButton);
		//开始
		this.startBox.add(this.startButton);
		//下一个
		this.nextTextBox.add(this.nextLabel);
		//省略其他布局代码
		
		this.setSize(900, 890);
		this.add(this.gamePanel);
		this.add(this.toolPanel);
		this.setLayout(null);
//		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setTitle("俄罗斯方块");
//		this.setUndecorated(true);	//屏蔽标题栏
		this.setPicBack();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
		//继续按钮点击事件
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
				pause();//设置为暂停
				
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
		
		//添加键盘监听器
		startButton.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				System.out.println("监测到用户按下了键盘:"+e.getKeyCode());
				//上
				if (e.getKeyCode() == 38) change();
				//左
				if (e.getKeyCode() == 37) left(1);
				//右
				if (e.getKeyCode() == 39) right(1);
				//下
				if (e.getKeyCode() == 40) down();	
				if (e.getKeyCode() == KeyEvent.VK_ENTER){
					if(pauseFlag) resume();
					else pause();
				}
			}
		});
		//添加键盘监听器
		pauseButton.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				System.out.println("监测到用户按下了键盘:"+e.getKeyCode());
				//上
				if (e.getKeyCode() == 38) change();
				//左
				if (e.getKeyCode() == 37) left(1);
				//右
				if (e.getKeyCode() == 39) right(1);
				//下
				if (e.getKeyCode() == 40) down();
				if (e.getKeyCode() == KeyEvent.VK_ENTER){
					if(pauseFlag) resume();
					else pause();
				}
			}
		});
		//添加键盘监听器
		resumeButton.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				System.out.println("监测到用户按下了键盘:"+e.getKeyCode());
				//上
				if (e.getKeyCode() == 38) change();
				//左
				if (e.getKeyCode() == 37) left(1);
				//右
				if (e.getKeyCode() == 39) right(1);
				//下
				if (e.getKeyCode() == 40) down();
				//enter键
				if (e.getKeyCode() == KeyEvent.VK_ENTER){
					if(pauseFlag) resume();
					else pause();
				}
			}
		});		
		
	
	}//MainFrame
	
	//设置图片背景
	public void setPicBack(){
        String path = "res/2.jpg";  
        // 背景图片  
        ImageIcon background = new ImageIcon(path);  
        // 把背景图片显示在一个标签里面  
        JLabel label = new JLabel(background);  
        // 把标签的大小位置设置为图片刚好填充整个面板  
        System.out.println("设置图片背景"+this.getWidth()+this.getHeight());
        label.setBounds(0, 0, this.getWidth(), this.getHeight());  
        // 把内容窗格强制转化为JPanel，否则不能用方法setOpaque()来使内容窗格透明  
        JPanel imagePanel = (JPanel) this.getContentPane();  
        //设置透明
        imagePanel.setOpaque(false);  
        // 把背景图片添加到分层窗格的最底层作为背景  
        this.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE)); 
	}
	
	//初始化界面
	public void init(){
		//gamePanel
		gamePanel.setBounds(0, 0, 600, 840);
		
		//toolPanel
		toolPanel.setBounds(600, 0, 300, 600);
		toolPanel.setBackground(Color.gray);
		toolPanel.setOpaque(false);
		
		//成绩面板
		scoreTextLabel.setSize(300, 100);
		scoreTextLabel.setFont(font1);
		scoreTextLabel.setForeground(Color.white);
		scoreTextLabel.setOpaque(false);
		scoreTextBox.setOpaque(false);
		toolPanel.add(scoreTextBox);
		
		//成绩标签
		scoreLabel.setSize(300, 100);
		scoreLabel.setFont(font2);
		scoreLabel.setForeground(Color.red);
		scoreBox.setOpaque(false);
		toolPanel.add(scoreBox);
		
		//级别面板
		levelTextLabel.setSize(300, 100);
		levelTextLabel.setFont(font1);
		levelTextLabel.setForeground(Color.white);
		levelTextLabel.setOpaque(false);
		levelTextBox.setOpaque(false);
		toolPanel.add(levelTextBox);
		
		//级别标签
		levelLabel.setSize(300, 100);
		levelLabel.setFont(font2);
		levelLabel.setForeground(Color.RED);
		levelBox.setOpaque(false);
		toolPanel.add(levelBox);
		
		//继续面板
		resumeButton.setFont(font1);
		resumeButton.setBackground(blue1);
		resumeButton.setForeground(Color.white);
		resumeBox.setOpaque(false);
		toolPanel.add(resumeBox);
		
		//暂停面板
		pauseButton.setFont(font1);
		pauseButton.setBackground(blue1);
		pauseButton.setForeground(Color.white);
		pauseBox.setOpaque(false);
		toolPanel.add(pauseBox);
		
		//开始面板
		startButton.setFont(font1);
		startButton.setBackground(blue1);
		startButton.setForeground(Color.white);
		startBox.setOpaque(false);
		toolPanel.add(startBox);
		
		//下一个
		nextLabel.setFont(font1);
		nextLabel.setForeground(Color.white);
		nextTextBox.setOpaque(false);
		toolPanel.add(nextTextBox);
		
		//下一个方块面板
		nextPanel.setBounds(600, 600, 300, 300);
		nextPanel.setOpaque(false);
		this.add(nextPanel);
	}
	
	//当前正在运动的对象
	private Piece currentPiece;
	//下一个大方块对象
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
	
	//下一个Piece的位置
	private final static int NEXT_X = 80;
	private final static int NEXT_Y = 50;
	//当前Piece的开始X座标
	private final static int BEGIN_X = Piece.SQUARE_BORDER * 6;
	//当前Piece的开始Y座标
	private final static int BEGIN_Y = 0;
	//创建下一个
	void createNextPiece() {
		this.nextPiece = this.creator.createPiece(NEXT_X, NEXT_Y);
		this.repaint();
	}
	//开始游戏
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
		nextLabel.setText("下一个");
		System.out.println();
		
		//创建下一个大方块
		createNextPiece();
//		ImageUtil.paintNextPiece(nextPanel.getGraphics(), getNextPiece());
		//创建当前运动的大方块
		this.currentPiece = creator.createPiece(BEGIN_X, BEGIN_Y);		
		change();
		//初始化定时器
		this.tetrisTask = new TetrisTask(this,this.nextPanel);
		int time = 800 / this.currentLevel;		//下降速度
		this.timer.schedule(this.tetrisTask, 0, time);
		
		gamestart = true;
	}
	
	//一个Piece对象完成下降
	void finishDown() {
		//将当前的Piece设置为下一个Piece
		this.setCurrentPiece(getNextPiece());
		//设置新的Piece座标
		this.currentPiece.setSquaresXLocation(-NEXT_X);
		this.currentPiece.setSquaresXLocation(BEGIN_X);
		this.currentPiece.setSquaresYLocation(-NEXT_Y);
		this.currentPiece.setSquaresYLocation(BEGIN_Y);
		//创建下一个Piece
		createNextPiece();
	}
	
	
	//按键盘上时触发的方法
	public void change() {
		if(pauseFlag) return;
		if (this.currentPiece == null) return;
		this.currentPiece.change();
		//判断转换后左边是否越界
		//得到当前方块最小的X座标
		int minX = this.currentPiece.getMinXLocation();
		//左边越界
		if (minX < 0) {
			//右移超过的部分
			this.currentPiece.setSquaresXLocation(-minX);
		}
		//判断转换后右边是否越界
		int maxX = this.currentPiece.getMaxXLocation();
		int gamePanelWidth = this.gamePanel.getWidth();
		//右边越界
		if (maxX > gamePanelWidth) {
			//左移超过GamePanel宽的部分
			this.currentPiece.setSquaresXLocation(-(maxX - gamePanelWidth));
		}
		this.gamePanel.repaint();
	}
	
	//左, 参数为距离
	public void left(int size) {
		if(pauseFlag) return;
		if (this.currentPiece == null) return;
		//判断是否已经在最左边边界
		if (this.currentPiece.getMinXLocation() <= 0) return;
		//得出移动距离
		int distance = -(Piece.SQUARE_BORDER * size);
		this.currentPiece.setSquaresXLocation(distance);
		this.gamePanel.repaint();
	}
	
	//右, 参数为距离(一格)
	public void right(int size) {
		if(pauseFlag) return;
		if (this.currentPiece == null) return;
		//判断是否超过GamePanel的宽
		if (this.currentPiece.getMaxXLocation() >= this.gamePanel.getWidth()) return;
		int distance = Piece.SQUARE_BORDER * size;
		this.currentPiece.setSquaresXLocation(distance);
		this.gamePanel.repaint();
	}
	
	//下加速
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
				//等级重置为1
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
			//调用Piece的方法，改变纵坐标的值
			this.currentPiece.setSquaresYLocation(distance);
			this.gamePanel.repaint();
		}
	}


	public Component getGamePanel() {
		return this.gamePanel;
	}

	//判断是否到界面最底部
	public boolean isButtom() {
		System.out.println("MainFrame 353 getMaxYLocation:"+this.currentPiece.getMaxYLocation());
		System.out.println("MainFrame 354 getPanelHeight:"+this.gamePanel.getHeight());
		return this.currentPiece.getMaxYLocation() >= this.gamePanel.getHeight();
	}


	//初始化界面二维数组
	void initSquares() {
		System.out.println("数组被初始化啦");
		//得到宽可以存放的方块个数
		int xSize = this.gamePanel.getWidth()/Piece.SQUARE_BORDER;
		System.out.println("xsize:"+xSize);
		//得到高可以存放的方块个数
		int ySize = this.gamePanel.getHeight()/Piece.SQUARE_BORDER;
		System.out.println("ysize:"+ySize);
		//构造界面的二维数组
		this.squares = new Square[ySize][xSize];
		int i,j,k,p;
		for(i = 0,p = 0,k = 0; i < this.squares.length; i++,k++,p = 0) {
			for (j = 0; j < this.squares[i].length;j++,p++) {
				this.squares[i][j] = new Square(Piece.SQUARE_BORDER * p, 
						Piece.SQUARE_BORDER * k);
			}
		}
		System.out.println("数组初始化完成");
	}

	public Square[][] getSquares() {
		// TODO 自动生成的方法存根
		return squares;
	}
	
	//将Piece中所有的Square都加入到二维数组中
	void appendToSquares() {
		List<Square> squares = this.currentPiece.getSquares();
		//遍历Piece中的Square,即遍历squares队列中的小方块
		System.out.println("MainFrame:383:this.squares.length:"+this.squares.length);
		for(Square square : squares) {
			//遍历二维数组中的小方块
			for(int i = 0; i < this.squares.length; i++) {
				for (int j = 0; j < this.squares[i].length; j++) {
					//得到当前数组界面中的Square
					Square s = this.squares[i][j];
					//判断Square是否一致
//					System.out.println("数组s的XY:"+s.getBeginX()+","+s.getBeginY());
//					System.out.println("square的XY:"+square.getBeginX()+","+square.getBeginY());
					if(s.getBeginX()==square.getBeginX()&&s.getBeginY()==square.getBeginY()){
						System.out.println("一样");
						this.squares[i][j] = square;
					}
				}
			}
		}
		this.gamePanel.repaint();
	}
	
	
	//判断当前的Piece是否遇到障碍, 返回true表示遇到障碍, 返回false表示没有遇到
	public boolean isBlock() {
		List<Square> squares = this.currentPiece.getSquares();
		for (int i = 0; i < squares.size(); i++) {
			Square s = squares.get(i);
			//需要拿一个Square的最大Y座标
			Square block = getSquare(s.getBeginX(), s.getBeginY() + Piece.SQUARE_BORDER);
			//block非空表示遇到障碍
			if (block != null) return true;
		}
		return false;
	}
	//根据开始座标在当前界面数组中查找相应的Square
	private Square getSquare(int beginX, int beginY) {
		for (int i = 0; i < this.squares.length; i++) {
			for (int j = 0; j < this.squares[i].length; j++) {
				Square s = this.squares[i][j];
				//与参数的开始座标相同并且图片不为空
				if (s.getImage() != null && (s.getBeginX() == beginX) && 
						(s.getBeginY() == beginY)) {
					return s;
				}
			}
		}
		return null;
	}
	
	//得到可以清理行集合
	void cleanRows() {
		//使用一个集合保存被删除的行的索引
		List<Integer> rowIndexs = new ArrayList<Integer>();
		for (int i = 0; i < this.squares.length; i++) {
			int k = 0;//记录图片数
			for (int j = 0; j < this.squares[i].length; j++) {
				Square s = this.squares[i][j];
				//如果该格有图片, 则k+1
				if (s.getImage() != null) k++;
			}
			System.out.println("K = "+k);
			System.out.println("squares[i].length = "+this.squares[i].length);
			//如果整行都有图片, 则消除
			if (k == this.squares[i].length) {
				System.out.println("第"+(i+1)+"行所有方格已满");
				//再次对该行进行遍历, 设置该行所有格的图片为null
				for ( int j = 0; j < this.squares[i].length; j++) {
					Square s = this.squares[i][j];
					s.setImage(null);
				}
				rowIndexs.add(i);
				addScore();
			}
		}
		//处理悬浮的Square
		this.handleDown(rowIndexs);
	}
	
	//处理行消除后其他Square的"下降", 参数为被删除的行的索引集合
	private void handleDown(List<Integer> rowIndexs) {
		if (rowIndexs.size() == 0) return;
		System.out.println("有行被消除，上面的行要下降");
		//从被删除的行中拿出索引最小的行
		int minCleanRow = rowIndexs.get(0);
		int cleanRowSize = rowIndexs.size();
		System.out.println("minCleanRow:"+minCleanRow);
		System.out.println("cleanRowSize:"+cleanRowSize);
		//处理下降的Square
		//这里是一列一列处理，不是一行一行处理
		for (int j = this.squares[0].length - 1; j >= 0; j--) {
			if (j < minCleanRow) {
				System.out.println("开始处理悬浮的行");
				//遍历上面的行, 即悬浮的行
				for (int i = minCleanRow - 1; i >= 0; i--) {
					Square s = this.squares[i][j];
					if (s.getImage() != null) {
						//得到下降前的图片
						Image image = s.getImage();
						s.setImage(null);
						//得到下降后对应的Square对象, 数组的二维值要加上消除行的行数
						System.out.println("输出当前要下降分方块的行号和列号"+i+","+j);
						Square sdown = this.squares[i+ cleanRowSize][j];
						sdown.setImage(image);
					}
				}
			}
		}		
	}
	
	//加入分数
	private void addScore() {
		//加分
		this.score += 10;
		this.scoreLabel.setText(String.valueOf(score));
		//如果可以被100整除, 则加一级
		if ((this.score % 100) == 0) {
			this.currentLevel += 1;
			this.levelLabel.setText(String.valueOf(this.currentLevel));
			//重新设置定时器
			this.timer.cancel();
			this.timer = new Timer();
			this.tetrisTask = new TetrisTask(this,this.nextPanel);
			//设置方块下降速度
			int time = 800 / this.currentLevel;
			timer.schedule(this.tetrisTask, 0, time);
		}
	}
	
	//暂停游戏
	public void pause() {	
		if(gameover) return;
		this.pauseFlag = true;		
		if (this.timer != null) this.timer.cancel();
		this.timer = null;
	}
	
	//继续游戏
	public void resume() {		
		if (!this.pauseFlag) return;
		if (gameover) return;
		this.timer = new Timer();
		this.tetrisTask = new TetrisTask(this,this.nextPanel);
		int time = 800 / this.currentLevel;
		timer.schedule(this.tetrisTask, 0, time);
		this.pauseFlag = false;
	}
	
	//判断是否失败, true为失败, false反之
	boolean isLost() {
		for (int i = 0,j = 0; j < this.squares[i].length; j++) {
			Square s = this.squares[i][j];
			if (s.getImage() != null) return true;
		}
		return false;
	}
	
	
	

}

class TetrisTask extends TimerTask{

	//主界面对象
	private MainFrame mainFrame;
	private JPanel nextPanel;
	public TetrisTask(MainFrame mainFrame,JPanel nextPanel) {
		this.mainFrame = mainFrame;
		this.nextPanel = nextPanel;
	}
	public void run() {
		//如果发生碰撞或者到达底部时
		if (this.mainFrame.isButtom()||this.mainFrame.isBlock()){
			//如果失败了···
			if(this.mainFrame.isLost()){
				mainFrame.gameover = true;
				mainFrame.gamePanel.repaint();
				ImageUtil.paintHuaji(this.nextPanel.getGraphics(),this.mainFrame.huaji );
				//初始化数组
				mainFrame.initSquares();
				//取消时间计划
				mainFrame.timer.cancel();
				//取消任务
				mainFrame.tetrisTask.cancel();
				//等级重置为1
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
			//得到当前正在运动的大方块
			Piece currentPiece = this.mainFrame.getCurrentPiece();
			//调用Piece的setSquaresYLocation方法
			currentPiece.setSquaresYLocation(Piece.DOWN_HEIGHT);
			this.mainFrame.getGamePanel().repaint();
			ImageUtil.paintNextPiece(this.nextPanel.getGraphics(),this.mainFrame.getNextPiece());
		}
		
	}

	
}
