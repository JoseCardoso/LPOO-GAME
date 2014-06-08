package com.jumpityJump.game;

public class Level1 extends GameLevel{

	@Override
	public void show() {
		super.show();

		box =new Hero(world, 0, 0,this);
		box.getBody().setActive(true);
		world.setContactListener(box);
		
		plats.add(new Platform(world, 0,-25, 2, 35));
		plats.add(new Platform(world,20 , 10, 1, 6));
		plats.add(new Platform(world,0, -10, 1, 6));
		plats.add(new Platform(world,20 , -15, 1, 6));
		plats.add(new Platform(world,20 , -3.5f, 1, 6));
		plats.add(new Exit(world,0 ,5, 1, 6));
		plats.add(new Platform(world, -20 , -15, 1, 6));
		plats.add(new Platform(world,-20 , 10, 1, 6));
		walls.add(new Platform (world, -25 , 0, 35, 1));
		walls.add(new Platform (world, 25 , 0, 35, 1));
		walls.add(new Platform (world, 0 ,25, 2, 35));
		runes.add(new Rune(world,0, -7, 0.5f));
		keys[0] = new Key(world,20, -12, 0.5f,"1");
		keys[1] = new Key(world,-20, -12, 0.5f,"2");
		keys[2] = new Key(world,-20, 12, 0.5f,"3");

		monsters.add(new Monster(world, 20, 12.5f, 4.5f, 3f));
		monsters.add(new Monster(world, -20, 12.5f, 3f, 4.5f));
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
		runes.add(new Rune(world,0, -7, 0.5f));
		destroyedRune = false;
	}
	else
		box.endRune();
	
	}
	
}
