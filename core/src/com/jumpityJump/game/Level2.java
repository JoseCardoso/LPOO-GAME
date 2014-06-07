package com.jumpityJump.game;

public class Level2 extends GameLevel{

	@Override
	public void show() {
		super.show();

		box =new Hero(world, -15-50, -12,this);
		box.getBody().setActive(true);
		world.setContactListener(box);
		
		
		///////ZONA A
		walls.add(new Platform (world, -25-50 , 0, 35, 1));//PAREDE ESQUERDA
		
		plats.add(new Platform(world, -15-50 , -15, 1, 3));
		plats.add(new Platform(world,0-50 , -7.5f, 1, 7));
		monsters.add(new Monster(world, -4f-50, 4f, 1f, 1f));
		plats.add(new Platform(world,-4 -50,1.5f, 1, 2));
		plats.add(new Platform(world,20 -50, -20, 1, 3));
		/////ZONA B
		plats.add(new Platform(world,20-50 , 10, 1, 3));
		plats.add(new Platform(world,4 -50, 19, 1, 3));
		
		monsters.add(new Monster(world, -7.5f-50, 22.5f, 2f, 2f));
		plats.add(new Platform(world,-7.5f-50 , 20, 1, 3));
		keys[0] = new Key(world,-15-50, 27, 0.5f,"1");
		plats.add(new Platform(world,-15 -50, 25, 1,  3));
		
		//ZONA C
		plats.add(new Platform(world,40-50 , 0, 1, 3));
		runes.add(new Rune(world,40-50, 2, 0.5f));
		monsters.add(new Monster(world, -10, -12.5f, 10f, 10f));
		plats.add(new Platform(world,40-50 , -15, 1, 13));
		plats.add(new Platform(world,70 -50, -7.5f, 1, 4));
		//walls.add(new Platform (world, 25 , 0, 35, 1));
		//walls.add(new Platform (world, 0 ,100, 2, 35));
		
		///ZONA D
		keys[2] = new Key(world,30, 24.5f, 0.5f,"3");
		plats.add(new Platform(world,30, 22.5f, 1, 4));
		plats.add(new Platform(world,0 , 10, 1, 3));
		plats.add(new Platform(world,10, 19, 1, 3));
		monsters.add(new Monster(world, 21, 22.5f, 2f, 2f));
		plats.add(new Platform(world,21 , 20, 1, 3));
		
		
		//ZONA E
		keys[1] = new Key(world,90-50, -20, 0.5f,"2");
		plats.add(new Platform(world,90 -50, -22.5f, 1, 4));
		monsters.add(new Monster(world, 50, -9f, 3f, 3f));
		plats.add(new Platform(world,50, -11.5f, 1, 4));
		plats.add(new Exit(world,100-50,5, 1, 8));
		

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
