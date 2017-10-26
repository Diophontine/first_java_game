package com.dd;


import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;


public class Friends {
BufferedImage img;
int x,y;
boolean isAlive;
public Friends(int startX,int startY){
	isAlive = true;
	x = startX;
	y = startY;
	//try {
		
	//img = ImageIO.read(new File("C:\\Users\\Denys\\GameDevelopment\\Denys Game\\Lib\\Dragon quest game\\Apple.png"));
		 try {
			img = ImageIO.read(getClass().getResourceAsStream("resources/Apple.png"));
			img = Scalr.resize(img,Scalr.Method.BALANCED, 50,50);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	//} catch (IOException e) {
		// TODO Auto-generated catch block
		//e.printStackTrace();
	//}
}
public int getX(){
	return x;
}
public int getY(){
	return y;
}
public Image getImage(){
	return img;
}

public boolean isAlive(){
	return isAlive;
}
public void move(){
	x=x - 25;
}
}
