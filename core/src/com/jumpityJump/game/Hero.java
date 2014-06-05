package com.jumpityJump.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
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

public class Hero implements ContactListener{

	public Body body;
	public Fixture fixture;
	public float force=0;
	public float cx,cy;
	public float velocity = 20f;
	public float upSpeed =30f;
	public boolean withRune = false;
	public boolean jump =false;
	//Music mp3Music;
	private int timeRune = 0;
	private int KeyCount = 0;
	GameLevel gameLevel;


	public Hero(World world, float cx, float cy,GameLevel gameLevel) {

		this.cx = cx;
		this.cy = cy;
		this.gameLevel = gameLevel;
		//	mp3Music =  Gdx.audio.newMusic(Gdx.files.internal("SOMKEWEEDEVERYDAY.mp3"));
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.DynamicBody;
		bodyDef.position.set(cx, cy);
		bodyDef.fixedRotation =true;

		PolygonShape shape = new PolygonShape();
		shape.setAsBox(1.0f,  1.5f);

		FixtureDef fixtureDef= new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.restitution = 0.0f;
		fixtureDef.friction = 1f;
		fixtureDef.density = 8;

		body = world.createBody(bodyDef);
		fixture = body.createFixture(fixtureDef);
	}

	public Body getBody() {
		return body;
	}

	@Override
	public void beginContact(Contact contact) {

		Fixture FixtA = contact.getFixtureA();
		Fixture FixtB = contact.getFixtureB();

		
		
		if (FixtA.getUserData() == "platform" || FixtB.getUserData() == "platform")
			jump =  true;

		else if (FixtA.getUserData() == "exit" || FixtB.getUserData() == "exit")
		{
			jump =  true;
			System.out.println("lol,n00b");
		}

		else if (FixtA.getUserData() == "rune"  )
		{
			timeRune = 100;
			withRune = true;
		}
		else if( FixtB.getUserData() == "rune" )
		{
			timeRune = 100;
			withRune = true;
		}

		else if ( FixtA.getUserData() == "key")
		{
			KeyCount++;
			gameLevel.addKeytoDelete("key");
		}

	/*	else if( FixtB.getUserData() == "key" )
		{
			KeyCount++;
			gameLevel.addKeytoDelete( "");
		}*/

	}


	public boolean isWithRune() {
		return withRune;
	}

	@Override
	public void endContact(Contact contact) {

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


}



