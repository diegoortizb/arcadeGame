package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Player extends GameObject{
	
	Handler handler;
	

	public Player(int x, int y, ID id, Handler handler) {
		
		super(x, y, id);
		this.handler = handler;
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 32, 32);
	}

	public void tick() {
		x += velX;
		y += velY;
		
		handler.addObject(new Trail(x, y, ID.Trail, Color.white, 32, 32, 0.06f, handler));

		
		x = Game.clamp(x,  0, Game.WIDTH - 32); //setting barriers 
		y = Game.clamp(y,  0, Game.HEIGHT - 54); //so you can't go outside the walls
		
		collision();
	}

	
	public void collision() {
		for (int i = 0; i < handler.object.size(); i++) {
			
			GameObject tempObject = handler.object.get(i);
			if (tempObject.getId() == ID.BasicEnemy || tempObject.getId() == ID.FastEnemy || tempObject.getId() == ID.SmartEnemy) {
				if(getBounds().intersects(tempObject.getBounds())) {
					//collision code
					HUD.HEALTH -= 1;
				}
			}
		}
	}
	
	public void render(Graphics g) {
 		
		g.setColor(Color.white);
		g.fillRect((int)x, (int)y, 32, 32);
		
	}
	
	
	
	
	
	
}
