package com.jumpityJump.game;

public class Tutorial3 extends GameLevel {


	Tutorial3()
	{
		super("Tutorial3");

		levelWidth=100;
		levelHeight=50;
	}
	
	@Override
	public void show() {
		super.show();

		box =new Hero(world, -15-50, -12,this);
		box.getBody().setActive(true);
		world.setContactListener(box);
	
		walls.add(new Platform (world, -75 , 0, 35, 1));//PAREDE ESQUERDA
		walls.add(new Platform(world, 0, -20, 1, 100));
		walls.add(new Exit(world, 0, -10, 1, 4));
		keys[0] = new Key(world,-50, -10f, 0.5f,"1");
		keys[1] = new Key(world,-40, -10f, 0.5f,"2");
		keys[2] = new Key(world,-30, -10f, 0.5f,"3");
	}
	
	
	
}
