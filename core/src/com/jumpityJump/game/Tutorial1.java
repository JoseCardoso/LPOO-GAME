package com.jumpityJump.game;


//chegar à saída
public class Tutorial1 extends GameLevel {
	
	Tutorial1()
	{
		super("Tutorial1");
		levelWidth=150;
		levelHeight=50;
	}
	
	@Override
	public void show() {
		super.show();

		box =new Hero(world, -15-50, -12,this);
		box.getBody().setActive(true);
		box.setKeyCount(3);
		world.setContactListener(box);
	
		plats.add(new Platform (world, -75 , 0, 35, 1));//PAREDE ESQUERDA
		plats.add(new Platform(world, 0, -20, 1, 100));
		plats.add(new Exit(world, 0, -10, 1, 4));
		keys = new Key[0];
	}
}
