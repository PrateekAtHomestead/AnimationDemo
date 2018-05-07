

import java.awt.*;
import java.util.*;

import processing.core.PImage;

public class Mario extends Sprite {

	public static final int MARIO_WIDTH = 40;
	public static final int MARIO_HEIGHT = 60;
	private double xVelocity, yVelocity;
	private boolean onPlatform;
	private int currentDirection;

	public Mario(PImage img, int x, int y) {
		super(img, x, y, MARIO_WIDTH, MARIO_HEIGHT);
		xVelocity = 0;
		yVelocity = 0;
		onPlatform = false;
		currentDirection = 0;
	}

	// METHODS
	public void walk(int dir) {
		if(dir != currentDirection)
			xVelocity = 0;
		if(onPlatform)
			x += dir* 7;
		else if(xVelocity < 4 && xVelocity > -4) 
			xVelocity += dir * 1.3;
		currentDirection = dir;
	}

	public void jump() {
		if(onPlatform) {
			yVelocity -= 14;
			onPlatform = false;
		}
	}

	public void act(ArrayList<Shape> obstacles) {
		yVelocity += 0.5;
		this.moveByAmount(xVelocity, yVelocity);
		for(Shape s : obstacles) {
			if(s.getBounds().intersects(x, y, MARIO_WIDTH, MARIO_HEIGHT)) {
				yVelocity = 0;
				y -= 0.5;
				onPlatform = true;
				xVelocity = 0;
			}
			
		}
	}

}
