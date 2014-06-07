package com.jumpityJump.game;


import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class Monster {

	public Body body;
	public Fixture fixture;
	public float cx,cy, rangeLeft, rangeRight;
	private boolean goingLeft;
	private Integer myCount;
	private String name;
	private int hitPoints;
	static int count = 0;
	private boolean negativeCoords;

	Monster(World world, float cx, float cy, float rangeLeft, float rangeRight)
	{
		negativeCoords = (cx < 0);
		count++;
		myCount = count;
		name = "monster" + myCount.toString();
		hitPoints = 2;
		this.cx = cx;
		this.cy = cy;
		this.rangeLeft = cx - rangeLeft;
		this.rangeRight = cx + rangeRight;		
		goingLeft = true;
		//	mp3Music =  Gdx.audio.newMusic(Gdx.files.internal("SOMKEWEEDEVERYDAY.mp3"));
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.KinematicBody;
		bodyDef.position.set(cx, cy);
		bodyDef.fixedRotation =true;

		PolygonShape shape = new PolygonShape();
		shape.setAsBox(1.0f,  1.5f);

		FixtureDef fixtureDef= new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.restitution = 0.8f;
		fixtureDef.friction = 1f;
		fixtureDef.density = 8;

		body = world.createBody(bodyDef);
		fixture = body.createFixture(fixtureDef);
		fixture.setUserData(name);
		body.setUserData(name);
	}

	public void update() {

		float inc = (rangeRight + rangeLeft)/1000;
		if(negativeCoords)
			inc *=-1;
		
		if(cx <= rangeLeft)
			goingLeft = false;
		else if(cx >= rangeRight)
			goingLeft = true;

		if(goingLeft)
			cx -= inc;
		else
			cx += inc;

		body.setTransform(cx, cy, 0);
	}
	
	public void decHP()
	{
		hitPoints--;
	}

	public int getHitPoints() {
		return hitPoints;
	}
	
	

}
