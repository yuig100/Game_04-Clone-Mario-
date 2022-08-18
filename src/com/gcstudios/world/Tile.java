package com.gcstudios.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.gcstudios.main.Game;

public class Tile {
	
	public static BufferedImage TILE_FLOOR = Game.spritesheet.getSprite(0,0,16,16);
	public static BufferedImage TILE_WALL = Game.spritesheet.getSprite(0,16,16,16);
	public static BufferedImage TILE_BRICK = Game.spritesheet.getSprite(16,32,16,16);
	public static BufferedImage TILE_GROUND = Game.spritesheet.getSprite(16,0,16,16);
	public static BufferedImage TILE_UNDERGROUND = Game.spritesheet.getSprite(16,16,16,16);
	public static BufferedImage TILE_QUESTION = Game.spritesheet.getSprite(0,32,16,16);
	public static BufferedImage[] TILE_PIPE = {Game.spritesheet.getSprite(16,47,16,16),Game.spritesheet.getSprite(16,64,16,16)};
	public static BufferedImage[] TILE_CLOUD = {Game.spritesheet.getSprite(0,80,16,16),Game.spritesheet.getSprite(16,80,16,16),Game.spritesheet.getSprite(0,96,16,16),Game.spritesheet.getSprite(16,96,16,16)};
	public static BufferedImage[] TILE_BLOCK = {Game.spritesheet.getSprite(0,48,16,16),Game.spritesheet.getSprite(0,64,16,16)};

	
	
	

	private BufferedImage sprite;
	private int x,y;
	
	public Tile(int x,int y,BufferedImage sprite){
		this.x = x;
		this.y = y;
		this.sprite = sprite;
	}
	
	public void render(Graphics g){
		g.drawImage(sprite, x - Camera.x, y - Camera.y, null);
	}

}
