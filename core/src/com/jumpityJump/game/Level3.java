package com.jumpityJump.game;

public class Level3 extends GameLevel {
	
	Level3()
	{
		super("Level3");
	}
	
	@Override
	public void show() {
		super.show();

		box =new Hero(world, -15-50, -12,this);
		box.getBody().setActive(true);
		world.setContactListener(box);
	
		walls.add(new Platform (world, -75 , 0, 35, 1));//PAREDE ESQUERDA

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
		}
	}
	else if(runes.isEmpty())
	{
		runes.add(new Rune(world,-10, 2, 0.5f));
		destroyedRune = false;
	}
	else
		box.endRune();
	
	}

}
