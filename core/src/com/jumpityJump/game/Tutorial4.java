package com.jumpityJump.game;

public class Tutorial4 extends GameLevel {

	Tutorial4()
	{
		super("Tutorial4");

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
		runes.add(new Rune(world,-40, -10, 0.5f,"Acceleration Boost"));
	}
	
	void updateRunes()
	{
	if(box.isWithRune() )
	{
		if(!destroyedRune)
		{
			world.destroyBody(runes.listIterator().next().body);
			runes.listIterator().next().body.setUserData(null);
			runes.listIterator().next().body = null;
			runes.clear();
			destroyedRune = true;
			box.setKeyCount(3);
		}
	}
	else if(runes.isEmpty())
	{
		runes.add(new Rune(world,-40, -10, 0.5f,"Acceleration Boost"));
		destroyedRune = false;
	}
	else
		box.endRune();
	
	}
	
	
	
}
