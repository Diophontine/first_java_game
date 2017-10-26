package com.dd;

import java.awt.Image;
import java.awt.image.BufferedImage;

public class Sprite {
public Sprite(ImageLoader image){

	this.image = image;
}

	private int x ;
	//x coordinates
	private int y ;
	//y coordinates
	private int w;
	private int h;
	private double vx ;
	//Horizontal Velocity 
	private double vy ;
	ImageLoader image;
	//Vertical Velocity 

	
	//long updateTime;
	

	public void changeLocation(){
	x = (int) (vx+x);
	y =(int) (vy+y);
	}
	
	public int getWidth(){
		w= image.getSprite().getWidth(null);
		return w;
	} 
	public int getHeight(){
		h= image.getSprite().getHeight(null);
		return h;
	} 
	
	//get  horizontal velocity
	public double getVelocityX(){
		return vx;
	}
	//get  Vertical velocity
		public double getVelocityY(){
			return vy;
		}
		//set horizontal velocity
		public void setVelocityX(double vx){
			this.vx = vx;
		}
		//set Vertical velocity
				public void setVelocityY(double vy){
					this.vy = vy;
				}
				
				//get sprites image
		long TimeBetween = 0;
	
		public void spriteAnimation(Image i){
		//	image = i;
			
		}

		
		public synchronized void update(Image i){
				//image = i;
		}
		public BufferedImage getImage(){
			return image.getSprite();
		}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	//set sprite position
	public void setX(int x){
		this.x = x;
	}
	public void setY(int y){
		this.y = y;
	}}

