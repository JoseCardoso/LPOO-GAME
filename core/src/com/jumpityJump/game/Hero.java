package com.jumpityJump.game;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Hero implements ContactListener,GestureListener, InputProcessor{

	public Body body;
	public Fixture fixture;
	public float force=0;
	public float cx,cy;
	public float velocity = 20f;
	public float upSpeed =30f;
	public boolean withRune = false;
	public boolean jump =false;
	private int timeRune = 0;
	private int KeyCount = 0;
	private int hitPoints;
	//private Sprite heroSprite;
	GameLevel gameLevel;
	private boolean invunerable, doubleDamage;

	public Hero(World world, float cx, float cy,GameLevel gameLevel/*,Sprite text*/) {
		invunerable = false;
		doubleDamage = false;

		this.cx = cx;
		this.cy = cy;
		this.gameLevel = gameLevel;
		hitPoints=4;
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.DynamicBody;
		bodyDef.position.set(cx, cy);
		bodyDef.fixedRotation =true;

		PolygonShape shape = new PolygonShape();
		shape.setAsBox(1.0f,  1.5f);

		//heroSprite = new Sprite(text);
	//	heroSprite.setSize(1.0f, 1.5f);
		FixtureDef fixtureDef= new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.restitution = 0.0f;
		fixtureDef.friction = 1f;
		fixtureDef.density = 8;
		body = world.createBody(bodyDef);
		fixture = body.createFixture(fixtureDef);



		InputMultiplexer im = new InputMultiplexer();
		GestureDetector gd = new GestureDetector(this);
		im.addProcessor(gd);
		im.addProcessor(this);


		Gdx.input.setInputProcessor(im);
	}

	public Body getBody() {
		return body;
	}

	@Override
	public void beginContact(Contact contact) {

		Fixture FixtA = contact.getFixtureA();
		Fixture FixtB = contact.getFixtureB();

		String FixtAString = ((String) FixtA.getUserData()) +"";//estas aspas servem para clonar a string, evitando que esta variável fique a apontar para um vazio
		String FixtBString = ((String) FixtB.getUserData()) +"";


		jump=true;

		if (FixtA.getUserData() == "exit" || FixtB.getUserData() == "exit")
		{	
			if (KeyCount == 3)
				((Game) Gdx.app.getApplicationListener()).setScreen(new WinScreen(gameLevel.timePassed(), gameLevel));

		}
		else if (FixtAString.startsWith("rune"))
		{
			timeRune = 1000;
			withRune = true;
			processRune(FixtAString);
			gameLevel.setRuneToDelete(FixtAString);
		}
		else if( FixtBString.startsWith("rune"))
		{
			timeRune = 1000;
			withRune = true;
			processRune(FixtBString);
			gameLevel.setRuneToDelete(FixtBString);
		}

		else if ( FixtAString.startsWith("key"))
		{
			gameLevel.sound();
			KeyCount++;
			gameLevel.setKeyToDelete(FixtAString);
		}

		else if( FixtBString.startsWith("key"))
		{
			gameLevel.sound();
			KeyCount++;
			gameLevel.setKeyToDelete( FixtBString);
		}
		else if ( FixtAString.startsWith("monster"))
		{
			gameLevel.vibrate();
			if(FixtA.getBody().getPosition().y +2 > FixtB.getBody().getPosition().y)
			{
				if(!invunerable)
					hitPoints--;
			}
			else
				gameLevel.setMonsterToDelete(FixtAString);
		}

		else if( FixtBString.startsWith("monster"))
		{
			gameLevel.vibrate();
			if(FixtB.getBody().getPosition().y +2 > FixtA.getBody().getPosition().y)
			{
				if(!invunerable){
					hitPoints--;
				}
			}
			else
			{
				gameLevel.setMonsterToDelete(FixtBString);
			}
		}

	}


	public void setKeyCount(int keyCount) {
		KeyCount = keyCount;
	}

	private void processRune(String rune)//process each power received by the hero
	{
		if(rune.contains("Acceleration Boost"))
		{
			velocity = 40f;
			upSpeed =60f;
		}
		else if(rune.contains("Invulnerability"))
		{
			invunerable = true;
		}
		else if(rune.contains("Double Damage"))
		{
			doubleDamage = true;
		}



	}

	public void endRune()
	{
		upSpeed =30f;
		velocity = 20f;
		invunerable = false;
		doubleDamage = false;
	}

	public boolean isWithRune() {
		return withRune;
	}

	@Override
	public void endContact(Contact contact) 
	{

		Fixture FixtA = contact.getFixtureA();
		Fixture FixtB = contact.getFixtureB();

		if (FixtA.getUserData() == "exit" || FixtB.getUserData() == "exit")
		{	
			jump=false;
		}
		else if (FixtA.getUserData() == "platform" || FixtB.getUserData() == "platform")
		{	
			jump=false;
		}
	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
		// TODO Auto-generated method stub

	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {

		// TODO Auto-generated method stub

	}

	public void update() {

		float accel = Gdx.input.getAccelerometerY();
		if(accel < -1)
			body.setLinearVelocity(-velocity, body.getLinearVelocity().y);
		else if(accel > 1)
			body.setLinearVelocity(velocity, body.getLinearVelocity().y);

		if (Gdx.input.isKeyPressed(Input.Keys.UP))
		{
			if(jump){
				jump = false;
				body.applyLinearImpulse(0.0f, 2500.0f ,body.getWorldCenter().x ,body.getWorldCenter().y, true);
				if (body.getLinearVelocity().y > upSpeed)
					body.setLinearVelocity(body.getLinearVelocity().x, upSpeed);
			}
		}

		if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
		{
			body.setLinearVelocity(-velocity, body.getLinearVelocity().y);
		}

		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
			body.setLinearVelocity(velocity, body.getLinearVelocity().y);
		}


		if(timeRune > 0)
			timeRune--;
		else if(timeRune <= 0)
			withRune = false;
		if(withRune)
		{
			//	mp3Music.play();
			//	mp3Music.setVolume(1.0f);
		}
		else{}
		//mp3Music.stop();
	}

	public int getKeyCount() {
		return KeyCount;
	}

	public boolean isDoubleDamage() {
		return doubleDamage;
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		if(jump)
		{
			jump = false;
			body.applyLinearImpulse(0.0f, 2500.0f ,body.getWorldCenter().x ,body.getWorldCenter().y, true);
			if (body.getLinearVelocity().y > upSpeed)
				body.setLinearVelocity(body.getLinearVelocity().x, upSpeed);
		}
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(float x, float y, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean tap(float x, float y, int count, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean longPress(float x, float y) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean fling(float velocityX, float velocityY, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean pan(float x, float y, float deltaX, float deltaY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean panStop(float x, float y, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean zoom(float initialDistance, float distance) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2,
			Vector2 pointer1, Vector2 pointer2) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean endGame()
	{
		if (hitPoints <= 0)
			return true;

		else if (body.getPosition().y < -70)
			return true;
		else 
			return false;
	}	

}



