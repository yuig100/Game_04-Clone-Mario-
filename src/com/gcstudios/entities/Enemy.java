package com.gcstudios.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.gcstudios.world.World;

public class Enemy extends Entity{

	public static boolean right=true,left=false;
	int dir=1;
	public int vida = 1;
	private int frames = 0, maxframes = 15, index = 0, maxindex = 1;
	
	public Enemy(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
		speed = 0.5;
	}
	
	public void tick() {
		
		if(World.isFree((int)x, (int)(y + 1))){

			y+=1;
		}
		
		if(right) {
			if(World.isFree((int)(x + speed),(int) y)) {
				
				x+=0.5;
				dir=1;
				
				if(World.isFree((int)(x+16), (int)y+1)) {
					
					right = false;
					left = true;
				}
				
			} else {
				
				right = false;
				left = true;
				
			}

		}
		
		if(left) {
			
			if(World.isFree((int)(x - speed),(int) y)) {
				
				x-=0.5;
				dir=-1;
				
				if(World.isFree((int)(x-16), (int)y+1)) {
					
					right = true;
					left = false;
				}
				
			} else {
				
				right = true;
				left = false;
				
			}
		} 
		
	}
	
	public void render(Graphics g) {
			
			frames++;
			
			if (frames == maxframes) {

				frames = 0;
				index++;

				if (index > maxindex) {

					index = 0;
				}

			}
			
		
		if(dir == 1) {
			
			sprite = Entity.BOZER[index];
			
		} else if(dir == -1) {
			
			sprite = Entity.BOZER[index];
			
		}
		
		super.render(g);
	}
	
}
