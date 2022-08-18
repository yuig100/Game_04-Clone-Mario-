package com.gcstudios.graficos;


import java.awt.Font;
import java.awt.Graphics;

import com.gcstudios.entities.Player;
import com.gcstudios.main.Game;

public class UI {

	public void render(Graphics g) {
		
		if(Player.life >= 1) {
			
			g.drawImage(Game.spritesheet.getSprite(128, 80, 16, 16),20, 20, 35, 35,null);
			
			if(Player.life >= 2) {
				
				g.drawImage(Game.spritesheet.getSprite(128, 80, 16, 16),60, 20, 35, 35,null);
				
				if(Player.life == 3) {
					
					g.drawImage(Game.spritesheet.getSprite(128, 80, 16, 16),100, 20, 35, 35,null);
					
				}
				
			}
			
		} 
		g.setFont(new Font("Arial",Font.BOLD,30));
		g.drawString(Player.CurrentCoins+"", 630, 42);
		g.drawImage(Game.spritesheet.getSprite(128, 32,16, 16),580, 10, 45, 45,null);
		
	}
	
}
