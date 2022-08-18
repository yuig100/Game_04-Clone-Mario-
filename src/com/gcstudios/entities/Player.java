package com.gcstudios.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.gcstudios.main.Game;
import com.gcstudios.main.Sound;
import com.gcstudios.world.Camera;
import com.gcstudios.world.World;

public class Player extends Entity{
	
	public static boolean right=false,left=false,space = false,moved=false;
	int dir = 1;
	public static int life = 3;
	public static boolean jump = false;
	public boolean isJumping = false;
	public int jumpHeight = 38;
	public int jumpFrames = 0;	
	private int frames = 0, maxframes = 15, index = 0, maxindex = 1;
	public boolean invunerable = false;
	public int inv=0;
	public static BufferedImage[] PLAYER_SPRITE;
	public static int CurrentCoins = 0,maxCoins=0;
	
	private double gravity = 0.6,vspd=0,signVsp=0;
	
	public Player(int x, int y, int width, int height,double speed,BufferedImage sprite) {
		super(x, y, width, height,speed,sprite);
		
	}
	
	public void tick(){
		
		depth = 2;
		
		vspd+=gravity;
		
		if(!World.isFree((int)x, (int)(y + 1)) && jump) {
			Sound.jump.play();
			vspd = -6;
			jump = false;
			
		}
		
		if(!World.isFree((int)x, (int)(y + vspd))){
			
			int signVsp = 0;
			if(vspd >=0) {
				
				signVsp = 1;
				
			} else {
				
				signVsp = -1;
				
			}
			
			while(World.isFree((int)x, (int)(y + signVsp))) {
				
				y = y + signVsp;
				
			}
			vspd = 0;
		} 
		
		y = y + vspd;
		

		if(World.isFree((int)x, (int)(y + 1)) && jump == false){

			
			
			for(int i = 0;i < Game.entities.size();i++) {
				
				Entity e = Game.entities.get(i);
				if(e instanceof Enemy) {
					
					if(Entity.isColidding(this, e)) {
						
							isJumping = true;
							((Enemy)e).vida--;
							if(((Enemy)e).vida == 0) {
								Sound.hit.play();
								Game.entities.remove(i);
								break;
							}
					}
					
				}
				
				
			}
			
		} 
		
		if(right && World.isFree((int)(x + speed),(int) y)) {
			dir = 1;
			x+=speed;
			
		} else if(left && World.isFree((int)(x - speed),(int) y)) {
			
			dir = -1;
			x-=speed;

		} 
		
		if(jump) {
			
			if(!World.isFree(this.getX(), this.getY() +1)) {
				
				isJumping = true;
				
			} else {
				
				jump = false;
				
			}
			
		}
		
		if(isJumping) {
			
			if(World.isFree(this.getX(), this.getY() - 2)) {
				y-=2;
				jumpFrames+=2;
				if(jumpFrames == jumpHeight) {

					isJumping=false;
					jump = false;
					jumpFrames = 0;
					
				}
				
			} else {
				
				isJumping = false;
				jump = false;
				jumpFrames = 0;
				
			}
			
		}
		
		if(invunerable == false) {
			
			for(int i = 0;i < Game.entities.size();i++) {
				
				Entity e = Game.entities.get(i);
				if(e instanceof Enemy) {
					
					if(Entity.isColidding(this, e)) {
							
							Sound.hurt.play();
							Player.life--;
							invunerable = true;
					}
					
				}
				
				
			}
			
		}
		
		if(invunerable == true) {
			
			inv++;
			
			if(inv == 100) {
				inv = 0;
				invunerable = false;
				
			}
		}
		if(life <= 0) {
		
			//Sound.die.play();

			//restartar jogo
			
		}
		
		for(int i = 0;i < Game.entities.size();i++) {
			
			Entity e = Game.entities.get(i);
			if(e instanceof Coin) {
				
				if(Entity.isColidding(this, e)) {
						
						Sound.coin.play();
						Game.entities.remove(i);
						Player.CurrentCoins++;
						break;
				}
				
			}
				
		}
		
		
			Camera.x = Camera.clamp((int)x - Game.WIDTH/2, 0, World.WIDTH * 16 - Game.WIDTH);
			Camera.y = Camera.clamp((int)y - Game.HEIGHT/2, 0, World.HEIGHT * 16 - Game.HEIGHT);
	}
	
	public void render(Graphics g) {
		
		if(moved) {
			
			frames++;
			
			if (frames == maxframes) {

				frames = 0;
				index++;

				if (index > maxindex) {

					index = 0;
				}

			}
			
		}
		
		if(dir == 1) {
			
			sprite = Entity.PLAYER_SPRITE[index];
			
		} else if(dir == -1) {
			
			sprite = Entity.PLAYER_SPRITE[index+3];
			
		}
		
		if(jump) {
			
			if(dir == 1) {
				
				sprite = Entity.PLAYER_SPRITE[2];
				
			} else if(dir == -1) {
				
				sprite = Entity.PLAYER_SPRITE[5];
				
			}
			
		}
		
		super.render(g);
		
	}
	
}
