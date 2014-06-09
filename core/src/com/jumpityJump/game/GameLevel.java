package com.jumpityJump.game;

import java.util.ArrayList;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.TimeUtils;

public class GameLevel implements Screen {

	protected World world;
	private Box2DDebugRenderer debugRenderer;
	private OrthographicCamera camera;
	protected Hero box;
	protected ArrayList<Platform> plats = new ArrayList<Platform>();
	protected ArrayList<Platform> walls = new ArrayList<Platform>();
	protected ArrayList<Rune> runes = new ArrayList<Rune>();
	protected ArrayList<Monster> monsters = new ArrayList<Monster>();
	protected String keyToDelete = "";
	protected String runeToDelete = "";
	protected String monsterToDelete = "";
	public TextureAtlas monsterText, heroRunRightText;
	protected int levelWidth, levelHeight;
	protected Key[] keys = new Key[3];
	protected final float TIMESTEP = 1 / 60f;
	protected final int VelocityIterations = 8, PositionIterations = 3;
	protected boolean destroyedRune = false;
	private Sound keySound;
	private long beginTime;
	protected HighScores highScores;
	private String levelName;
	private SpriteBatch batch;
	float animationElapsed =0, monsterAnimatonElapse = 0;
	Animation animacao, monsterAnimation;
	Texture heroStopped, jumpRight, ddRuneText, invRuneText, acRuneText, keyText,back, groundTex;
	Sprite heroSprite, monsterSprite, backGround, dRuneSprite, iRuneSprite, aRuneSprite,keySprite,groundSprite;

	public String getLevelName() {
		return levelName;
	}

	public GameLevel(String levelName) {
		// TODO Auto-generated constructor stub
		this.levelName = levelName;
		FileHandle handle = Gdx.files.local(levelName + ".txt");
		if (handle.exists()) {
			Json json = new Json();
			highScores = json.fromJson(HighScores.class, handle.readString());
		} else
			highScores = new HighScores();
		levelWidth = 0;
		levelHeight = 0;
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		world.step(TIMESTEP, VelocityIterations, PositionIterations);


		if (!keyToDelete.isEmpty())// há uma chave para apagar
			updateKeys();
		updateMonsters();
		debugRenderer.render(world, camera.combined);
		box.update();

		if (box.endGame()) {
			((Game) Gdx.app.getApplicationListener()).setScreen(new LoseScreen(this));
		}
		camera.update();


		updateRunes();

		batch.setProjectionMatrix(camera.combined);
		batch.begin();

		
		
		backGround.setPosition(-backGround.getWidth()/2, -backGround.getHeight()/2);
		backGround.setScale(levelWidth/backGround.getWidth(), levelHeight/backGround.getHeight()*2);
		backGround.draw(batch);

		for(int i = 0; i < monsters.size();i++)
		{
			monsterSprite = new Sprite();
			animationElapsed += Gdx.graphics.getDeltaTime();
			monsterAnimatonElapse +=Gdx.graphics.getDeltaTime() / monsters.size();
			monsterSprite.setRegion(monsterAnimation.getKeyFrame(monsterAnimatonElapse,true));


			if(monsters.listIterator(i).next().isGoingLeft() )
				monsterSprite.flip(true, false);

			box.getBody().setUserData(monsterSprite);
			monsterSprite.setSize(2 *2 , 2*2);
			monsterSprite.setPosition(monsters.listIterator(i).next().body.getPosition().x - monsterSprite.getWidth()/2,monsters.listIterator(i).next().body.getPosition().y - monsterSprite.getHeight()/2  + 0.3f);
			monsterSprite.draw(batch);
		}
		
		
		for(int i = 0; i < plats.size(); i++)
		{
			groundSprite.setSize(plats.listIterator(i).next().getWidth() *2 , 1*2);
			groundSprite.setPosition(plats.listIterator(i).next().body.getPosition().x - groundSprite.getWidth()/2,plats.listIterator(i).next().body.getPosition().y - groundSprite.getHeight()/2  + 0.3f);
			groundSprite.draw(batch);
		}
		

		for(int i = 0; i < keys.length;i++)
		{
			keys[i].body.setUserData(keySprite);
			keySprite.setSize(2f, 2);
			keySprite.setPosition(keys[i].body.getPosition().x -  keySprite.getWidth()/2, keys[i].body.getPosition().y  -  keySprite.getWidth()/2);
			keySprite.draw(batch);
		}


		for(int i = 0; i < runes.size();i++)
		{
			String acc = "Acceleration Boost";
			String inv = "Invulnerability";
			String has = "Double Damage";
			if(runes.listIterator(i).next().getType().equals(acc))
			{
				runes.listIterator(i).next().body.setUserData(aRuneSprite);
				aRuneSprite.setSize(2 , 2);
				aRuneSprite.setPosition(runes.listIterator(i).next().body.getPosition().x -  aRuneSprite.getWidth()/2, runes.listIterator(i).next().body.getPosition().y   -  aRuneSprite.getWidth()/2);
				aRuneSprite.draw(batch);				
			}	
			else if(runes.listIterator(i).next().getType().equals(inv))
			{
				runes.listIterator(i).next().body.setUserData(iRuneSprite);
				iRuneSprite.setSize(2 , 2);
				iRuneSprite.setPosition(runes.listIterator(i).next().body.getPosition().x -  iRuneSprite.getWidth()/2, runes.listIterator(i).next().body.getPosition().y   -  iRuneSprite.getWidth()/2);
				iRuneSprite.draw(batch);				
			}	
			if(runes.listIterator(i).next().getType().equals(has))
			{
				runes.listIterator(i).next().body.setUserData(dRuneSprite);
				dRuneSprite.setSize(2 , 2);
				dRuneSprite.setPosition(runes.listIterator(i).next().body.getPosition().x -  dRuneSprite.getWidth()/2, runes.listIterator(i).next().body.getPosition().y   -  dRuneSprite.getWidth()/2);
				dRuneSprite.draw(batch);				
			}	

		}

		if(box.jump == true)
		{
			if(box.getBody().getLinearVelocity().x == 0)
				heroSprite = new Sprite(heroStopped);
			else
			{
				animationElapsed += Gdx.graphics.getDeltaTime();
				heroSprite = new Sprite();
				heroSprite.setRegion(animacao.getKeyFrame(animationElapsed,true));
			}

		}
		else
		{
			if(box.getBody().getLinearVelocity().x == 0)
				heroSprite = new Sprite(heroStopped);
			else
			{
				heroSprite = new Sprite(jumpRight);
			}
		}
		if(box.getBody().getLinearVelocity().x <0)
			heroSprite.flip(true, false);

		box.getBody().setUserData(heroSprite);
		heroSprite.setSize(1 *2 , 2*2);
		heroSprite.setPosition(box.getBody().getPosition().x - heroSprite.getWidth()/2,box.getBody().getPosition().y - heroSprite.getHeight()/2  + 0.3f);
		heroSprite.draw(batch);
		batch.end();


	}

	private void updateMonsters() {
		// TODO Auto-generated method stub

		for (int i = 0; i < monsters.size(); i++) {
			String t = monsters.listIterator(i).next().body.getUserData() + "";
			if (t.equals(monsterToDelete)) {
				if (box.isDoubleDamage())// para o caso do heroi estar com uma
					// rune de dano duplo
					monsters.listIterator(i).next().decHP();
				if (monsters.listIterator(i).next().getHitPoints() > 1) {
					monsters.listIterator(i).next().decHP();
					monsters.listIterator(i).next().update();
				} else {
					world.destroyBody(monsters.listIterator(i).next().body);
					monsters.listIterator(i).next().body.setUserData(null);
					monsters.listIterator(i).next().body = null;
					monsters.remove(i);
					break;
				}
			} else
				monsters.listIterator(i).next().update();
		}
		monsterToDelete = "";
	}

	void updateKeys() {

		int sizeKeys = keys.length;
		Key[] tempKeys = new Key[sizeKeys - 1];
		String t;
		int count = 0;
		for (int j = 0; j < sizeKeys; j++) {
			t = keys[j].getName() + "";// para evitar apontadores para vazio

			if (keyToDelete.equals(t))// se for esta a chave a apagar
			{
				world.destroyBody(keys[j].body);
				keys[j].body.setUserData(null);
				keys[j].body = null;
				count--;
			} else {
				tempKeys[count] = keys[j];
			}
			count++;
		}
		keys = new Key[sizeKeys - 1];
		for (int j = 0; j < sizeKeys - 1; j++) {
			keys[j] = tempKeys[j];
		}

		keyToDelete = "";

	}

	@Override
	public void resize(int width, int height) {
		float wte = width * levelHeight
				/ height;
		if (wte >= levelWidth) {
			camera.viewportWidth = wte;
			camera.viewportHeight = levelHeight;
		}
		else {
			float hte = height * levelWidth
					/ width;
			camera.viewportWidth = levelWidth;
			camera.viewportHeight = hte;
		}

	}

	public Hero getBox() {
		return box;
	}

	@Override
	public void show() {
		beginTime = TimeUtils.millis();
		
		groundTex = new Texture("img/platform.png");
		groundSprite = new Sprite(groundTex);

		ddRuneText = new Texture("img/DD.png");
		dRuneSprite = new Sprite(ddRuneText);

		invRuneText = new Texture("img/invRune.png");
		iRuneSprite = new Sprite(invRuneText);

		acRuneText = new Texture("img/acceleration.png");
		aRuneSprite = new Sprite(acRuneText);

		keyText = new Texture("img/key.png");
		keySprite = new Sprite(keyText);

		back = new Texture("img/background.jpg");	
		backGround = new Sprite(back);

		heroStopped = new Texture("img/Hero.png");
		jumpRight = new Texture("img/HeroJumpRight.png");
		batch = new SpriteBatch();

		heroRunRightText = new TextureAtlas("img/mario.txt");
		heroRunRightText.findRegion("HeroRight1");

		monsterText = new TextureAtlas("img/monster.txt");

		animacao = new Animation(1/15F,heroRunRightText.getRegions());
		monsterAnimation =  new Animation(1/15F,monsterText.getRegions());

		world = new World(new Vector2(0, -40.0f), true);
		keySound = Gdx.audio.newSound(Gdx.files.internal("shiny-ding.mp3"));
		debugRenderer = new Box2DDebugRenderer();
		float wte = Gdx.graphics.getWidth() * levelHeight
				/ Gdx.graphics.getHeight();
		if (wte >= levelWidth)
			camera = new OrthographicCamera(wte, levelHeight);
		else {
			float hte = Gdx.graphics.getHeight() * levelWidth
					/ Gdx.graphics.getWidth();
			camera = new OrthographicCamera(levelWidth, hte);
		}

		animationElapsed =0;
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
		debugRenderer.dispose();
		back.dispose();
		keySound.dispose();
		heroStopped.dispose();
		jumpRight.dispose();
		monsterText.dispose();
		heroRunRightText.dispose();
		ddRuneText.dispose();
		invRuneText.dispose();
		acRuneText.dispose();
		keyText.dispose();
		groundTex.dispose();
	}

	public void setKeyToDelete(String key) {
		keyToDelete = key + "";
	}

	public void setRuneToDelete(String runeToDelete) {
		this.runeToDelete = runeToDelete;
	}

	public void setMonsterToDelete(String monsterToDelete) {
		this.monsterToDelete = monsterToDelete;
	}

	public HighScores getHighScores() {
		return highScores;
	}

	public void vibrate() {
		if (JumpityJump.vibrate)
			Gdx.input.vibrate(100);
	}

	public void sound() {
		if (JumpityJump.sound)
			keySound.play();
	}

	void updateRunes() {

	}

	public long timePassed() {
		highScores.newScore(TimeUtils.timeSinceMillis(beginTime));
		FileHandle handle = Gdx.files.local(levelName + ".txt");
		Json json = new Json();
		handle.writeString(json.toJson(highScores), false);
		return TimeUtils.timeSinceMillis(beginTime);
	}
}
