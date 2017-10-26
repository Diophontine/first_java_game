package com.dd;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import org.imgscalr.Scalr;


public class ImageLoader {
	BufferedImage Block_Ground;
	BufferedImage Block_Water;
	BufferedImage Block_Platform;
	BufferedImage slimeStanding;
	BufferedImage backGround;
	BufferedImage slimeRight;
	BufferedImage slimeLeft;
	BufferedImage apple;
	BestImage best;
	boolean running;
	int current = 0;
	int counter= 0;
	ArrayList<BufferedImage> spriteImages = new ArrayList<BufferedImage>();
	
	ArrayList<BufferedImage> typesOfBlocks = new ArrayList<BufferedImage>();
	
//	public void setImageType(boolean running, boolean jumping , boolean moving, boolean right){
//		if(running&&!jumping){
//			if(right){
//				if(counter==10&&running){
//					current = 2;
//					counter = 0;
//					running = !running;
//				}
//				if(counter==10&&!running){
//					current = 0;
//					counter = 0;
//					running = !running;
//				}
//			}
//			
//		}
//	}
	public void Right(){
		current = 1;
		
	}
	public void Left(){
		current = 0;
	}
public ImageLoader(BufferedImage Block_Ground,BufferedImage Block_Water,BufferedImage Block_Platform,BufferedImage slimeStanding,BufferedImage backGround,
BufferedImage slimeRight,
BufferedImage slimeLeft,
BufferedImage apples){
	
	this.apple = apples;
	this.Block_Ground = Block_Ground;
	this.Block_Water = Block_Water;
	this.Block_Platform = Block_Platform;
	this.slimeStanding = slimeStanding;
	this.backGround = backGround;
	this.slimeRight = slimeRight;
	this.slimeLeft = slimeLeft;
	
	
}
public void ScaleImages(int frameX,int frameY) {
	backGround = Scalr.resize(backGround, Scalr.Method.BALANCED,2000,2000, Scalr.OP_ANTIALIAS);
//	slimeStanding = Scalr.resize(slimeStanding,Scalr.Method.BALANCED,frameX/10,frameY/10);
	slimeLeft = Scalr.resize(slimeLeft,Scalr.Method.BALANCED,frameX/10,frameY/10);
	slimeRight = Scalr.resize(slimeRight,Scalr.Method.BALANCED,frameX/10,frameY/10);
	for (int i = 0; i < 3; i++) {
		Block_Ground = Scalr.resize(Block_Ground,Scalr.Method.BALANCED,frameX/10,frameY/10);
		Block_Platform = Scalr.resize(Block_Platform,Scalr.Method.BALANCED,frameX/10,frameY/10);
		Block_Water = Scalr.resize(Block_Water,Scalr.Method.BALANCED,frameX/10,frameY/10);
	}
	
	typesOfBlocks.add(Block_Ground);
	typesOfBlocks.add(Block_Platform);
	typesOfBlocks.add(Block_Water);
	//spriteImages.add(slimeStanding);
	spriteImages.add(slimeLeft);
	spriteImages.add(slimeRight);
}
public BufferedImage getBackground(){
	return backGround;
}
public BufferedImage getBlock(int i){
	return typesOfBlocks.get(i);
}
public BufferedImage getSprite(){
	return spriteImages.get(current);
}
}
