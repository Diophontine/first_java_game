package com.dd;

public class Physics {
	//PE = mgh
	float potetialEnergy;
	float mass;
	float gravity;
	float height;
	float initialVelocity;
	double velocity = 0;
	//s(t) = –0.5gt^2 + vt + h
	
	public double jump(double gravtity, double vy,double time) {
		velocity = 0;
		velocity= (gravtity*time) + vy;
		return velocity;
		}
	public double airResistance(double drag, double velocity,double area,double density){
		double dragForce = drag*(velocity*velocity)* density * area;
		return dragForce;
	}
	public double drag(double vx){
		return vx*0.999;
	}
	
	public double XAccelertion(double coifficent,double time, double vy) {
		double velocity = 0;
		velocity= (coifficent* time) + vy;
		return velocity;
		}
	
	
}

