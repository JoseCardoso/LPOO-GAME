package com.jumpityJump.game;


//matar o monstro e chegar à saída
public class Tutorial2 extends GameLevel{

	Tutorial2()
	{
		super("Tutorial2");

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
		monsters.add(new Monster(world, -10, -17.5f, 2, 2));
	}
	
	void updateRunes()
	{
		if(monsters.size() <1)
			box.setKeyCount(3);		
	}
	
}
