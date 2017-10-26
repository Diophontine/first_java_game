package com.dd;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URISyntaxException;
import java.util.ArrayList;

import javax.print.DocFlavor.URL;


public class Maps {
	public void loadMaps(){
		try {
		java.net.URL url = 	this.getClass().getResource("resources/Map0.txt");
			try {

				maps.add(new FileReader(new File(url.toURI())));
			} catch (URISyntaxException | FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}finally{
		
		
	}
	}
ArrayList <FileReader>maps = new ArrayList<FileReader>();
int m;
	public void setMap(int m) {
		this.m = m;
		
	}
BufferedReader br;
	public BufferedReader getMap() {
		br = new BufferedReader( maps.get(m));
		return br;
		
		
	}

}
