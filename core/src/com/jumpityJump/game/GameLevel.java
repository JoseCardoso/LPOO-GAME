package com.jumpityJump.game;


import java.util.ArrayList;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

public class GameLevel implements Screen{

	private World world;
	private Box2DDebugRenderer debugRenderer;
	private OrthographicCamera camera;
	private Hero box;
	private ArrayList <Platform> plats= new ArrayList<Platform>();
	private ArrayList <Platform> walls= new ArrayList<Platform>();
	private Platform floor;
	private final float TIMESTEP = 1/60f;
	private final int VelocityIterations = 8, PositionIterations = 3;
	
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		world.step(TIMESTEP, VelocityIterations, PositionIterations);
		
		debugRenderer.render(world, camera.combined);
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
		box =new Hero(world, 0, 0);
		box.getBody().setActive(true);
		world.setContactListener(box);
		floor = new Platform(world, 0,-25, 2, 35);
		plats.add(new Platform(world,15 , 10, 1, 3));
		plats.add(new Platform(world,0, -10, 1, 3));
		plats.add(new Platform(world,15 , -15, 1, 3));
		plats.add(new Platform(world,0 ,5, 1, 3));
		plats.add(new Platform(world, -15 , -15, 1, 3));
		plats.add(new Platform(world,-15 , 10, 1, 3));
		walls.add(new Platform (world, -25 , 0, 35, 1));
		walls.add(new Platform (world, 25 , 0, 35, 1));
		walls.add(new Platform (world, 0 ,25, 2, 35));
		
		
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

	

}
