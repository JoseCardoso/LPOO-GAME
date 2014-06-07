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

	protected World world;
	private Box2DDebugRenderer debugRenderer;
	private OrthographicCamera camera;
	protected Hero box;
	protected ArrayList <Platform> plats= new ArrayList<Platform>();
	protected ArrayList <Platform> walls= new ArrayList<Platform>();
	protected ArrayList <Rune> runes = new ArrayList<Rune>();
	protected ArrayList <Monster> monsters = new ArrayList<Monster>();
	protected String keyToDelete ="";
	protected String runeToDelete ="";
	protected String monsterToDelete ="";
	protected Key[] keys = new Key[3];
	protected final float TIMESTEP = 1/60f;
	protected final int VelocityIterations = 8, PositionIterations = 3;
	protected boolean destroyedRune =false;


	@Override
	public void render(float delta) 
	{
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		world.step(TIMESTEP, VelocityIterations, PositionIterations);

		updateRunes();

		if(!keyToDelete.isEmpty())//há uma chave para apagar
			updateKeys();
		updateMonsters();
		debugRenderer.render(world, camera.combined);
		box.update();
		camera.update();
	}


	private void updateMonsters() {
		// TODO Auto-generated method stub
			
		for(int i = 0; i < monsters.size();i++)
		{
			String t = monsters.listIterator(i).next().body.getUserData() + "";
			if(t.equals(monsterToDelete))
			{
				if(box.isDoubleDamage())//para o caso do heroi estar com uma rune de dano duplo
					monsters.listIterator(i).next().decHP();
				if(monsters.listIterator(i).next().getHitPoints() >1)
				{
					monsters.listIterator(i).next().decHP();
					monsters.listIterator(i).next().update();
				}
				else
				{
					world.destroyBody(monsters.listIterator(i).next().body);
					monsters.listIterator(i).next().body.setUserData(null);
					monsters.listIterator(i).next().body = null;
					monsters.remove(i);
					break;
				}
			}
			else
				monsters.listIterator(i).next().update();
		}
		monsterToDelete = "";
	}


	void updateRunes()
	{

	}

	void updateKeys()
	{

		int sizeKeys = keys.length;
		Key[] tempKeys = new Key[sizeKeys-1];
		String t;
		int count = 0;
		for(int j = 0; j < sizeKeys;j++)
		{
			t = ((String)keys[j].body.getUserData() )+"";//para evitar apontadores para vazio

			if(keyToDelete.equals(t))//se for esta a chave a apagar
			{
				world.destroyBody(keys[j].body);
				keys[j].body.setUserData(null);
				keys[j].body = null;		
				count--;
			}
			else
			{
				tempKeys[count] = keys[j];
			}
			count++;
		}
		keys = new Key[sizeKeys-1];
		for(int j = 0; j < sizeKeys-1;j++)
		{
			keys[j] = tempKeys[j];
		}

		keyToDelete = "";

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
		box.dispose();
	}

	public void setKeyToDelete(String key)
	{
		keyToDelete = key +"";		
	}


	public void setRuneToDelete(String runeToDelete) {
		this.runeToDelete = runeToDelete;
	}


	public void setMonsterToDelete(String monsterToDelete) {
		this.monsterToDelete = monsterToDelete;
	}



}
