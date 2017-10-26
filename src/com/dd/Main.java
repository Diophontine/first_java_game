package com.dd;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;




public class Main implements Runnable, KeyListener {

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 new Main();
	}
	JFrame f;
	BufferStrategy s;
	GridLayout grid;
	Scanner input;
	BufferedReader br;
	int Time = 0;
	Maps maps;
	ImageLoader images;
	Sprite sprite;
	Physics physics = new Physics();
	int map;
	boolean locked;
	boolean running = true;
	long precycleTime;
	long afterTime;
	long TimeReq;
	long sleepTime;
	int ground ;
	Friends friend;
	ArrayList<String> rows = new ArrayList<String>();
	ArrayList<String> rows1 = new ArrayList<String>();
	ArrayList <Rectangle> tiles = new ArrayList<Rectangle>();
	ArrayList <Rectangle> tiles1 = new ArrayList<Rectangle>();
	ArrayList <Rectangle> tiles2 = new ArrayList<Rectangle>();
	ArrayList <Friends> apple = new ArrayList<Friends>();
	ArrayList <Rectangle> appleBox = new ArrayList<Rectangle>();

	boolean jumping;
	boolean standing;
	boolean right;
	boolean run;
	boolean win = false;
	int counter = 0;
	boolean lose = false;
	BufferedImage Block_Ground;
	BufferedImage Block_Water;
	BufferedImage Block_Platform;
	BufferedImage slimeStanding;
	BufferedImage backGround;
	BufferedImage slimeRight;
	BufferedImage slimeLeft;
	BufferedImage apples;
	public Main(){
		
			
		
		maps = new Maps();
		f = new JFrame();
		 f.setResizable(false);
		 f.setFocusTraversalKeysEnabled(true);
		f.addKeyListener(this);
		f.setSize(600, 600);
		f.setTitle("Camera");
		f.setFocusable(true);
		f.setAutoRequestFocus(true);
	//	f.setUndecorated(true);
		f.setBackground(Color.white);
		f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		f.setVisible(true);
		f.createBufferStrategy(2);
		
			s =  f.getBufferStrategy();
		
	
		int map = 0;
		
	
		maps.loadMaps();
		
		maps.setMap(map);
		ImageLoader();
		
		images = new ImageLoader( Block_Ground,
		 Block_Water,
		 Block_Platform,
		 slimeStanding,
		backGround,
		 slimeRight,
		 slimeLeft,
		 apples);
		
		images.ScaleImages(f.getWidth(), f.getHeight());
		sprite = new Sprite(images);
		sprite.setX(0);
	
		 br = maps.getMap();
		 ground= (0);
		 sprite.setY(ground);
		
		input = new Scanner(br);
		blocks();
		setFriends();
		 createTileHitBox ();
		running = true;
		Thread animator = new Thread();
		animator.start();
		
		running();
		
		
		
		
	}

	
	public void ImageLoader(){
		
		try {
			apples =ImageIO.read(getClass().getResourceAsStream("resources/Apple.png"));
			
			backGround = ImageIO.read(getClass().getResourceAsStream("resources/Background.png"));
			slimeStanding = ImageIO.read(getClass().getResourceAsStream("resources/Truck.gif"));
			 slimeLeft =ImageIO.read(getClass().getResourceAsStream("resources/Truck.gif"));
			 slimeRight = ImageIO.read(getClass().getResourceAsStream("resources/TruckR.gif"));
			 Block_Ground = ImageIO.read(getClass().getResourceAsStream("resources/BlockGround.png"));
			 Block_Platform =  ImageIO.read(getClass().getResourceAsStream("resources/BlockPlatform.png"));
			Block_Water = ImageIO.read(getClass().getResourceAsStream("resources/BlockWater.png"));
		
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,"Error : "+e.getMessage());
			
		}
		
	}
	private void setFriends() {
		int y;
		int x;
		Rectangle rec;
	for (int i = 0; i < 10; i++) {
		 y=-500 + (int)(Math.random() * ((f.getHeight()+500) + 1));
		 x=0 + (int)(Math.random() * (0+max));
		friend = new Friends(x, y);
	apple.add(friend);
	rec = new Rectangle();
	rec.setBounds(x,y, 50, 50);
	appleBox.add(rec);
	
	}
		
	}
	private void running() {
		
		
	while (running) {
		 precycleTime = System.currentTimeMillis();
		 physics();
		 collision();
	
		 
		sprite.changeLocation();
		
	//	images.setImageType(run,jumping,standing,right);
		draw();
		
		update();
		timer();
		System.out.println(sleepTime);
	
		
	}
		
	}
	
boolean moving = false;
double time; 
	private void physics() {
		
		if(falling){
			time =  0.016666666666666666666666667;
		sprite.setVelocityY(physics.jump(9.8,sprite.getVelocityY(), time));
	}
		sprite.setVelocityX(physics.drag(sprite.getVelocityX()));
		sprite.setVelocityY(physics.drag(sprite.getVelocityY()));

	}
	public void timer(){
		
		 afterTime = System.currentTimeMillis();
		if(Time>=120){
			lose = true;
		}
		
		 TimeReq = (afterTime - precycleTime);
		 sleepTime = ((16666667 - (TimeReq*1000000))/1000000);
		
		
		// How long thread sleeps, seconds
		
		if (sleepTime > 0 ){

		try {
				Thread.sleep(sleepTime);
				
			} catch (InterruptedException e) 
				{
				// TODO Auto-generated catch block
				e.printStackTrace();
				}
TimeCount++;
if(TimeCount==60){
	Time++;
	TimeCount = 0;
}

	}
	
		
	}
	int TimeCount;
	int max;
	public void blocks(){
		
			max = input.nextLine().length()*50;
			
		while(input.hasNextLine()){
		rows.add(input.nextLine());
		
		}
	}
	Font myFont;
	public void	draw(){
		
		s = f.getBufferStrategy();
		Graphics2D g = (Graphics2D) s.getDrawGraphics();
		 myFont = new Font("Serif", Font.BOLD, 48);
		g.setFont(myFont);
		g.drawImage(images.getBackground(), 0, 0,null);
		if(win){
			
			g.setFont(myFont);
			
			g.drawString("You Win", f.getWidth()/2-100, f.getHeight()/2);
			s.show();
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.exit(0);
		}
		if(lose){
			
			g.setFont(myFont);
			
			g.drawString("You Lose", f.getWidth()/2-100, f.getHeight()/2);
			s.show();
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.exit(0);
		}
		if(sprite.getX()<300){
			
			for (int i = 0; i < tiles.size(); i++) {
				//check to see if they are on the screen
				if( tiles.get(i).getX()- sprite.getX()-f.getWidth()<600&& tiles.get(i).getX()- sprite.getX()+f.getWidth()/2>0-sprite.getWidth()){
				g.drawImage(images.getBlock(0), (int) tiles.get(i).getX(),(int) tiles.get(i).getY()-sprite.getY()/2,null);}
			}
			for (int i = 0; i < tiles1.size(); i++) {
				if( tiles1.get(i).getX()- sprite.getX()-f.getWidth()<600&& tiles1.get(i).getX()- sprite.getX()+f.getWidth()/2>0-sprite.getWidth()){
				g.drawImage(images.getBlock(1), (int) tiles1.get(i).getX(),(int) tiles1.get(i).getY()-sprite.getY()/2,null);}
			}
			
			for (int i = 0; i < tiles2.size(); i++) {
				if( tiles2.get(i).getX()- sprite.getX()-f.getWidth()<600&& tiles2.get(i).getX()- sprite.getX()+f.getWidth()/2>0-sprite.getWidth()){
				g.drawImage(images.getBlock(2), (int) tiles2.get(i).getX() ,(int) tiles2.get(i).getY()-sprite.getY()/2,null);
			}}
			//if(sprite.getX()<f.getHeight()/2-sprite.getWidth()/2){
			for (int i = 0; i < apple.size(); i++) {
				if( apple.get(i).getX()- sprite.getX()-f.getWidth()<600&& apple.get(i).getX()- sprite.getX()+f.getWidth()/2>0-sprite.getWidth()){
				g.drawImage(apple.get(i).getImage(), (int) apple.get(i).getX(),(int) apple.get(i).getY()-sprite.getY()/2,null);}
			}
			g.drawString("Time:"+(120- Time),50,100);
			g.drawImage(sprite.getImage(),sprite.getX(),sprite.getY()-sprite.getY()/2, null);
		}else{
			
		for (int i = 0; i < tiles.size(); i++) {
			//check to see if they are on the screen
			if( tiles.get(i).getX()- sprite.getX()+f.getWidth()/2<600&& tiles.get(i).getX()- sprite.getX()+f.getWidth()/2>0-sprite.getWidth()){
			g.drawImage(images.getBlock(0), (int) tiles.get(i).getX()- sprite.getX()+f.getWidth()/2,(int) tiles.get(i).getY()-sprite.getY()/2,null);
		}}
		for (int i = 0; i < tiles1.size(); i++) {
			if( tiles1.get(i).getX()- sprite.getX()+f.getWidth()/2<600&& tiles1.get(i).getX()- sprite.getX()+f.getWidth()/2>0-sprite.getWidth()){
			g.drawImage(images.getBlock(1), (int) tiles1.get(i).getX()- sprite.getX()+f.getWidth()/2,(int) tiles1.get(i).getY()-sprite.getY()/2,null);
		}}
		for (int i = 0; i < tiles2.size(); i++) {
			if( tiles2.get(i).getX()- sprite.getX()+f.getWidth()/2<600&& tiles2.get(i).getX()- sprite.getX()+f.getWidth()/2>0-sprite.getWidth()){
			g.drawImage(images.getBlock(2), (int) tiles2.get(i).getX()- sprite.getX()+f.getWidth()/2 ,(int) tiles2.get(i).getY()-sprite.getY()/2,null);
		}}
		for (int i = 0; i < apple.size(); i++) {
			if( apple.get(i).getX()- sprite.getX()+f.getWidth()/2<600&& apple.get(i).getX()- sprite.getX()+f.getWidth()/2>0-sprite.getWidth()){
				g.drawImage(apple.get(i).getImage(), (int)apple.get(i).getX()- sprite.getX()+f.getWidth()/2 ,(int) apple.get(i).getY()-sprite.getY()/2,null);
			}
			
		}
		//if(sprite.getX()<f.getHeight()/2-sprite.getWidth()/2){
			
			g.drawImage(sprite.getImage(),sprite.getX()- sprite.getX()+f.getWidth()/2,sprite.getY()-sprite.getY()/2, null);
			g.drawString("Time:"+(120-Time),50,100);
		//}
//		else{
//			g.drawImage(sprite.getImage(),f.getWidth()/2-sprite.getWidth()/2,sprite.getY(), null);
		}
	g.dispose();
	}
	boolean pressedSide = false;
	boolean falling = false;
	Rectangle Myslime;
	int counter1;
	public void collision(){
	if(counter>=5){
		win = true;
	}
	 Myslime = new Rectangle();
	Myslime.setSize(sprite.getWidth(), sprite.getHeight());
	Myslime.setLocation(sprite.getX(), sprite.getY());
	if(Myslime.getX()>4600||Myslime.getY()> 1000 ){
		lose = true;
	}
	if(Myslime.getX()<0){
		sprite.setX(0);
	}
	for (int i = 0; i < appleBox.size(); i++) {
		if(appleBox.get(i).intersects(Myslime)){
			counter++;
			appleBox.remove(i);
			apple.remove(i);
		}
	}
		 counter1 =0;
		for (int i = 0; i < tiles.size(); i++) {
			if(Myslime.intersects(tiles.get(i))){
				counter1++;
			}
			if(Myslime.intersects(tiles.get(i))){
				falling = false;
				locked = false;
				jumping = false;
				
				if(!pressedUp){
					sprite.setVelocityY(0);
				}
				if(!pressedSide){
					sprite.setVelocityX(0);
				}
				 if(sprite.getY()>= tiles.get(i).getY()+tiles.get(i).height&&sprite.getVelocityY()<0){
						sprite.setY((int) (tiles.get(i).getY()+tiles.get(i).getHeight()));
						jumping = true;
						falling = true;
						sprite.setVelocityY(-0.5*sprite.getVelocityY());
						break;
					}
				 //hit under
//				 else if(sprite.getVelocityX()!=0 && sprite.getY()< tiles.get(i).getY()&& sprite.getY()>tiles.get(i).getY()+sprite.getHeight()&&sprite.getVelocityY()<0){
//						
//						jumping = true;
//						falling = true;
//						sprite.setVelocityX(-0.5*sprite.getVelocityX());
//						break;
//					}
				 //side
				 
				else if(sprite.getVelocityY()>=0 && sprite.getY()<= tiles.get(i).getY()&&!falling){
					sprite.setY((int) tiles.get(i).getY()-sprite.getHeight());
					break;
					}
				 //on top
				
			
				
				
			}
			
		}
		if(counter1==0) {
			falling = true;
			locked = true;
		}
	}
	private void update() {
		if(!s.contentsLost()){
			s.show();
		
		}
	}
	boolean pressedUp = true;
	@Override
	public void keyPressed(KeyEvent e) {
		int  keyCode  = e.getKeyCode();
		if (keyCode == KeyEvent.VK_UP) {
			pressedUp = true;
			falling = true;
			if(!locked){
			sprite.setVelocityY(-7);
			locked = true;
			moving= true;
			jumping = true;
			}
			
		}
		if (keyCode == KeyEvent.VK_DOWN) {
			
		}
		if (keyCode == KeyEvent.VK_ESCAPE) {
			System.exit(0);
		}
		if (keyCode == KeyEvent.VK_LEFT) {
			moving= true;
			sprite.setVelocityX(-5);
			run = true;
			pressedSide = true;
			images.Left();
			
		}if (keyCode == KeyEvent.VK_RIGHT) {
			moving= true;
			sprite.setVelocityX(5);
			run = true;
			pressedSide = true;
			images.Right();
			
		}

		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		int  keyCode  = e.getKeyCode();
		if (keyCode == KeyEvent.VK_UP) {
		if(!locked){
			pressedUp = false;
			
		}
		}
		if (keyCode == KeyEvent.VK_DOWN) {
			if(!locked){
				
			}
		}
		if (keyCode == KeyEvent.VK_LEFT) {
			moving= false;
			run = false;
			pressedSide = false;
			if(!locked){
				sprite.setVelocityX(0);
				
			}
		}
		if (keyCode == KeyEvent.VK_RIGHT) {
			run = false;
			moving= false;
			pressedSide = false;
			if(!locked){
				
				sprite.setVelocityX(0);
			}
		}
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void run() {
		running();
	}
	public void createTileHitBox (){
		String line = null;
		for (int i = 0; i <rows.size(); i++) {
			
			line = rows.get(i);
			
		for (int j = 0; j < line.length(); j++) {
			char number = line.charAt(j);
			int num = Character.getNumericValue(number);
			Rectangle rec = new Rectangle();
			rec.setBounds(50*j,50*i , 50, 50);
			switch (num) {
			case 0:
				
				tiles.add(rec);
				break;
			case 1:
				tiles1.add(rec);
				break;
			case 2:
				tiles2.add(rec);
				break;
			default:
				break;
			}
			
			
		}
		}
	}
}
