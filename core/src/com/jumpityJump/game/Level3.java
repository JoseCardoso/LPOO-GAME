package com.jumpityJump.game;


//nivel de visão 
//em x: de -60 a 50
//em y: de-25 a 30
public class Level3 extends GameLevel{
	Level3()
	{
		super("Level3");
		levelHeight = 60;
		levelWidth = 160;
	}
	
	@Override
	public void show() {
		super.show();

		box =new Hero(world, -15-25, -12,this);
		box.getBody().setActive(true);
		world.setContactListener(box);
		
		plats.add(new Platform(world, -15-25 , -15, 1, 8));
		plats.add(new Platform(world,-25 , -7.5f, 1, 7));
		
		keys[0] = new Key(world,-50, 22.5f, 0.5f,"1");
		plats.add(new Platform(world,-50 , 20, 1, 10));

		plats.add(new Platform(world,-50,0, 1, 7));
		monsters.add(new Monster(world, -50, 2.5f, 4.5f, 4.5f));
		
		plats.add(new Platform(world,-30,10, 1, 8));

		plats.add(new Platform(world,5, 15, 1, 7));
		
		monsters.add(new Monster(world,30 , 2.5f, 7.5f, 7.5f));
		plats.add(new Platform(world,30,0, 1, 8));
		
		keys[2] = new Key(world,15, -5, 0.5f,"3");
		plats.add(new Platform(world,15 , -7.5f, 1, 7));
		
		monsters.add(new Monster(world, 20, -17.5f, 4.5f, 4.5f));
		plats.add(new Platform(world,20 , -20, 1, 7));

		keys[1] = new Key(world,40, -20, 0.5f,"2");
		plats.add(new Platform(world,40 , -22.5f, 1, 7));
		
		runes.add(new Rune(world,-10, 2, 0.5f));

		
		plats.add(new Exit(world, 0, 30, 1, 5));
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
