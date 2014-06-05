package com.jumpityJump.game;


import java.util.ArrayList;



import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

public class GameLevel implements Screen{

	private World world;
	private Box2DDebugRenderer debugRenderer;
	private OrthographicCamera camera;
	private Hero box;
	private ArrayList <Platform> plats= new ArrayList<Platform>();
	private ArrayList <Platform> walls= new ArrayList<Platform>();
	private ArrayList <Rune> runes = new ArrayList<Rune>();
	private ArrayList <Monster> monsters = new ArrayList<Monster>();
	private String keyToDelete ="";
	private Key[] keys = new Key[3];
	private Platform floor;
	private final float TIMESTEP = 1/60f;
	private final int VelocityIterations = 8, PositionIterations = 3;
	boolean destroyedRune =false;


	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		world.step(TIMESTEP, VelocityIterations, PositionIterations);
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
/*
		for(int j = 0; j < keys.length;j++)
		{
			if(keyToDelete.equals(keys[j].body.getUserData()))
			{
				world.destroyBody(keys[j].body);
				keys[j].body.setUserData(null);
				keys[j].body = null;		
			}
		}
	
*/
		debugRenderer.render(world, camera.combined);
		monsters.listIterator().next().update();
		box.update();
		camera.update();
	}

	@Override
	public void resize(int width, int height) {
		camera.viewportWidth = width/10;
		camera.viewportHeight= height/10;

	}

	public Hero getBox() {
		return box;
	}

	@Override
	public void show() {
		world = new World(new Vector2(0,-40.0f), true);

		debugRenderer = new Box2DDebugRenderer();
		camera = new OrthographicCamera(Gdx.graphics.getWidth() /10, Gdx.graphics.getHeight()/10 );
		box =new Hero(world, 0, 0,this);
		box.getBody().setActive(true);
		world.setContactListener(box);
		floor = new Platform(world, 0,-25, 2, 35);
		plats.add(new Platform(world,15 , 10, 1, 3));
		plats.add(new Platform(world,0, -10, 1, 3));
		plats.add(new Platform(world,15 , -15, 1, 3));
		plats.add(new Exit(world,0 ,5, 1, 3));
		plats.add(new Platform(world, -15 , -15, 1, 3));
		plats.add(new Platform(world,-15 , 10, 1, 3));
		walls.add(new Platform (world, -25 , 0, 35, 1));
		walls.add(new Platform (world, 25 , 0, 35, 1));
		walls.add(new Platform (world, 0 ,25, 2, 35));
		runes.add(new Rune(world,0, -7, 0.5f));
		keys[0] = new Key(world,15, -12, 0.5f,"1");
		keys[1] = new Key(world,-15, -12, 0.5f,"2");
		keys[2] = new Key(world,-15, 12, 0.5f,"3");

		monsters.add(new Monster(world, 15, 12.5f, 2f, 2f));
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	public void addKeytoDelete(String key)
	{
		keyToDelete = key;		
	}

}
